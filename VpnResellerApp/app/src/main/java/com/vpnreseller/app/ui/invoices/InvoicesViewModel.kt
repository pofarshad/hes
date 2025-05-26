package com.vpnreseller.app.ui.invoices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.ImportUiState
import com.vpnreseller.core_data.repository.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoicesViewModel @Inject constructor(
    private val invoiceRepository: InvoiceRepository
) : ViewModel() {

    private val _importState = MutableStateFlow<ImportUiState>(ImportUiState.Idle)
    val importState: StateFlow<ImportUiState> = _importState.asStateFlow()

    private val _spreadsheetId = MutableStateFlow("")
    val spreadsheetId: StateFlow<String> = _spreadsheetId.asStateFlow()

    private val _range = MutableStateFlow("")
    val range: StateFlow<String> = _range.asStateFlow()

    val invoices = invoiceRepository.getAllInvoices()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setSpreadsheetId(id: String) {
        _spreadsheetId.value = id
    }

    fun setRange(range: String) {
        _range.value = range
    }

    fun importInvoices() {
        val id = _spreadsheetId.value
        val range = _range.value
        if (id.isBlank() || range.isBlank()) {
            _importState.value = ImportUiState.Error("Spreadsheet ID and range must not be empty")
            return
        }

        viewModelScope.launch {
            try {
                _importState.value = ImportUiState.Loading
                invoiceRepository.importInvoicesFromGoogleSheet(id, range)
                _importState.value = ImportUiState.Success("Invoices imported successfully")
            } catch (e: Exception) {
                _importState.value = ImportUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun resetImportState() {
        _importState.value = ImportUiState.Idle
    }
}
