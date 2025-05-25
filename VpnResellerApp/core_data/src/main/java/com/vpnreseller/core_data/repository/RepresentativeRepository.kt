package com.vpnreseller.core_data.repository

import com.vpnreseller.core_data.local.dao.RepresentativeDao
import com.vpnreseller.core_data.local.entity.toDomainModel
import com.vpnreseller.core_data.local.entity.toEntity
import com.vpnreseller.core_domain.model.Representative
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface RepresentativeRepository {
    fun getAllRepresentatives(): Flow<List<Representative>>
    fun getActiveRepresentatives(): Flow<List<Representative>>
    suspend fun getRepresentativeById(id: String): Representative?
    fun getRepresentativesByParent(parentId: String): Flow<List<Representative>>
    fun searchRepresentatives(query: String): Flow<List<Representative>>
    suspend fun insertRepresentative(representative: Representative)
    suspend fun updateRepresentative(representative: Representative)
    suspend fun deleteRepresentative(representative: Representative)
    suspend fun deleteRepresentativeById(id: String)
    suspend fun getRepresentativeCount(): Int
    suspend fun getActiveRepresentativeCount(): Int
}

@Singleton
class RepresentativeRepositoryImpl @Inject constructor(
    private val representativeDao: RepresentativeDao
) : RepresentativeRepository {

    override fun getAllRepresentatives(): Flow<List<Representative>> {
        return representativeDao.getAllRepresentatives().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getActiveRepresentatives(): Flow<List<Representative>> {
        return representativeDao.getActiveRepresentatives().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun getRepresentativeById(id: String): Representative? {
        return representativeDao.getRepresentativeById(id)?.toDomainModel()
    }

    override fun getRepresentativesByParent(parentId: String): Flow<List<Representative>> {
        return representativeDao.getRepresentativesByParent(parentId).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun searchRepresentatives(query: String): Flow<List<Representative>> {
        return representativeDao.searchRepresentatives(query).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun insertRepresentative(representative: Representative) {
        representativeDao.insertRepresentative(representative.toEntity())
    }

    override suspend fun updateRepresentative(representative: Representative) {
        representativeDao.updateRepresentative(representative.toEntity())
    }

    override suspend fun deleteRepresentative(representative: Representative) {
        representativeDao.deleteRepresentative(representative.toEntity())
    }

    override suspend fun deleteRepresentativeById(id: String) {
        representativeDao.deleteRepresentativeById(id)
    }
    
    override suspend fun getRepresentativeCount(): Int {
        return representativeDao.getRepresentativeCount()
    }

    override suspend fun getActiveRepresentativeCount(): Int {
        return representativeDao.getActiveRepresentativeCount()
    }
}
