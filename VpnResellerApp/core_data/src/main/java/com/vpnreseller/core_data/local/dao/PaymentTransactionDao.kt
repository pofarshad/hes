package com.vpnreseller.core_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vpnreseller.core_data.local.entity.PaymentTransactionEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Payment Transaction operations
 */
@Dao
interface PaymentTransactionDao {
    
    /**
     * Get all payment transactions
     */
    @Query("SELECT * FROM payment_transactions ORDER BY paymentDate DESC")
    fun getAllPaymentTransactions(): Flow<List<PaymentTransactionEntity>>
    
    /**
     * Get payment transactions by representative ID
     */
    @Query("SELECT * FROM payment_transactions WHERE representativeId = :representativeId ORDER BY paymentDate DESC")
    fun getPaymentTransactionsByRepresentative(representativeId: String): Flow<List<PaymentTransactionEntity>>
    
    /**
     * Get payment transactions by invoice ID
     */
    @Query("SELECT * FROM payment_transactions WHERE invoiceId = :invoiceId ORDER BY paymentDate DESC")
    fun getPaymentTransactionsByInvoice(invoiceId: String): Flow<List<PaymentTransactionEntity>>
    
    /**
     * Get payment transaction by ID
     */
    @Query("SELECT * FROM payment_transactions WHERE id = :id")
    suspend fun getPaymentTransactionById(id: String): PaymentTransactionEntity?
    
    /**
     * Get payment transactions by date range
     */
    @Query("SELECT * FROM payment_transactions WHERE paymentDate BETWEEN :startDate AND :endDate ORDER BY paymentDate DESC")
    fun getPaymentTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<PaymentTransactionEntity>>
    
    /**
     * Get payment transactions by payment method
     */
    @Query("SELECT * FROM payment_transactions WHERE paymentMethod = :paymentMethod ORDER BY paymentDate DESC")
    fun getPaymentTransactionsByMethod(paymentMethod: String): Flow<List<PaymentTransactionEntity>>
    
    /**
     * Insert payment transaction
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentTransaction(paymentTransaction: PaymentTransactionEntity)
    
    /**
     * Insert multiple payment transactions
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentTransactions(paymentTransactions: List<PaymentTransactionEntity>)
    
    /**
     * Update payment transaction
     */
    @Update
    suspend fun updatePaymentTransaction(paymentTransaction: PaymentTransactionEntity)
    
    /**
     * Delete payment transaction
     */
    @Delete
    suspend fun deletePaymentTransaction(paymentTransaction: PaymentTransactionEntity)
    
    /**
     * Delete payment transaction by ID
     */
    @Query("DELETE FROM payment_transactions WHERE id = :id")
    suspend fun deletePaymentTransactionById(id: String)
    
    // Statistics and aggregations
    
    /**
     * Get total payments by representative
     */
    @Query("SELECT SUM(amountPaid) FROM payment_transactions WHERE representativeId = :representativeId")
    suspend fun getTotalPaymentsByRepresentative(representativeId: String): Double?
    
    /**
     * Get total payments for an invoice
     */
    @Query("SELECT SUM(amountPaid) FROM payment_transactions WHERE invoiceId = :invoiceId")
    suspend fun getTotalPaymentsForInvoice(invoiceId: String): Double?
    
    /**
     * Get payment count by representative
     */
    @Query("SELECT COUNT(*) FROM payment_transactions WHERE representativeId = :representativeId")
    suspend fun getPaymentCountByRepresentative(representativeId: String): Int
    
    /**
     * Get total payments by date range
     */
    @Query("SELECT SUM(amountPaid) FROM payment_transactions WHERE paymentDate BETWEEN :startDate AND :endDate")
    suspend fun getTotalPaymentsByDateRange(startDate: Long, endDate: Long): Double?
    
    /**
     * Get payment transactions count
     */
    @Query("SELECT COUNT(*) FROM payment_transactions")
    suspend fun getPaymentTransactionCount(): Int
}
