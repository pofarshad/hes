package com.vpnreseller.core_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
import java.util.UUID

/**
 * Invoice header domain model
 * Represents an invoice for a representative
 */
@Parcelize
data class InvoiceHeader(
    val id: String = UUID.randomUUID().toString(),
    val representativeId: String,
    val generationDate: Date = Date(),
    val totalAmount: Double,
    val status: InvoiceStatus = InvoiceStatus.UNPAID,
    val isSent: Boolean = false
) : Parcelable

/**
 * Invoice line item domain model
 * Represents individual items within an invoice
 */
@Parcelize
data class InvoiceLineItem(
    val id: String = UUID.randomUUID().toString(),
    val invoiceHeaderId: String,
    val description: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double
) : Parcelable

/**
 * Invoice status enumeration
 */
enum class InvoiceStatus {
    UNPAID,
    PARTIAL,
    PAID
}
