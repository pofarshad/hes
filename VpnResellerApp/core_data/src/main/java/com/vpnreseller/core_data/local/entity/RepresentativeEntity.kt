package com.vpnreseller.core_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vpnreseller.core_domain.model.DiscountType
import com.vpnreseller.core_domain.model.Representative
import com.vpnreseller.core_domain.model.SubscriptionType

/**
 * Room entity for Representative
 * Maps domain model to database table
 */
@Entity(tableName = "representatives")
data class RepresentativeEntity(
    @PrimaryKey
    val id: String,
    val fullName: String,
    val adminUsernameMarzban: String,
    val telegramLink: String?,
    val pricePerGbLimited: Double,
    val monthlyUnlimitedPrice: Double,
    val discountType: String,
    val discountValue: Double,
    val parentRepresentativeId: String?,
    val defaultSubscriptionType: String,
    val defaultDurationMonths: Int,
    val isActive: Boolean
)

/**
 * Extension function to convert RepresentativeEntity to Representative domain model
 */
fun RepresentativeEntity.toDomainModel(): Representative {
    return Representative(
        id = id,
        fullName = fullName,
        adminUsernameMarzban = adminUsernameMarzban,
        telegramLink = telegramLink,
        pricePerGbLimited = pricePerGbLimited,
        monthlyUnlimitedPrice = monthlyUnlimitedPrice,
        discountType = DiscountType.valueOf(discountType),
        discountValue = discountValue,
        parentRepresentativeId = parentRepresentativeId,
        defaultSubscriptionType = SubscriptionType.valueOf(defaultSubscriptionType),
        defaultDurationMonths = defaultDurationMonths,
        isActive = isActive
    )
}

/**
 * Extension function to convert Representative domain model to RepresentativeEntity
 */
fun Representative.toEntity(): RepresentativeEntity {
    return RepresentativeEntity(
        id = id,
        fullName = fullName,
        adminUsernameMarzban = adminUsernameMarzban,
        telegramLink = telegramLink,
        pricePerGbLimited = pricePerGbLimited,
        monthlyUnlimitedPrice = monthlyUnlimitedPrice,
        discountType = discountType.name,
        discountValue = discountValue,
        parentRepresentativeId = parentRepresentativeId,
        defaultSubscriptionType = defaultSubscriptionType.name,
        defaultDurationMonths = defaultDurationMonths,
        isActive = isActive
    )
}
