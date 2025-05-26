package com.vpnreseller.core_data.util

import com.vpnreseller.core_domain.model.SheetRow
import com.vpnreseller.core_domain.model.SheetLineItem
import com.vpnreseller.core_domain.model.SheetParsingException

/**
 * Utility class for parsing Google Sheet data into domain objects.
 */
object SheetParser {
    // Column indices for representative information (A-G)
    private const val COL_ADMIN_USERNAME = 0 // A
    private const val COL_REP_NAME = 1      // B
    private const val COL_TELEGRAM_ID = 2    // C
    private const val COL_PARENT_REP_ID = 3  // D
    private const val COL_PRICE_PER_GB = 4   // E
    private const val COL_DISCOUNT = 5       // F
    private const val COL_SPECIAL_OFFER = 6  // G

    // Column indices for line items (H-S)
    // Each line item takes 3 columns: Description, Quantity, Unit Price
    // Total Price is calculated
    private val LINE_ITEM_RANGES = listOf(
        7..9,   // H-J: Line Item 1
        10..12, // K-M: Line Item 2
        13..15, // N-P: Line Item 3
        16..18  // Q-S: Line Item 4
    )

    /**
     * Parses a row of data from the Google Sheet into a SheetRow object.
     * @param row List of values from the Google Sheet API
     * @return SheetRow object containing parsed data
     * @throws SheetParsingException if the data is invalid or cannot be parsed
     */
    fun parseRow(row: List<Any>): SheetRow {
        try {
            // Ensure we have enough columns
            if (row.size < 19) { // We need at least 19 columns (A-S)
                throw SheetParsingException("Row has insufficient columns: ${row.size}")
            }

            // Parse representative information
            val adminUsername = row[COL_ADMIN_USERNAME].toString()
            val repName = row[COL_REP_NAME].toString()
            val telegramId = row[COL_TELEGRAM_ID].toString().takeIf { it.isNotBlank() }
            val parentRepId = row[COL_PARENT_REP_ID].toString().takeIf { it.isNotBlank() }
            
            val pricePerGb = row[COL_PRICE_PER_GB].toString().toDoubleOrNull()
                ?: throw SheetParsingException("Invalid price per GB")
                
            val discount = row[COL_DISCOUNT].toString().toDoubleOrNull()
                ?: throw SheetParsingException("Invalid discount percentage")
                
            val specialOffer = row[COL_SPECIAL_OFFER].toString().equals("yes", ignoreCase = true)

            // Parse line items
            val lineItems = LINE_ITEM_RANGES.mapNotNull { range ->
                parseLineItem(row, range.first)
            }

            return SheetRow(
                adminUsername = adminUsername,
                representativeName = repName,
                telegramId = telegramId,
                parentRepId = parentRepId,
                pricePerGb = pricePerGb,
                discountPercentage = discount,
                specialOffer = specialOffer,
                lineItems = lineItems
            )
        } catch (e: Exception) {
            when (e) {
                is SheetParsingException -> throw e
                else -> throw SheetParsingException("Error parsing row: ${e.message}")
            }
        }
    }

    /**
     * Parses a line item from three consecutive columns.
     * @param row The full row of data
     * @param startIndex The index of the first column for this line item
     * @return SheetLineItem if the data is valid, null if the line item is empty
     */
    private fun parseLineItem(row: List<Any>, startIndex: Int): SheetLineItem? {
        val description = row[startIndex].toString()
        if (description.isBlank()) return null // Skip empty line items

        val quantity = row[startIndex + 1].toString().toIntOrNull()
            ?: throw SheetParsingException("Invalid quantity at column ${startIndex + 1}")

        val unitPrice = row[startIndex + 2].toString().toDoubleOrNull()
            ?: throw SheetParsingException("Invalid unit price at column ${startIndex + 2}")

        val totalPrice = quantity * unitPrice

        return SheetLineItem(
            description = description,
            quantity = quantity,
            unitPrice = unitPrice,
            totalPrice = totalPrice
        )
    }

    /**
     * Validates a complete SheetRow object.
     * @throws SheetParsingException if validation fails
     */
    fun validateRow(row: SheetRow) {
        if (row.adminUsername.isBlank()) {
            throw SheetParsingException("Admin username cannot be empty")
        }
        if (row.representativeName.isBlank()) {
            throw SheetParsingException("Representative name cannot be empty")
        }
        if (row.pricePerGb <= 0) {
            throw SheetParsingException("Price per GB must be positive")
        }
        if (row.discountPercentage < 0 || row.discountPercentage > 100) {
            throw SheetParsingException("Discount percentage must be between 0 and 100")
        }
        if (row.lineItems.isEmpty()) {
            throw SheetParsingException("At least one line item is required")
        }
        row.lineItems.forEach { lineItem ->
            if (!lineItem.isValid()) {
                throw SheetParsingException("Line item total price does not match quantity * unit price")
            }
        }
    }
}
