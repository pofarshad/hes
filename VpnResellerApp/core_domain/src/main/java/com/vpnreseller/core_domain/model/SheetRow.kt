package com.vpnreseller.core_domain.model

/**
 * Represents a row from the imported Google Sheet.
 * Maps to the columns as specified in the requirements.
 */
data class SheetRow(
    // Representative Information (Columns A-G)
    val adminUsername: String,
    val representativeName: String,
    val telegramId: String?,
    val parentRepId: String?,
    val pricePerGb: Double,
    val discountPercentage: Double,
    val specialOffer: Boolean,
    
    // Line Items (Columns H-S)
    val lineItems: List<SheetLineItem>
)

/**
 * Represents a single line item from the sheet.
 * Each line item corresponds to a service or product being billed.
 */
data class SheetLineItem(
    val description: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double // Should equal quantity * unitPrice
) {
    fun isValid(): Boolean = totalPrice == quantity * unitPrice
}

/**
 * Exception thrown when sheet data is invalid or cannot be parsed.
 */
class SheetParsingException(message: String) : Exception(message)
