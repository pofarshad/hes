package com.vpnreseller.core_domain.model

import java.util.Date

/**
 * Domain model for an invoice.
 * Represents a billing document for a representative.
 */
data class Invoice(
    val id: String,
    val representativeId: String,
    val generationDate: Date,
    val totalAmount: Double,
    val status: InvoiceStatus,
    val isSentToTelegram: Boolean,
    val pdfPath: String?,
    val imagePath: String?,
    val importedSheetData: Map<String, String>? = null // Metadata from import process
)

/**
 * Status of an invoice in the system.
 */
enum class InvoiceStatus {
    UNPAID,      // Invoice has been generated but not paid
    PARTIALLY_PAID, // Some payments have been made but full amount not received
    PAID,        // Invoice has been fully paid
    CANCELLED,   // Invoice has been cancelled (e.g., due to error)
    OVERDUE      // Payment deadline has passed
}

/**
 * Domain model for an invoice line item.
 * Represents a single item or service being billed in an invoice.
 */
data class InvoiceLineItem(
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
    fun isValid(): Boolean = totalPrice == quantity * unitPrice
}

/**
 * Represents an invoice with its associated line items.
 * Used when both the invoice header and its items are needed together.
 */
data class InvoiceWithItems(
    val invoice: Invoice,
    val lineItems: List<InvoiceLineItem>
) {
    /**
     * Validates that the invoice total matches the sum of line item totals
     */
    fun isValid(): Boolean {
        val lineItemsTotal = lineItems.sumOf { it.totalPrice }
        return invoice.totalAmount == lineItemsTotal &&
               lineItems.all { it.isValid() }
    }

    /**
     * Calculates any discounts applied to this invoice
     */
    fun getDiscountAmount(): Double {
        // TODO: Implement discount calculation based on representative's settings
        return 0.0
    }

    /**
     * Gets the final amount after applying discounts
     */
    fun getFinalAmount(): Double {
        return invoice.totalAmount - getDiscountAmount()
    }

    /**
     * Groups line items by type/category for reporting
     */
    fun groupLineItemsByType(): Map<String, List<InvoiceLineItem>> {
        return lineItems.groupBy { it.description }
    }

    /**
     * Gets statistics about the invoice
     */
    fun getStats(): InvoiceStats {
        return InvoiceStats(
            totalItems = lineItems.size,
            totalQuantity = lineItems.sumOf { it.quantity },
            averageUnitPrice = lineItems.map { it.unitPrice }.average(),
            highestLineItem = lineItems.maxByOrNull { it.totalPrice },
            lowestLineItem = lineItems.minByOrNull { it.totalPrice }
        )
    }
}

/**
 * Statistical information about an invoice
 */
data class InvoiceStats(
    val totalItems: Int,
    val totalQuantity: Int,
    val averageUnitPrice: Double,
    val highestLineItem: InvoiceLineItem?,
    val lowestLineItem: InvoiceLineItem?
)
