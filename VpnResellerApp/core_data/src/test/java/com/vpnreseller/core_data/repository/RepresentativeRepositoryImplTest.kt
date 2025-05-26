package com.vpnreseller.core_data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vpnreseller.core_data.local.VpnResellerDatabase
import com.vpnreseller.core_data.local.dao.RepresentativeDao
import com.vpnreseller.core_data.local.entity.RepresentativeEntity
import com.vpnreseller.core_domain.model.Representative
import com.vpnreseller.core_domain.model.RepresentativeStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class RepresentativeRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: VpnResellerDatabase
    private lateinit var representativeDao: RepresentativeDao
    private lateinit var repository: RepresentativeRepositoryImpl

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            VpnResellerDatabase::class.java
        ).allowMainThreadQueries().build()

        representativeDao = database.representativeDao()
        repository = RepresentativeRepositoryImpl(
            representativeDao,
            mockInvoiceRepository()
        )
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun createAndGetRepresentative() = runTest {
        // Given
        val testRep = createTestRepresentative()

        // When
        repository.createRepresentative(testRep)
        val retrieved = repository.getRepresentative(testRep.id)

        // Then
        assertNotNull(retrieved)
        assertEquals(testRep.id, retrieved?.id)
        assertEquals(testRep.name, retrieved?.name)
    }

    @Test
    fun updateRepresentative() = runTest {
        // Given
        val testRep = createTestRepresentative()
        repository.createRepresentative(testRep)

        // When
        val updatedRep = testRep.copy(name = "Updated Name")
        repository.updateRepresentative(updatedRep)
        val retrieved = repository.getRepresentative(testRep.id)

        // Then
        assertEquals("Updated Name", retrieved?.name)
    }

    @Test
    fun deleteRepresentative() = runTest {
        // Given
        val testRep = createTestRepresentative()
        repository.createRepresentative(testRep)

        // When
        repository.deleteRepresentative(testRep.id)
        val retrieved = repository.getRepresentative(testRep.id)

        // Then
        assertNull(retrieved)
    }

    @Test
    fun getAllRepresentatives() = runTest {
        // Given
        val reps = listOf(
            createTestRepresentative("1"),
            createTestRepresentative("2"),
            createTestRepresentative("3")
        )
        reps.forEach { repository.createRepresentative(it) }

        // When
        val allReps = repository.getAllRepresentatives()

        // Then
        allReps.collect { list ->
            assertEquals(3, list.size)
            assertTrue(list.any { it.id == "1" })
            assertTrue(list.any { it.id == "2" })
            assertTrue(list.any { it.id == "3" })
        }
    }

    @Test
    fun getFinancialSummary() = runTest {
        // Given
        val testRep = createTestRepresentative()
        repository.createRepresentative(testRep)

        // When
        val summary = repository.getFinancialSummary(testRep.id)

        // Then
        assertEquals(testRep.id, summary.representativeId)
        // More assertions would depend on mockInvoiceRepository implementation
    }

    private fun createTestRepresentative(id: String = UUID.randomUUID().toString()): Representative {
        return Representative(
            id = id,
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
            registrationDate = Date(),
            lastLoginDate = null,
            isAdmin = false
        )
    }

    private fun mockInvoiceRepository(): InvoiceRepository {
        return object : InvoiceRepository {
            override fun calculateTotalOutstanding(representativeId: String) = 100.0
            override fun calculateTotalPaid(representativeId: String) = 200.0
            // Implement other required methods with mock returns
        }
    }
}
