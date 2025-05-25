package com.vpnreseller.core_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vpnreseller.core_data.local.entity.InvoiceEntity // Changed from InvoiceHeaderEntity
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Invoice operations
 */
@Dao
interface InvoiceDao {
    
    // Invoice Header operations
    
    /**
     * Get all invoices
     */
    @Query("SELECT * FROM invoices ORDER BY generationDate DESC") // Changed from invoice_headers
    fun getAllInvoices(): Flow<List<InvoiceEntity>> // Changed from InvoiceHeaderEntity
    
    /**
     * Get invoices by representative ID
     */
    @Query("SELECT * FROM invoices WHERE representativeId = :representativeId ORDER BY generationDate DESC") // Changed from invoice_headers
    fun getInvoicesByRepresentative(representativeId: String): Flow<List<InvoiceEntity>> // Changed from InvoiceHeaderEntity
    
    /**
     * Get invoice by ID
     */
    @Query("SELECT * FROM invoices WHERE id = :id") // Changed from invoice_headers
    suspend fun getInvoiceById(id: String): InvoiceEntity? // Changed from InvoiceHeaderEntity
    
    /**
     * Get unpaid invoices
     */
    @Query("SELECT * FROM invoices WHERE status = 'UNPAID' ORDER BY generationDate DESC") // Changed from invoice_headers
    fun getUnpaidInvoices(): Flow<List<InvoiceEntity>> // Changed from InvoiceHeaderEntity
    
    /**
     * Get invoices by status
     */
    @Query("SELECT * FROM invoices WHERE status = :status ORDER BY generationDate DESC") // Changed from invoice_headers
    fun getInvoicesByStatus(status: String): Flow<List<InvoiceEntity>> // Changed from InvoiceHeaderEntity
    
    /**
     * Insert invoice
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoice(invoice: InvoiceEntity) // Changed from invoiceHeader: InvoiceHeaderEntity
    
    /**
     * Update invoice
     */
    @Update
    suspend fun updateInvoice(invoice: InvoiceEntity) // Changed from invoiceHeader: InvoiceHeaderEntity
    
    /**
     * Delete invoice
     */
    @Delete
    suspend fun deleteInvoice(invoice: InvoiceEntity) // Changed from invoiceHeader: InvoiceHeaderEntity
    
    // Invoice Line Item operations
    
    /**
     * Get line items for an invoice
     */
    @Query("SELECT * FROM invoice_line_items WHERE invoiceHeaderId = :invoiceHeaderId")
    fun getLineItemsByInvoiceId(invoiceHeaderId: String): Flow<List<InvoiceLineItemEntity>>
    
    /**
     * Get line item by ID
     */
    @Query("SELECT * FROM invoice_line_items WHERE id = :id")
    suspend fun getLineItemById(id: String): InvoiceLineItemEntity?
    
    /**
     * Insert line item
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLineItem(lineItem: InvoiceLineItemEntity)
    
    /**
     * Insert multiple line items
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLineItems(lineItems: List<InvoiceLineItemEntity>)
    
    /**
     * Update line item
     */
    @Update
    suspend fun updateLineItem(lineItem: InvoiceLineItemEntity)
    
    /**
     * Delete line item
     */
    @Delete
    suspend fun deleteLineItem(lineItem: InvoiceLineItemEntity)
    
    /**
     * Delete all line items for an invoice
     */
    @Query("DELETE FROM invoice_line_items WHERE invoiceHeaderId = :invoiceHeaderId")
    suspend fun deleteLineItemsByInvoiceId(invoiceHeaderId: String)
    
    // Statistics and aggregations
    
    /**
     * Get total amount for a representative
     */
    @Query("SELECT SUM(totalAmount) FROM invoices WHERE representativeId = :representativeId") // Changed from invoice_headers
    suspend fun getTotalAmountByRepresentative(representativeId: String): Double?
    
    /**
     * Get total unpaid amount for a representative
     */
    @Query("SELECT SUM(totalAmount) FROM invoices WHERE representativeId = :representativeId AND status = 'UNPAID'") // Changed from invoice_headers
    suspend fun getUnpaidAmountByRepresentative(representativeId: String): Double?
    
    /**
     * Get invoice count by representative
     */
    @Query("SELECT COUNT(*) FROM invoices WHERE representativeId = :representativeId") // Changed from invoice_headers
    suspend fun getInvoiceCountByRepresentative(representativeId: String): Int
}
