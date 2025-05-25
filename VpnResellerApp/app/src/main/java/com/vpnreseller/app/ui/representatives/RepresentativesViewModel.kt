package com.vpnreseller.app.ui.representatives

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpnreseller.core_domain.model.Representative
import com.vpnreseller.core_data.repository.RepresentativeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce // Import debounce
import kotlinx.coroutines.flow.flatMapLatest // Import flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepresentativesViewModel @Inject constructor(
    private val representativeRepository: RepresentativeRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Efficiently fetches representatives based on search query
    val representatives: StateFlow<List<Representative>> = _searchQuery
        .debounce(300) // Add a debounce to avoid too many queries while typing
        .flatMapLatest { query ->
            if (query.isBlank()) {
                representativeRepository.getAllRepresentatives()
            } else {
                representativeRepository.searchRepresentatives(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // For Add/Edit screen, to hold the representative being edited or null for new
    private val _selectedRepresentative = MutableStateFlow<Representative?>(null)
    val selectedRepresentative: StateFlow<Representative?> = _selectedRepresentative.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun loadRepresentativeDetails(id: String?) {
        if (id == null) {
            _selectedRepresentative.value = null // For adding a new representative
            return
        }
        viewModelScope.launch {
            _selectedRepresentative.value = representativeRepository.getRepresentativeById(id)
        }
    }
    
    fun clearSelectedRepresentative() {
        _selectedRepresentative.value = null
    }

    fun saveRepresentative(representative: Representative) {
        viewModelScope.launch {
            // Check if representative with this ID already exists to decide insert vs update
            val existingRep = representativeRepository.getRepresentativeById(representative.id)
            if (existingRep != null) {
                representativeRepository.updateRepresentative(representative)
            } else {
                // This case implies a new representative, ensure ID is unique if not already handled by domain model
                representativeRepository.insertRepresentative(representative)
            }
            clearSelectedRepresentative() // Clear selection after save
        }
    }

    fun deleteRepresentative(representative: Representative) {
        viewModelScope.launch {
            representativeRepository.deleteRepresentative(representative)
            clearSelectedRepresentative() // Clear selection after delete
        }
    }
     fun deleteRepresentativeById(id: String) {
        viewModelScope.launch {
            representativeRepository.deleteRepresentativeById(id)
            if (_selectedRepresentative.value?.id == id) {
                 clearSelectedRepresentative()
            }
        }
    }
}
