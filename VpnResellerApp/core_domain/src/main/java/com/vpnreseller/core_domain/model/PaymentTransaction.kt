package com.vpnreseller.core_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
import java.util.UUID

/**
 * Payment transaction domain model
 * Represents a payment made by a representative
 */
@Parcelize
data class PaymentTransaction(
    val id: String = UUID.randomUUID().toString(),
    val representativeId: String,
    val invoiceId: String? = null, // nullable for general payments
    val paymentDate: Date = Date(),
    val amountPaid: Double,
    val paymentMethod: String, // e.g., "Cash", "Bank Transfer", "Online"
    val notes: String? = null,
    val type: TransactionType
) : Parcelable

/**
 * Transaction type enumeration
 * CREDIT: Payment received from representative (reduces their debt / increases their balance)
 * DEBIT: Charge issued to representative (e.g., new invoice, service fee - increases their debt / decreases their balance)
 */
enum class TransactionType {
    CREDIT,
    DEBIT
}
