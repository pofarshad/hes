package com.yourcompany.vpnresellerapp.core_data.model

// import androidx.room.Entity
// import androidx.room.ForeignKey
// import androidx.room.Index
// import androidx.room.PrimaryKey

// @Entity(
//    tableName = "payments",
//    foreignKeys = [
//        ForeignKey(
//            entity = Representative::class,
//            parentColumns = ["id"],
//            childColumns = ["representativeId"],
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = Invoice::class,
//            parentColumns = ["id"],
//            childColumns = ["invoiceId"],
//            onDelete = ForeignKey.SET_NULL // A payment might exist even if an invoice is deleted, or link to rep directly
//        )
//    ],
//    indices = [Index(value = ["representativeId"]), Index(value = ["invoiceId"])]
// )
data class Payment(
    // @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val representativeId: Long, // Who made the payment
    val invoiceId: Long?,       // Optional: Which invoice this payment is for
    val amount: Double,
    val paymentDate: Long = System.currentTimeMillis(),
    val paymentMethod: String?, // e.g., "Cash", "Bank Transfer", "Card" - consider enum
    val notes: String?          // Any notes about the payment
)

// enum class PaymentMethod { CASH, BANK_TRANSFER, CARD, OTHER }
