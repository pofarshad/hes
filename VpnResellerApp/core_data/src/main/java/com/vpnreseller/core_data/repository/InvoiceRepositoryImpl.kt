package com.vpnreseller.core_data.repository

import com.vpnreseller.core_data.local.dao.InvoiceDao
import com.vpnreseller.core_data.local.dao.InvoiceLineItemDao
import com.vpnreseller.core_data.local.entity.InvoiceEntity
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import com.vpnreseller.core_data.mapper.toDomain
import com.vpnreseller.core_data.mapper.toEntity
import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.InvoiceLineItem
import com.vpnreseller.core_domain.model.InvoiceWithItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InvoiceRepositoryImpl @Inject constructor(
    private val invoiceDao: InvoiceDao,
    private val lineItemDao: InvoiceLineItemDao
) : InvoiceRepository {

    override suspend fun createInvoice(invoice: Invoice, lineItems: List<InvoiceLineItem>) {
        val invoiceId = invoiceDao.insert(invoice.toEntity())
        lineItemDao.insertAll(lineItems.map { it.toEntity(invoiceId) })
    }

    override suspend fun updateInvoice(invoice: Invoice) {
        invoiceDao.update(invoice.toEntity())
    }

    override suspend fun deleteInvoice(invoiceId: String) {
        invoiceDao.deleteById(invoiceId)
        lineItemDao.deleteByInvoiceId(invoiceId)
    }

    override suspend fun getInvoice(invoiceId: String): Invoice? {
        return invoiceDao.getById(invoiceId)?.toDomain()
    }

    override suspend fun getInvoiceWithItems(invoiceId: String): InvoiceWithItems? {
        val invoice = invoiceDao.getById(invoiceId) ?: return null
        val lineItems = lineItemDao.getByInvoiceId(invoiceId).map { it.toDomain() }
        return InvoiceWithItems(
            invoice = invoice.toDomain(),
            lineItems = lineItems
        )
    }

    override suspend fun createInvoices(invoices: List<InvoiceWithItems>) {
        invoices.forEach { invoiceWithItems ->
            createInvoice(invoiceWithItems.invoice, invoiceWithItems.lineItems)
        }
    }

    override suspend fun deleteInvoices(invoiceIds: List<String>) {
        invoiceIds.forEach { invoiceId ->
            deleteInvoice(invoiceId)
        }
    }

    override fun getAllInvoices(): Flow<List<Invoice>> {
        return invoiceDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getInvoicesByRepresentative(representativeId: String): Flow<List<Invoice>> {
        return invoiceDao.getByRepresentativeId(representativeId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getInvoicesByStatus(status: String): Flow<List<Invoice>> {
        return invoiceDao.getByStatus(status).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getInvoicesByDateRange(startDate: Long, endDate: Long): Flow<List<Invoice>> {
        return invoiceDao.getByDateRange(startDate, endDate).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getInvoiceLineItems(invoiceId: String): Flow<List<InvoiceLineItem>> {
        return lineItemDao.getByInvoiceIdFlow(invoiceId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun markInvoiceAsSentToTelegram(invoiceId: String) {
        invoiceDao.updateTelegramStatus(invoiceId, true)
    }

    override suspend fun updateInvoiceStatus(invoiceId: String, status: String) {
        invoiceDao.updateStatus(invoiceId, status)
    }

    override suspend fun calculateTotalOutstanding(representativeId: String): Double {
        return invoiceDao.getTotalOutstanding(representativeId) ?: 0.0
    }

    override suspend fun calculateTotalPaid(representativeId: String): Double {
        return invoiceDao.getTotalPaid(representativeId) ?: 0.0
    }

    override suspend fun importInvoicesFromSheet(rows: List<Map<String, String>>): List<InvoiceWithItems> {
        // TODO: Implement actual sheet import logic
        return emptyList()
    }

    override suspend fun exportInvoicesToSheet(invoiceIds: List<String>): List<Map<String, String>> {
        // TODO: Implement actual sheet export logic
        return emptyList()
    }

    override suspend fun validateInvoice(invoice: Invoice): Boolean {
        // TODO: Implement validation logic
        return true
    }

    override suspend fun validateInvoiceWithItems(invoiceWithItems: InvoiceWithItems): Boolean {
        return invoiceWithItems.isValid()
    }
}
