package com.vpnreseller.core_data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vpnreseller.core_data.local.dao.RepresentativeDao
import com.vpnreseller.core_data.local.entity.RepresentativeEntity
import com.vpnreseller.core_domain.model.Representative
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class RepresentativeRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var representativeDao: RepresentativeDao
    private lateinit var repository: RepresentativeRepositoryImpl

    private val sampleEntity = RepresentativeEntity(
        id = "1",
        fullName = "John Doe",
        adminUsernameMarzban = "johndoe",
        telegramLink = "https://t.me/johndoe",
        pricePerGbLimited = 10.0,
        monthlyUnlimitedPrice = 100.0,
        discountType = "NONE",
        discountValue = 0.0,
        parentRepresentativeId = null,
        defaultSubscriptionType = "LIMITED",
        defaultDurationMonths = 1,
        isActive = true,
        notes = "Sample notes"
    )

    private val sampleDomain = Representative(
        id = "1",
        fullName = "John Doe",
        adminUsernameMarzban = "johndoe",
        telegramLink = "https://t.me/johndoe",
        pricePerGbLimited = 10.0,
        monthlyUnlimitedPrice = 100.0,
        discountType = com.vpnreseller.core_domain.model.DiscountType.NONE,
        discountValue = 0.0,
        parentRepresentativeId = null,
        defaultSubscriptionType = com.vpnreseller.core_domain.model.SubscriptionType.LIMITED,
        defaultDurationMonths = 1,
        isActive = true,
        notes = "Sample notes"
    )

    @Before
    fun setup() {
        representativeDao = mock()
        repository = RepresentativeRepositoryImpl(representativeDao)
    }

    @Test
    fun getAllRepresentatives_returnsMappedList() = runTest {
        whenever(representativeDao.getAllRepresentatives()).thenReturn(flowOf(listOf(sampleEntity)))

        val result = repository.getAllRepresentatives()

        result.collect { list ->
            assertEquals(1, list.size)
            assertEquals(sampleDomain, list[0])
        }
    }

    @Test
    fun getRepresentativeById_returnsMappedEntity() = runTest {
        whenever(representativeDao.getRepresentativeById("1")).thenReturn(sampleEntity)

        val result = repository.getRepresentativeById("1")

        assertEquals(sampleDomain, result)
    }

    @Test
    fun insertRepresentative_callsDao() = runTest {
        repository.insertRepresentative(sampleDomain)

        verify(representativeDao).insertRepresentative(sampleEntity)
    }

    @Test
    fun updateRepresentative_callsDao() = runTest {
        repository.updateRepresentative(sampleDomain)

        verify(representativeDao).updateRepresentative(sampleEntity)
    }

    @Test
    fun deleteRepresentative_callsDao() = runTest {
        repository.deleteRepresentative(sampleDomain)

        verify(representativeDao).deleteRepresentative(sampleEntity)
    }

    @Test
    fun deleteRepresentativeById_callsDao() = runTest {
        repository.deleteRepresentativeById("1")

        verify(representativeDao).deleteRepresentativeById("1")
    }
}
