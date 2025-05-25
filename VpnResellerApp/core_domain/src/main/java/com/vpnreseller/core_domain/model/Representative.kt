package com.vpnreseller.core_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

/**
 * Representative domain model
 * Represents a VPN reseller representative/customer
 */
@Parcelize
data class Representative(
    val id: String = UUID.randomUUID().toString(),
    val fullName: String,
    val adminUsernameMarzban: String,
    val telegramLink: String? = null,
    val pricePerGbLimited: Double,
    val monthlyUnlimitedPrice: Double,
    val discountType: DiscountType = DiscountType.NONE,
    val discountValue: Double = 0.0,
    val parentRepresentativeId: String? = null,
    val defaultSubscriptionType: SubscriptionType = SubscriptionType.LIMITED,
    val defaultDurationMonths: Int = 1,
    val isActive: Boolean = true
) : Parcelable

/**
 * Discount type enumeration
 */
enum class DiscountType {
    PERCENTAGE,
    TIME_BASED,
    NONE
}

/**
 * Subscription type enumeration
 */
enum class SubscriptionType {
    LIMITED,
    UNLIMITED
}
