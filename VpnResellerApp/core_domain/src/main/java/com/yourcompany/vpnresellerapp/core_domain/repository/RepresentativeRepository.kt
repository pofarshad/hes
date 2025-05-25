package com.yourcompany.vpnresellerapp.core_domain.repository

// Assuming Representative model is defined in core_data and core_domain has a dependency or its own version
// For now, let's assume we might use a domain-specific model or reuse core_data's model if core_domain depends on core_data.
// If core_domain defines its own models, core_data would map to them.
// For simplicity, let's imagine it uses a type `DomainRepresentative` which could be similar to core_data.Representative
// or core_data.Representative itself if core_domain depends on core_data.
// To avoid circular dependencies and keep domain clean, it's better if domain defines its own models or core_data models are very generic.

// For now, let's use a placeholder. We will refine this when connecting modules.
// import com.yourcompany.vpnresellerapp.core_data.model.Representative // If core_domain depends on core_data
import kotlinx.coroutines.flow.Flow

// Placeholder for a domain-specific representative model if needed.
// data class DomainRepresentative(val id: Long, val name: String, /* ... other domain relevant fields */)

interface RepresentativeRepository {

    // fun getRepresentatives(): Flow<List<DomainRepresentative>> // Example with domain model

    // fun getRepresentativeById(id: Long): Flow<DomainRepresentative?>

    // suspend fun addRepresentative(representative: DomainRepresentative)

    // suspend fun updateRepresentative(representative: DomainRepresentative)

    // suspend fun deleteRepresentative(id: Long)

    // For now, to avoid compilation errors without the actual model from core_data yet,
    // I'll use placeholders or keep it very generic.
    // Let's assume for now that core_domain will eventually get access to core_data models
    // or define its own and core_data will adapt.

    // Placeholder functions - to be defined properly with models later
    fun getRepresentatives(): Flow<List<Any>> // Replace 'Any' with actual Representative model

    fun getRepresentativeById(id: Long): Flow<Any?> // Replace 'Any'

    suspend fun addRepresentative(representative: Any) // Replace 'Any'

    suspend fun updateRepresentative(representative: Any) // Replace 'Any'

    suspend fun deleteRepresentative(id: Long)
}
