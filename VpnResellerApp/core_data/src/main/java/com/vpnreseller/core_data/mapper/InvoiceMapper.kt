package com.vpnreseller.core_data.mapper

import com.vpnreseller.core_data.local.entity.InvoiceEntity
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.InvoiceLineItem

/**
 * Mapper functions for converting between Invoice domain models and database entities.
 */
object InvoiceMapper {

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
            importedSheetData = importedSheetData
        )
    }

    fun InvoiceEntity.toDomain(): Invoice {
        return Invoice(
            id = id,
            representativeId = representativeId,
            generationDate = Date(generationDate),
            totalAmount = totalAmount,
            status = enumValueOf(status),
            isSentToTelegram = isSentToTelegram,
            pdfPath = pdfPath,
            imagePath = imagePath,
            importedSheetData = importedSheetData
        )
    }

    fun InvoiceLineItem.toEntity(invoiceId: String): InvoiceLineItemEntity {
        return InvoiceLineItemEntity(
            id = id,
            invoiceHeaderId = invoiceId,
            description = description,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice
        )
    }

    fun InvoiceLineItemEntity.toDomain(): InvoiceLineItem {
        return InvoiceLineItem(
            id = id,
            invoiceHeaderId = invoiceHeaderId,
            description = description,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice
        )
    }
}
