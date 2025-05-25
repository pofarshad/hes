package com.vpnreseller.core_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vpnreseller.core_data.local.entity.RepresentativeEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Representative operations
 */
@Dao
interface RepresentativeDao {
    
    /**
     * Get all representatives as Flow for reactive updates
     */
    @Query("SELECT * FROM representatives ORDER BY fullName ASC")
    fun getAllRepresentatives(): Flow<List<RepresentativeEntity>>
    
    /**
     * Get all active representatives
     */
    @Query("SELECT * FROM representatives WHERE isActive = 1 ORDER BY fullName ASC")
    fun getActiveRepresentatives(): Flow<List<RepresentativeEntity>>
    
    /**
     * Get representative by ID
     */
    @Query("SELECT * FROM representatives WHERE id = :id")
    suspend fun getRepresentativeById(id: String): RepresentativeEntity?
    
    /**
     * Get representatives by parent ID (for hierarchy)
     */
    @Query("SELECT * FROM representatives WHERE parentRepresentativeId = :parentId ORDER BY fullName ASC")
    fun getRepresentativesByParent(parentId: String): Flow<List<RepresentativeEntity>>
    
    /**
     * Search representatives by name or username
     */
    @Query("SELECT * FROM representatives WHERE fullName LIKE '%' || :query || '%' OR adminUsernameMarzban LIKE '%' || :query || '%' ORDER BY fullName ASC")
    fun searchRepresentatives(query: String): Flow<List<RepresentativeEntity>>
    
    /**
     * Insert new representative
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepresentative(representative: RepresentativeEntity)
    
    /**
     * Insert multiple representatives
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepresentatives(representatives: List<RepresentativeEntity>)
    
    /**
     * Update representative
     */
    @Update
    suspend fun updateRepresentative(representative: RepresentativeEntity)
    
    /**
     * Delete representative
     */
    @Delete
    suspend fun deleteRepresentative(representative: RepresentativeEntity)
    
    /**
     * Delete representative by ID
     */
    @Query("DELETE FROM representatives WHERE id = :id")
    suspend fun deleteRepresentativeById(id: String)
    
    /**
     * Get count of all representatives
     */
    @Query("SELECT COUNT(*) FROM representatives")
    suspend fun getRepresentativeCount(): Int
    
    /**
     * Get count of active representatives
     */
    @Query("SELECT COUNT(*) FROM representatives WHERE isActive = 1")
    suspend fun getActiveRepresentativeCount(): Int
}
