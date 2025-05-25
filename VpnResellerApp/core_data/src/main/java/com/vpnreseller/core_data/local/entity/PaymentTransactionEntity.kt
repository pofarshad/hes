package com.vpnreseller.core_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpnreseller.core_domain.model.PaymentTransaction
import java.util.Date

/**
 * Room entity for Payment Transaction
 */
@Entity(tableName = "payment_transactions")
data class PaymentTransactionEntity(
    @PrimaryKey
    val id: String,
    val representativeId: String,
    val invoiceId: String?,
    val paymentDate: Long, // Store as timestamp
    val amountPaid: Double,
    val paymentMethod: String,
    val notes: String?
)

/**
 * Extension function to convert PaymentTransactionEntity to PaymentTransaction domain model
 */
fun PaymentTransactionEntity.toDomainModel(): PaymentTransaction {
    return PaymentTransaction(
        id = id,
        representativeId = representativeId,
        invoiceId = invoiceId,
        paymentDate = Date(paymentDate),
        amountPaid = amountPaid,
        paymentMethod = paymentMethod,
        notes = notes
    )
}

/**
 * Extension function to convert PaymentTransaction domain model to PaymentTransactionEntity
 */
fun PaymentTransaction.toEntity(): PaymentTransactionEntity {
    return PaymentTransactionEntity(
        id = id,
        representativeId = representativeId,
        invoiceId = invoiceId,
        paymentDate = paymentDate.time,
        amountPaid = amountPaid,
        paymentMethod = paymentMethod,
        notes = notes
    )
}
