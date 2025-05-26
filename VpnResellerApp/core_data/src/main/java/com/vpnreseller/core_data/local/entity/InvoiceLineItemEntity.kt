package com.vpnreseller.core_data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vpnreseller.core_domain.model.InvoiceLineItem

/**
 * Database entity for invoice line items.
 * Has a foreign key relationship with InvoiceEntity and is indexed for faster queries.
 */
@Entity(
    tableName = "invoice_line_items",
    foreignKeys = [
        ForeignKey(
            entity = InvoiceEntity::class,
            parentColumns = ["id"],
            childColumns = ["invoiceHeaderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("invoiceHeaderId")
    ]
)
data class InvoiceLineItemEntity(
    @PrimaryKey
    val id: String,
    val invoiceHeaderId: String,
    val description: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double
) {
    /**
     * Validates that the total price matches quantity * unitPrice
     */
    fun isValid(): Boolean {
        return totalPrice == quantity * unitPrice
    }

    fun toDomainModel() = InvoiceLineItem(
        id = id,
        invoiceHeaderId = invoiceHeaderId,
        description = description,
        quantity = quantity,
        unitPrice = unitPrice,
        totalPrice = totalPrice
    )

    companion object {
        fun fromDomainModel(lineItem: InvoiceLineItem) = InvoiceLineItemEntity(
            id = lineItem.id,
            invoiceHeaderId = lineItem.invoiceHeaderId,
            description = lineItem.description,
            quantity = lineItem.quantity,
            unitPrice = lineItem.unitPrice,
            totalPrice = lineItem.totalPrice
        )
    }
}

/**
 * Type converters for Room to handle Date objects
 */
class Converters {
    @androidx.room.TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @androidx.room.TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @androidx.room.TypeConverter
    fun fromInvoiceStatus(status: InvoiceStatus): String {
        return status.name
    }

    @androidx.room.TypeConverter
    fun toInvoiceStatus(status: String): InvoiceStatus {
        return InvoiceStatus.valueOf(status)
    }
}
