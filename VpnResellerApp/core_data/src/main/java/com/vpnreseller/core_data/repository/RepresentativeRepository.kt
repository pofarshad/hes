package com.vpnreseller.core_data.repository

import com.vpnreseller.core_domain.model.Representative
import com.vpnreseller.core_domain.model.RepresentativeFinancialSummary
import com.vpnreseller.core_domain.model.RepresentativeWithReferrals
import kotlinx.coroutines.flow.Flow

interface RepresentativeRepository {
    // Basic CRUD operations
    suspend fun createRepresentative(representative: Representative)
    suspend fun updateRepresentative(representative: Representative)
    suspend fun deleteRepresentative(representativeId: String)
    suspend fun getRepresentative(representativeId: String): Representative?
    
    // Bulk operations
    suspend fun createRepresentatives(representatives: List<Representative>)
    suspend fun deleteRepresentatives(representativeIds: List<String>)
    
    // Query operations
    fun getAllRepresentatives(): Flow<List<Representative>>
    fun getRepresentativesByStatus(status: String): Flow<List<Representative>>
    fun getRepresentativesByParent(parentRepId: String): Flow<List<Representative>>
    fun getRepresentativeWithReferrals(representativeId: String): Flow<RepresentativeWithReferrals?>
    
    // Financial operations
    suspend fun getFinancialSummary(representativeId: String): RepresentativeFinancialSummary
    suspend fun updatePricePerGb(representativeId: String, newPrice: Double)
    suspend fun updateDiscountPercentage(representativeId: String, newPercentage: Double)
    suspend fun toggleSpecialOffer(representativeId: String)
    
    // Status operations
    suspend fun updateStatus(representativeId: String, newStatus: String)
    suspend fun activateRepresentative(representativeId: String)
    suspend fun deactivateRepresentative(representativeId: String)
    
    // Validation operations
    suspend fun validateRepresentative(representative: Representative): Boolean
    suspend fun isAdminUsernameAvailable(username: String): Boolean
}
