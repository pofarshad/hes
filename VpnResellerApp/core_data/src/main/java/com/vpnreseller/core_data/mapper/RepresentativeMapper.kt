package com.vpnreseller.core_data.mapper

import com.vpnreseller.core_data.local.entity.RepresentativeEntity
import com.vpnreseller.core_domain.model.Representative

/**
 * Mapper functions for converting between Representative domain models and database entities.
 */
object RepresentativeMapper {

    fun Representative.toEntity(): RepresentativeEntity {
        return RepresentativeEntity(
            id = id,
            name = name,
            username = username,
            passwordHash = passwordHash,
            email = email,
            phone = phone,
            telegramId = telegramId,
            parentRepresentativeId = parentRepresentativeId,
            pricePerGb = pricePerGb,
            discountPercentage = discountPercentage,
            hasSpecialOffer = hasSpecialOffer,
            status = status.name,
            registrationDate = registrationDate.time,
            lastLoginDate = lastLoginDate?.time,
            isAdmin = isAdmin
        )
    }

    fun RepresentativeEntity.toDomain(): Representative {
        return Representative(
            id = id,
            name = name,
            username = username,
            passwordHash = passwordHash,
            email = email,
            phone = phone,
            telegramId = telegramId,
            parentRepresentativeId = parentRepresentativeId,
            pricePerGb = pricePerGb,
            discountPercentage = discountPercentage,
            hasSpecialOffer = hasSpecialOffer,
            status = enumValueOf(status),
            registrationDate = Date(registrationDate),
            lastLoginDate = lastLoginDate?.let { Date(it) },
            isAdmin = isAdmin
        )
    }
}
