package com.vpnreseller.core_data.repository

import com.vpnreseller.core_data.local.dao.InvoiceDao
import com.vpnreseller.core_data.local.entity.InvoiceEntity
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.InvoiceLineItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import com.vpnreseller.core_data.google.GoogleSheetsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.InvoiceLineItem
import java.util.UUID

interface InvoiceRepository {
    fun getAllInvoices(): Flow<List<Invoice>>
    fun getInvoicesByRepresentative(representativeId: String): Flow<List<Invoice>>
    suspend fun getInvoiceById(id: String): Invoice?
    suspend fun insertInvoice(invoice: Invoice, lineItems: List<InvoiceLineItem>)
    suspend fun updateInvoice(invoice: Invoice, lineItems: List<InvoiceLineItem>)
    suspend fun deleteInvoice(invoice: Invoice)
    suspend fun deleteInvoiceById(id: String)
    fun getLineItemsByInvoiceId(invoiceId: String): Flow<List<InvoiceLineItem>>

    // New method to import invoices from Google Sheets
    suspend fun importInvoicesFromGoogleSheet(spreadsheetId: String, range: String)
}

@Singleton
class InvoiceRepositoryImpl @Inject constructor(
    private val invoiceDao: InvoiceDao,
    private val googleSheetsService: GoogleSheetsService
) : InvoiceRepository {

    override fun getAllInvoices(): Flow<List<Invoice>> {
        return invoiceDao.getAllInvoices().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getInvoicesByRepresentative(representativeId: String): Flow<List<Invoice>> {
        return invoiceDao.getInvoicesByRepresentative(representativeId).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun getInvoiceById(id: String): Invoice? {
        return invoiceDao.getInvoiceById(id)?.toDomainModel()
    }

    override suspend fun insertInvoice(invoice: Invoice, lineItems: List<InvoiceLineItem>) {
        invoiceDao.insertInvoice(invoice.toEntity())
        invoiceDao.insertLineItems(lineItems.map { it.toEntity() })
    }

    override suspend fun updateInvoice(invoice: Invoice, lineItems: List<InvoiceLineItem>) {
        invoiceDao.updateInvoice(invoice.toEntity())
        invoiceDao.deleteLineItemsByInvoiceId(invoice.id)
        invoiceDao.insertLineItems(lineItems.map { it.toEntity() })
    }

    override suspend fun deleteInvoice(invoice: Invoice) {
        invoiceDao.deleteInvoice(invoice.toEntity())
        invoiceDao.deleteLineItemsByInvoiceId(invoice.id)
    }

    override suspend fun deleteInvoiceById(id: String) {
        val invoice = invoiceDao.getInvoiceById(id)
        if (invoice != null) {
            invoiceDao.deleteInvoice(invoice)
            invoiceDao.deleteLineItemsByInvoiceId(id)
        }
    }

    override fun getLineItemsByInvoiceId(invoiceId: String): Flow<List<InvoiceLineItem>> {
        return invoiceDao.getLineItemsByInvoiceId(invoiceId).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun importInvoicesFromGoogleSheet(spreadsheetId: String, range: String) {
        val rows = googleSheetsService.getSheetValues(spreadsheetId, range)
        if (rows.isEmpty()) return

        // Assuming first row is header
        val header = rows[0].map { it.toString() }
        val dataRows = rows.drop(1)

        for (row in dataRows) {
            val rowMap = header.zip(row.map { it.toString() }).toMap()

            // Parse invoice data from rowMap
            val invoiceId = rowMap["Invoice ID"] ?: UUID.randomUUID().toString()
            val representativeId = rowMap["Representative ID"] ?: continue
            val generationDateMillis = rowMap["Generation Date"]?.toLongOrNull() ?: System.currentTimeMillis()
            val totalAmount = rowMap["Total Amount"]?.toDoubleOrNull() ?: 0.0
            val status = rowMap["Status"] ?: "UNPAID"
            val isSentToTelegram = rowMap["Is Sent To Telegram"]?.toBoolean() ?: false
            val pdfPath = rowMap["PDF Path"]
            val imagePath = rowMap["Image Path"]

            val importedSheetData = rowMap

            val invoice = Invoice(
                id = invoiceId,
                representativeId = representativeId,
                generationDate = java.util.Date(generationDateMillis),
                totalAmount = totalAmount,
                status = com.vpnreseller.core_domain.model.InvoiceStatus.valueOf(status),
                isSentToTelegram = isSentToTelegram,
                pdfPath = pdfPath,
                imagePath = imagePath,
                importedSheetData = importedSheetData
            )

            // For simplicity, no line items parsed here. Could be extended.

            insertInvoice(invoice, emptyList())
        }
    }
}
