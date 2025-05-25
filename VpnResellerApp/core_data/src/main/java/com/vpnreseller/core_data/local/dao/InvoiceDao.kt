package com.vpnreseller.core_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vpnreseller.core_data.local.entity.InvoiceHeaderEntity
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Invoice operations
 */
@Dao
interface InvoiceDao {
    
    // Invoice Header operations
    
    /**
     * Get all invoice headers
     */
    @Query("SELECT * FROM invoice_headers ORDER BY generationDate DESC")
    fun getAllInvoiceHeaders(): Flow<List<InvoiceHeaderEntity>>
    
    /**
     * Get invoice headers by representative ID
     */
    @Query("SELECT * FROM invoice_headers WHERE representativeId = :representativeId ORDER BY generationDate DESC")
    fun getInvoiceHeadersByRepresentative(representativeId: String): Flow<List<InvoiceHeaderEntity>>
    
    /**
     * Get invoice header by ID
     */
    @Query("SELECT * FROM invoice_headers WHERE id = :id")
    suspend fun getInvoiceHeaderById(id: String): InvoiceHeaderEntity?
    
    /**
     * Get unpaid invoices
     */
    @Query("SELECT * FROM invoice_headers WHERE status = 'UNPAID' ORDER BY generationDate DESC")
    fun getUnpaidInvoices(): Flow<List<InvoiceHeaderEntity>>
    
    /**
     * Get invoices by status
     */
    @Query("SELECT * FROM invoice_headers WHERE status = :status ORDER BY generationDate DESC")
    fun getInvoicesByStatus(status: String): Flow<List<InvoiceHeaderEntity>>
    
    /**
     * Insert invoice header
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoiceHeader(invoiceHeader: InvoiceHeaderEntity)
    
    /**
     * Update invoice header
     */
    @Update
    suspend fun updateInvoiceHeader(invoiceHeader: InvoiceHeaderEntity)
    
    /**
     * Delete invoice header
     */
    @Delete
    suspend fun deleteInvoiceHeader(invoiceHeader: InvoiceHeaderEntity)
    
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
    @Query("SELECT SUM(totalAmount) FROM invoice_headers WHERE representativeId = :representativeId")
    suspend fun getTotalAmountByRepresentative(representativeId: String): Double?
    
    /**
     * Get total unpaid amount for a representative
     */
    @Query("SELECT SUM(totalAmount) FROM invoice_headers WHERE representativeId = :representativeId AND status = 'UNPAID'")
    suspend fun getUnpaidAmountByRepresentative(representativeId: String): Double?
    
    /**
     * Get invoice count by representative
     */
    @Query("SELECT COUNT(*) FROM invoice_headers WHERE representativeId = :representativeId")
    suspend fun getInvoiceCountByRepresentative(representativeId: String): Int
}
