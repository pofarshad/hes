package com.vpnreseller.core_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpnreseller.core_domain.model.InvoiceHeader
import com.vpnreseller.core_domain.model.InvoiceLineItem
import com.vpnreseller.core_domain.model.InvoiceStatus
import java.util.Date

/**
 * Room entity for Invoice Header
 */
@Entity(tableName = "invoice_headers")
data class InvoiceHeaderEntity(
    @PrimaryKey
    val id: String,
    val representativeId: String,
    val generationDate: Long, // Store as timestamp
    val totalAmount: Double,
    val status: String,
    val isSent: Boolean
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
 * Extension function to convert InvoiceHeaderEntity to InvoiceHeader domain model
 */
fun InvoiceHeaderEntity.toDomainModel(): InvoiceHeader {
    return InvoiceHeader(
        id = id,
        representativeId = representativeId,
        generationDate = Date(generationDate),
        totalAmount = totalAmount,
        status = InvoiceStatus.valueOf(status),
        isSent = isSent
    )
}

/**
 * Extension function to convert InvoiceHeader domain model to InvoiceHeaderEntity
 */
fun InvoiceHeader.toEntity(): InvoiceHeaderEntity {
    return InvoiceHeaderEntity(
        id = id,
        representativeId = representativeId,
        generationDate = generationDate.time,
        totalAmount = totalAmount,
        status = status.name,
        isSent = isSent
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
