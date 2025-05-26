package com.vpnreseller.core_data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.InvoiceStatus
import java.util.Date

/**
 * Database entity for invoices.
 * Has a foreign key relationship with RepresentativeEntity and is indexed for faster queries.
 */
@Entity(
    tableName = "invoices",
    foreignKeys = [
        ForeignKey(
            entity = RepresentativeEntity::class,
            parentColumns = ["id"],
            childColumns = ["representativeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("representativeId"),
        Index("status"),
        Index("generationDate")
    ]
)
data class InvoiceEntity(
    @PrimaryKey
    val id: String,
    val representativeId: String,
    val generationDate: Date,
    val totalAmount: Double,
    val status: InvoiceStatus,
    val isSentToTelegram: Boolean,
    val pdfPath: String?,
    val imagePath: String?,
    val importedSheetData: String? // Raw data from Google Sheet for reference
) {
    fun toDomainModel() = Invoice(
        id = id,
        representativeId = representativeId,
        generationDate = generationDate,
        totalAmount = totalAmount,
        status = status,
        isSentToTelegram = isSentToTelegram,
        pdfPath = pdfPath,
        imagePath = imagePath,
        importedSheetData = importedSheetData?.let { mapOf("raw" to it) }
    )

    companion object {
        fun fromDomainModel(invoice: Invoice) = InvoiceEntity(
            id = invoice.id,
            representativeId = invoice.representativeId,
            generationDate = invoice.generationDate,
            totalAmount = invoice.totalAmount,
            status = invoice.status,
            isSentToTelegram = invoice.isSentToTelegram,
            pdfPath = invoice.pdfPath,
            imagePath = invoice.imagePath,
            importedSheetData = invoice.importedSheetData?.get("raw")
        )
    }
}

/**
 * Represents the relationship between an invoice and its line items.
 * Used for @Transaction queries that need to fetch both invoice and its items.
 */
data class InvoiceWithLineItems(
    @androidx.room.Embedded
    val invoice: InvoiceEntity,

    @androidx.room.Relation(
        parentColumn = "id",
        entityColumn = "invoiceHeaderId"
    )
    val lineItems: List<InvoiceLineItemEntity>
) {
    fun toDomainModel() = Invoice(
        id = invoice.id,
        representativeId = invoice.representativeId,
        generationDate = invoice.generationDate,
        totalAmount = invoice.totalAmount,
        status = invoice.status,
        isSentToTelegram = invoice.isSentToTelegram,
        pdfPath = invoice.pdfPath,
        imagePath = invoice.imagePath,
        importedSheetData = invoice.importedSheetData?.let { mapOf("raw" to it) }
    )
}
