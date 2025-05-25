package com.vpnreseller.core_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
import java.util.UUID

/**
 * Invoice domain model
 * Represents an invoice for a representative
 */
@Parcelize
data class Invoice(
    val id: String = UUID.randomUUID().toString(),
    val representativeId: String,
    val generationDate: Date = Date(),
    val totalAmount: Double,
    val status: InvoiceStatus = InvoiceStatus.UNPAID,
    val isSentToTelegram: Boolean = false, // Renamed from isSent for clarity
    val pdfPath: String? = null,
    val imagePath: String? = null,
    val importedSheetData: Map<String, String>? = null // To store raw Google Sheet row
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
