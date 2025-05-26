package com.vpnreseller.core_domain.model

/**
 * Domain model for a VPN reseller representative.
 * Represents a person who resells VPN services and receives invoices.
 */
data class Representative(
    val id: String,
    val adminUsername: String, // Username in the Marzban panel
    val name: String,         // Full name or business name
    val telegramId: String?,  // Optional Telegram username/ID for notifications
    val parentRepId: String?, // ID of the representative who introduced this rep (for MLM)
    val pricePerGb: Double,   // Base price per GB for this representative
    val discountPercentage: Double, // Standard discount percentage
    val specialOffer: Boolean,      // Whether this rep has special pricing
    val status: RepresentativeStatus = RepresentativeStatus.ACTIVE,
    val metadata: Map<String, String>? = null // Additional flexible data storage
) {
    /**
     * Calculates the final price per GB after applying discounts
     */
    fun calculateFinalPricePerGb(): Double {
        val discountMultiplier = 1 - (discountPercentage / 100)
        return pricePerGb * discountMultiplier
    }

    /**
     * Checks if this representative is eligible for special pricing
     */
    fun isEligibleForSpecialPricing(): Boolean {
        return specialOffer || status == RepresentativeStatus.VIP
    }

    /**
     * Gets the Telegram sharing link for this representative
     */
    fun getTelegramLink(): String? {
        return telegramId?.let { "https://t.me/$it" }
    }
}

/**
 * Status of a representative in the system
 */
enum class RepresentativeStatus {
    ACTIVE,      // Regular active representative
    INACTIVE,    // Temporarily inactive
    SUSPENDED,   // Suspended due to policy violations
    VIP,         // VIP representative with special privileges
    TRIAL        // New representative in trial period
}

/**
 * Represents a representative with their referral network
 */
data class RepresentativeWithReferrals(
    val representative: Representative,
    val referrals: List<Representative> // Representatives referred by this rep
) {
    /**
     * Calculates total earnings from referrals
     */
    fun calculateReferralEarnings(referralCommissionPercentage: Double): Double {
        // TODO: Implement actual referral earnings calculation based on
        // referred representatives' invoice totals
        return 0.0
    }

    /**
     * Gets statistics about the representative's referral network
     */
    fun getReferralStats(): ReferralStats {
        return ReferralStats(
            totalReferrals = referrals.size,
            activeReferrals = referrals.count { it.status == RepresentativeStatus.ACTIVE },
            vipReferrals = referrals.count { it.status == RepresentativeStatus.VIP },
            averageReferralDiscount = referrals.map { it.discountPercentage }.average()
        )
    }
}

/**
 * Statistical information about a representative's referral network
 */
data class ReferralStats(
    val totalReferrals: Int,
    val activeReferrals: Int,
    val vipReferrals: Int,
    val averageReferralDiscount: Double
)

/**
 * Represents a representative's financial summary
 */
data class RepresentativeFinancialSummary(
    val representative: Representative,
    val totalInvoiced: Double,
    val totalPaid: Double,
    val outstandingBalance: Double,
    val referralEarnings: Double,
    val lastPaymentDate: java.util.Date?
) {
    /**
     * Determines if the representative is in good financial standing
     */
    fun isInGoodStanding(): Boolean {
        return outstandingBalance <= 0
    }

    /**
     * Gets the payment status category
     */
    fun getPaymentStatus(): PaymentStatus {
        return when {
            outstandingBalance <= 0 -> PaymentStatus.PAID
            outstandingBalance < totalInvoiced * 0.5 -> PaymentStatus.PARTIALLY_PAID
            else -> PaymentStatus.UNPAID
        }
    }
}

/**
 * Payment status categories
 */
enum class PaymentStatus {
    PAID,
    PARTIALLY_PAID,
    UNPAID
}
