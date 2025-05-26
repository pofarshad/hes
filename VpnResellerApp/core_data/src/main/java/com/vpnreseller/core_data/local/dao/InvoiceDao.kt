package com.vpnreseller.core_data.local.dao

import androidx.room.*
import com.vpnreseller.core_data.local.entity.InvoiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    @Query("SELECT * FROM invoices ORDER BY generationDate DESC")
    fun getAllInvoices(): Flow<List<InvoiceEntity>>

    @Query("SELECT * FROM invoices WHERE id = :id")
    suspend fun getInvoiceById(id: String): InvoiceEntity?

    @Query("SELECT * FROM invoices WHERE representativeId = :representativeId ORDER BY generationDate DESC")
    fun getInvoicesForRepresentative(representativeId: String): Flow<List<InvoiceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoice(invoice: InvoiceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoices(invoices: List<InvoiceEntity>)

    @Update
    suspend fun updateInvoice(invoice: InvoiceEntity)

    @Query("UPDATE invoices SET isSentToTelegram = :isSent WHERE id = :invoiceId")
    suspend fun updateInvoiceSentStatus(invoiceId: String, isSent: Boolean)

    @Query("UPDATE invoices SET status = :status WHERE id = :invoiceId")
    suspend fun updateInvoiceStatus(invoiceId: String, status: String)

    @Query("UPDATE invoices SET pdfPath = :path WHERE id = :invoiceId")
    suspend fun updateInvoicePdfPath(invoiceId: String, path: String?)

    @Query("UPDATE invoices SET imagePath = :path WHERE id = :invoiceId")
    suspend fun updateInvoiceImagePath(invoiceId: String, path: String?)

    @Delete
    suspend fun deleteInvoice(invoice: InvoiceEntity)

    @Query("DELETE FROM invoices WHERE id = :invoiceId")
    suspend fun deleteInvoiceById(invoiceId: String)

    @Query("SELECT COUNT(*) FROM invoices WHERE representativeId = :representativeId AND status != 'PAID'")
    suspend fun getUnpaidInvoiceCount(representativeId: String): Int

    @Query("SELECT SUM(totalAmount) FROM invoices WHERE representativeId = :representativeId AND status != 'PAID'")
    suspend fun getTotalUnpaidAmount(representativeId: String): Double?

    @Transaction
    @Query("SELECT * FROM invoices WHERE id = :invoiceId")
    suspend fun getInvoiceWithLineItems(invoiceId: String): InvoiceWithLineItems?
}
