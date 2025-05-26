package com.vpnreseller.core_data.repository

import com.vpnreseller.core_data.local.dao.RepresentativeDao
import com.vpnreseller.core_data.mapper.toDomain
import com.vpnreseller.core_data.mapper.toEntity
import com.vpnreseller.core_domain.model.Representative
import com.vpnreseller.core_domain.model.RepresentativeFinancialSummary
import com.vpnreseller.core_domain.model.RepresentativeWithReferrals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepresentativeRepositoryImpl @Inject constructor(
    private val representativeDao: RepresentativeDao,
    private val invoiceRepository: InvoiceRepository
) : RepresentativeRepository {

    override suspend fun createRepresentative(representative: Representative) {
        representativeDao.insert(representative.toEntity())
    }

    override suspend fun updateRepresentative(representative: Representative) {
        representativeDao.update(representative.toEntity())
    }

    override suspend fun deleteRepresentative(representativeId: String) {
        representativeDao.deleteById(representativeId)
    }

    override suspend fun getRepresentative(representativeId: String): Representative? {
        return representativeDao.getById(representativeId)?.toDomain()
    }

    override suspend fun createRepresentatives(representatives: List<Representative>) {
        representativeDao.insertAll(representatives.map { it.toEntity() })
    }

    override suspend fun deleteRepresentatives(representativeIds: List<String>) {
        representativeIds.forEach { id -> representativeDao.deleteById(id) }
    }

    override fun getAllRepresentatives(): Flow<List<Representative>> {
        return representativeDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getRepresentativesByStatus(status: String): Flow<List<Representative>> {
        return representativeDao.getByStatus(status).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getRepresentativesByParent(parentRepId: String): Flow<List<Representative>> {
        return representativeDao.getByParentId(parentRepId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getRepresentativeWithReferrals(representativeId: String): Flow<RepresentativeWithReferrals?> {
        return representativeDao.getWithReferrals(representativeId).map { entityWithReferrals ->
            entityWithReferrals?.let {
                RepresentativeWithReferrals(
                    representative = it.representative.toDomain(),
                    referrals = it.referrals.map { ref -> ref.toDomain() }
                )
            }
        }
    }

    override suspend fun getFinancialSummary(representativeId: String): RepresentativeFinancialSummary {
        val totalOutstanding = invoiceRepository.calculateTotalOutstanding(representativeId)
        val totalPaid = invoiceRepository.calculateTotalPaid(representativeId)
        return RepresentativeFinancialSummary(
            representativeId = representativeId,
            totalOutstanding = totalOutstanding,
            totalPaid = totalPaid,
            netEarnings = totalPaid * 0.8 // Assuming 20% commission
        )
    }

    override suspend fun updatePricePerGb(representativeId: String, newPrice: Double) {
        representativeDao.updatePricePerGb(representativeId, newPrice)
    }

    override suspend fun updateDiscountPercentage(representativeId: String, newPercentage: Double) {
        representativeDao.updateDiscountPercentage(representativeId, newPercentage)
    }

    override suspend fun toggleSpecialOffer(representativeId: String) {
        representativeDao.toggleSpecialOffer(representativeId)
    }

    override suspend fun updateStatus(representativeId: String, newStatus: String) {
        representativeDao.updateStatus(representativeId, newStatus)
    }

    override suspend fun activateRepresentative(representativeId: String) {
        representativeDao.updateStatus(representativeId, "ACTIVE")
    }

    override suspend fun deactivateRepresentative(representativeId: String) {
        representativeDao.updateStatus(representativeId, "INACTIVE")
    }

    override suspend fun validateRepresentative(representative: Representative): Boolean {
        // TODO: Implement validation logic
        return true
    }

    override suspend fun isAdminUsernameAvailable(username: String): Boolean {
        return representativeDao.getByUsername(username) == null
    }
}
