package com.vpnreseller.core_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpnreseller.core_domain.model.Invoice // Changed from InvoiceHeader
import com.vpnreseller.core_domain.model.InvoiceLineItem
import com.vpnreseller.core_domain.model.InvoiceStatus
import java.util.Date

/**
 * Room entity for Invoice
 */
@Entity(tableName = "invoices") // Changed from invoice_headers
data class InvoiceEntity(
    @PrimaryKey
    val id: String,
    val representativeId: String,
    val generationDate: Long, // Store as timestamp
    val totalAmount: Double,
    val status: String, // Represents InvoiceStatus enum
    val isSentToTelegram: Boolean, // Changed from isSent
    val pdfPath: String?,
    val imagePath: String?,
    val importedSheetData: Map<String, String>? // Room will use TypeConverter
)

/**
 * Room entity for Invoice Line Item
 */
@Entity(tableName = "invoice_line_items")
data class InvoiceLineItemEntity(
    @PrimaryKey
    val id: String,
    val invoiceHeaderId: String,
    val description: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double
)

/**
 * Extension function to convert InvoiceEntity to Invoice domain model
 */
fun InvoiceEntity.toDomainModel(): Invoice {
    return Invoice(
        id = id,
        representativeId = representativeId,
        generationDate = Date(generationDate),
        totalAmount = totalAmount,
        status = InvoiceStatus.valueOf(status),
        isSentToTelegram = isSentToTelegram,
        pdfPath = pdfPath,
        imagePath = imagePath,
        importedSheetData = importedSheetData // Pass through, Room handles conversion
    )
}

/**
 * Extension function to convert Invoice domain model to InvoiceEntity
 */
fun Invoice.toEntity(): InvoiceEntity {
    return InvoiceEntity(
        id = id,
        representativeId = representativeId,
        generationDate = generationDate.time,
        totalAmount = totalAmount,
        status = status.name,
        isSentToTelegram = isSentToTelegram,
        pdfPath = pdfPath,
        imagePath = imagePath,
        importedSheetData = importedSheetData // Pass through, Room handles conversion
    )
}

/**
 * Extension function to convert InvoiceLineItemEntity to InvoiceLineItem domain model
 */
fun InvoiceLineItemEntity.toDomainModel(): InvoiceLineItem {
    return InvoiceLineItem(
        id = id,
        invoiceHeaderId = invoiceHeaderId,
        description = description,
        quantity = quantity,
        unitPrice = unitPrice,
        totalPrice = totalPrice
    )
}

/**
 * Extension function to convert InvoiceLineItem domain model to InvoiceLineItemEntity
 */
fun InvoiceLineItem.toEntity(): InvoiceLineItemEntity {
    return InvoiceLineItemEntity(
        id = id,
        invoiceHeaderId = invoiceHeaderId,
        description = description,
        quantity = quantity,
        unitPrice = unitPrice,
        totalPrice = totalPrice
    )
}
