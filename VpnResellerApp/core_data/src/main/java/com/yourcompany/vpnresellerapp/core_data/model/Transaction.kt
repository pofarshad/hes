package com.yourcompany.vpnresellerapp.core_data.model

// import androidx.room.Entity
// import androidx.room.ForeignKey
// import androidx.room.Index
// import androidx.room.PrimaryKey

// @Entity(
//    tableName = "transactions",
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
//            childColumns = ["relatedInvoiceId"],
//            onDelete = ForeignKey.SET_NULL // Transaction might be general, not always invoice-specific
//        ),
//        ForeignKey(
//            entity = Payment::class,
//            parentColumns = ["id"],
//            childColumns = ["relatedPaymentId"],
//            onDelete = ForeignKey.SET_NULL
//        )
//    ],
//    indices = [
//        Index(value = ["representativeId"]),
//        Index(value = ["relatedInvoiceId"]),
//        Index(value = ["relatedPaymentId"])
//    ]
// )
data class Transaction(
    // @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val representativeId: Long, // The representative this transaction is for
    val transactionDate: Long = System.currentTimeMillis(),
    val type: String,           // e.g., "InvoiceGenerated", "PaymentReceived", "CreditAdded", "Refund" - consider enum
    val description: String,    // Detailed description of the transaction
    val amount: Double,         // Can be positive (income/credit) or negative (expense/debit)
    val relatedInvoiceId: Long?,// Optional: Link to an invoice
    val relatedPaymentId: Long?,// Optional: Link to a payment
    val openingBalance: Double, // Balance before this transaction
    val closingBalance: Double  // Balance after this transaction
)

// enum class TransactionType {
//    INVOICE_GENERATED, // Amount might be invoice total (negative impact on rep balance until paid)
//    PAYMENT_RECEIVED,  // Amount is payment value (positive impact)
//    MANUAL_CREDIT,     // Admin adds credit
//    MANUAL_DEBIT,      // Admin adds debit/charge
//    REFUND_ISSUED,
//    OTHER
// }
