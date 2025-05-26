package com.vpnreseller.core_domain.model

sealed class ImportUiState {
    object Idle : ImportUiState()
    object Loading : ImportUiState()
    object Parsing : ImportUiState()
    data class Success(val message: String) : ImportUiState()
    data class Error(val message: String) : ImportUiState()
}
