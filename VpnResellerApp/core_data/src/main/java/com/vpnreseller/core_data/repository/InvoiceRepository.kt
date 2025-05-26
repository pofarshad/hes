package com.vpnreseller.core_data.repository

import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.InvoiceLineItem
import com.vpnreseller.core_domain.model.InvoiceWithItems
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for invoice-related operations.
 */
interface InvoiceRepository {
    // Basic CRUD operations
    suspend fun createInvoice(invoice: Invoice, lineItems: List<InvoiceLineItem>)
    suspend fun updateInvoice(invoice: Invoice)
    suspend fun deleteInvoice(invoiceId: String)
    suspend fun getInvoice(invoiceId: String): Invoice?
    suspend fun getInvoiceWithItems(invoiceId: String): InvoiceWithItems?
    
    // Bulk operations
    suspend fun createInvoices(invoices: List<InvoiceWithItems>)
    suspend fun deleteInvoices(invoiceIds: List<String>)
    
    // Query operations
    fun getAllInvoices(): Flow<List<Invoice>>
    fun getInvoicesByRepresentative(representativeId: String): Flow<List<Invoice>>
    fun getInvoicesByStatus(status: String): Flow<List<Invoice>>
    fun getInvoicesByDateRange(startDate: Long, endDate: Long): Flow<List<Invoice>>
    fun getInvoiceLineItems(invoiceId: String): Flow<List<InvoiceLineItem>>
    
    // Special operations
    suspend fun markInvoiceAsSentToTelegram(invoiceId: String)
    suspend fun updateInvoiceStatus(invoiceId: String, status: String)
    suspend fun calculateTotalOutstanding(representativeId: String): Double
    suspend fun calculateTotalPaid(representativeId: String): Double
    
    // Import/export operations
    suspend fun importInvoicesFromSheet(rows: List<Map<String, String>>): List<InvoiceWithItems>
    suspend fun exportInvoicesToSheet(invoiceIds: List<String>): List<Map<String, String>>
    
    // Validation operations
    suspend fun validateInvoice(invoice: Invoice): Boolean
    suspend fun validateInvoiceWithItems(invoiceWithItems: InvoiceWithItems): Boolean
}
