package com.yourcompany.vpnresellerapp.core_data.model

// import androidx.room.Entity // To be uncommented when Room setup is finalized
// import androidx.room.PrimaryKey // To be uncommented

// @Entity(tableName = "representatives") // To be uncommented
data class Representative(
    // @PrimaryKey(autoGenerate = true) // To be uncommented
    val id: Long = 0,
    val fullName: String,
    val adminUsername: String, // Marzban panel username
    val telegramLink: String?, // Deep-link to contact
    val pricePerGbLimited: Double?, // Price per GB for limited subscriptions
    val monthlyUnlimitedPrice: Double?, // Monthly unlimited subscription price

    // Discount types: percentage-based or time-based
    // This could be an enum or a sealed class for more structure
    val discountType: String?, // e.g., "percentage", "time_based", "none"
    val discountValue: Double?, // e.g., 10.0 for 10% or 7 for 7 days free

    val referralParentId: Long?, // ID of the parent representative, e.g., "Benyamin" (would be an ID)
    val subscriptionType: String, // e.g., "limited", "unlimited" - consider an enum
    val subscriptionDurationMonths: Int, // 1-6 months

    // Additional fields that might be useful
    val isActive: Boolean = true,
    val registrationDate: Long = System.currentTimeMillis(), // Store as timestamp
    val internalNotes: String? = null // For operator notes
)

// Consider Enums for structured fields like discountType, subscriptionType
// enum class DiscountType { PERCENTAGE, TIME_BASED, NONE }
// enum class SubscriptionModelType { LIMITED, UNLIMITED }
