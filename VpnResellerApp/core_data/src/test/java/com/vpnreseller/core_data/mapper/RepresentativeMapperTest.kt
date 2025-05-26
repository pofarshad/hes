package com.vpnreseller.core_data.mapper

import com.vpnreseller.core_data.local.entity.RepresentativeEntity
import com.vpnreseller.core_domain.model.Representative
import com.vpnreseller.core_domain.model.RepresentativeStatus
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class RepresentativeMapperTest {

    @Test
    fun `toEntity converts domain model to entity correctly`() {
        // Given
        val domainModel = Representative(
            id = "1",
            name = "Test Rep",
            username = "testuser",
            passwordHash = "hash",
            email = "test@example.com",
            phone = "1234567890",
            telegramId = "telegram123",
            parentRepresentativeId = null,
            pricePerGb = 5.0,
            discountPercentage = 10.0,
            hasSpecialOffer = false,
            status = RepresentativeStatus.ACTIVE,
            registrationDate = Date(1672531200000), // Jan 1, 2023
            lastLoginDate = Date(1672617600000), // Jan 2, 2023
            isAdmin = false
        )

        // When
        val entity = RepresentativeMapper.toEntity(domainModel)

        // Then
        assertEquals("1", entity.id)
        assertEquals("Test Rep", entity.name)
        assertEquals("testuser", entity.username)
        assertEquals("hash", entity.passwordHash)
        assertEquals("test@example.com", entity.email)
        assertEquals("1234567890", entity.phone)
        assertEquals("telegram123", entity.telegramId)
        assertNull(entity.parentRepresentativeId)
        assertEquals(5.0, entity.pricePerGb, 0.001)
        assertEquals(10.0, entity.discountPercentage, 0.001)
        assertFalse(entity.hasSpecialOffer)
        assertEquals("ACTIVE", entity.status)
        assertEquals(1672531200000, entity.registrationDate)
        assertEquals(1672617600000, entity.lastLoginDate)
        assertFalse(entity.isAdmin)
    }

    @Test
    fun `toDomain converts entity to domain model correctly`() {
        // Given
        val entity = RepresentativeEntity(
            id = "1",
            name = "Test Rep",
            username = "testuser",
            passwordHash = "hash",
            email = "test@example.com",
            phone = "1234567890",
            telegramId = "telegram123",
            parentRepresentativeId = null,
            pricePerGb = 5.0,
            discountPercentage = 10.0,
            hasSpecialOffer = false,
            status = "ACTIVE",
            registrationDate = 1672531200000, // Jan 1, 2023
            lastLoginDate = 1672617600000, // Jan 2, 2023
            isAdmin = false
        )

        // When
        val domainModel = RepresentativeMapper.toDomain(entity)

        // Then
        assertEquals("1", domainModel.id)
        assertEquals("Test Rep", domainModel.name)
        assertEquals("testuser", domainModel.username)
        assertEquals("hash", domainModel.passwordHash)
        assertEquals("test@example.com", domainModel.email)
        assertEquals("1234567890", domainModel.phone)
        assertEquals("telegram123", domainModel.telegramId)
        assertNull(domainModel.parentRepresentativeId)
        assertEquals(5.0, domainModel.pricePerGb, 0.001)
        assertEquals(10.0, domainModel.discountPercentage, 0.001)
        assertFalse(domainModel.hasSpecialOffer)
        assertEquals(RepresentativeStatus.ACTIVE, domainModel.status)
        assertEquals(Date(1672531200000), domainModel.registrationDate)
        assertEquals(Date(1672617600000), domainModel.lastLoginDate)
        assertFalse(domainModel.isAdmin)
    }

    @Test
    fun `toDomain handles null lastLoginDate correctly`() {
        // Given
        val entity = RepresentativeEntity(
            id = "1",
            name = "Test Rep",
            username = "testuser",
            passwordHash = "hash",
            email = "test@example.com",
            phone = "1234567890",
            telegramId = "telegram123",
            parentRepresentativeId = null,
            pricePerGb = 5.0,
            discountPercentage = 10.0,
            hasSpecialOffer = false,
            status = "ACTIVE",
            registrationDate = 1672531200000,
            lastLoginDate = null,
            isAdmin = false
        )

        // When
        val domainModel = RepresentativeMapper.toDomain(entity)

        // Then
        assertNull(domainModel.lastLoginDate)
    }
}
