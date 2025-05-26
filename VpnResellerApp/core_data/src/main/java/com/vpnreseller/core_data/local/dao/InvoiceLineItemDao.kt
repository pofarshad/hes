package com.vpnreseller.core_data.local.dao

import androidx.room.*
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceLineItemDao {
    @Query("SELECT * FROM invoice_line_items WHERE invoiceHeaderId = :invoiceId ORDER BY id")
    suspend fun getLineItemsForInvoice(invoiceId: String): List<InvoiceLineItemEntity>

    @Query("SELECT * FROM invoice_line_items WHERE invoiceHeaderId = :invoiceId ORDER BY id")
    fun getLineItemsForInvoiceFlow(invoiceId: String): Flow<List<InvoiceLineItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLineItem(lineItem: InvoiceLineItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLineItems(lineItems: List<InvoiceLineItemEntity>)

    @Update
    suspend fun updateLineItem(lineItem: InvoiceLineItemEntity)

    @Delete
    suspend fun deleteLineItem(lineItem: InvoiceLineItemEntity)

    @Query("DELETE FROM invoice_line_items WHERE invoiceHeaderId = :invoiceId")
    suspend fun deleteLineItemsForInvoice(invoiceId: String)

    @Query("SELECT SUM(totalPrice) FROM invoice_line_items WHERE invoiceHeaderId = :invoiceId")
    suspend fun getTotalAmountForInvoice(invoiceId: String): Double?

    @Transaction
    suspend fun replaceLineItemsForInvoice(invoiceId: String, newLineItems: List<InvoiceLineItemEntity>) {
        deleteLineItemsForInvoice(invoiceId)
        insertLineItems(newLineItems)
    }

    @Query("""
        SELECT COUNT(*) 
        FROM invoice_line_items 
        WHERE invoiceHeaderId IN (
            SELECT id 
            FROM invoices 
            WHERE representativeId = :representativeId
        )
    """)
    suspend fun getLineItemCountForRepresentative(representativeId: String): Int

    @Query("""
        SELECT SUM(quantity) 
        FROM invoice_line_items 
        WHERE invoiceHeaderId IN (
            SELECT id 
            FROM invoices 
            WHERE representativeId = :representativeId
        )
    """)
    suspend fun getTotalQuantityForRepresentative(representativeId: String): Int?
}
