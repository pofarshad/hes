package com.yourcompany.vpnresellerapp.core_data.model

// import androidx.room.Entity
// import androidx.room.ForeignKey
// import androidx.room.Index
// import androidx.room.PrimaryKey

// @Entity(
//    tableName = "invoices",
//    foreignKeys = [
//        ForeignKey(
//            entity = Representative::class,
//            parentColumns = ["id"],
//            childColumns = ["representativeId"],
//            onDelete = ForeignKey.CASCADE // Or SET_NULL, RESTRICT as per business logic
//        )
//    ],
//    indices = [Index(value = ["representativeId"])]
// )
data class Invoice(
    // @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val representativeId: Long, // Foreign key to Representative

    // Data from Google Sheet (Schema as provided)
    val adminUsername: String,       // Column A
    val userUsername: String,        // Column B
    val totalResets: Int?,           // Column C
    val totalDataLimitGib: Double?,  // Column D
    val totalSubscriptionMonths: Int?,// Column E (Assuming this is total duration in months)

    val limitedSubscriptionCount: Int?, // Column F
    val unlimitedSubscriptionCount: Int?,// Column G

    // Limited subscription breakdown (H-M)
    val limited1Month: Int?,
    val limited2Month: Int?,
    val limited3Month: Int?,
    val limited4Month: Int?,
    val limited5Month: Int?,
    val limited6Month: Int?,

    // Unlimited subscription breakdown (N-S)
    val unlimited1Month: Int?,
    val unlimited2Month: Int?,
    val unlimited3Month: Int?,
    val unlimited4Month: Int?,
    val unlimited5Month: Int?,
    val unlimited6Month: Int?,

    // Columns T-Y were mentioned as "duplicate breakdown of unlimited subscriptions"
    // Clarification might be needed if they are different or can be omitted.
    // For now, I'm omitting them to avoid redundancy unless specified otherwise.

    val totalPrice: Double,          // Column Z

    // Invoice specific details
    val generationDate: Long = System.currentTimeMillis(),
    var status: String,              // e.g., "Generated", "Sent", "Unpaid", "Partial", "Paid" - consider enum
    var amountPaid: Double = 0.0,
    val dueDate: Long?,              // Optional due date for the invoice
    val exportedImagePath: String?,  // Path to the exported image
    val exportedPdfPath: String?,    // Path to the exported PDF
    val telegramSentStatus: Boolean = false // "Sent" / "Not Sent" for Telegram
)

// enum class InvoiceStatus { GENERATED, SENT_TELEGRAM, UNPAID, PARTIALLY_PAID, FULLY_PAID, OVERDUE, CANCELLED }
