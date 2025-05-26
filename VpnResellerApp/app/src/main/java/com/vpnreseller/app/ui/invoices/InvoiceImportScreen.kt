package com.vpnreseller.app.ui.invoices

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vpnreseller.app.R
import com.vpnreseller.core_domain.model.ImportUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceImportScreen(
    viewModel: InvoicesViewModel = hiltViewModel(),
    onImportComplete: () -> Unit
) {
    var spreadsheetId by remember { mutableStateOf(TextFieldValue("")) }
    var range by remember { mutableStateOf(TextFieldValue("")) }
    
    val importState by viewModel.importState.collectAsState()

    // Effect to handle successful import
    LaunchedEffect(importState) {
        if (importState is ImportUiState.Success) {
            onImportComplete()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.import_invoices_screen_title)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = spreadsheetId,
                onValueChange = { spreadsheetId = it },
                label = { Text(stringResource(id = R.string.label_spreadsheet_id)) },
                modifier = Modifier.fillMaxWidth(),
                enabled = importState !is ImportUiState.Loading
            )
            
            OutlinedTextField(
                value = range,
                onValueChange = { range = it },
                label = { Text(stringResource(id = R.string.label_range)) },
                modifier = Modifier.fillMaxWidth(),
                enabled = importState !is ImportUiState.Loading
            )

            when (importState) {
                is ImportUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = stringResource(id = R.string.importing_invoices),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                is ImportUiState.Error -> {
                    Text(
                        text = (importState as ImportUiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                is ImportUiState.Success -> {
                    Text(
                        text = (importState as ImportUiState.Success).message,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                else -> { /* Idle or other states don't need special UI */ }
            }

            Button(
                onClick = {
                    viewModel.setSpreadsheetId(spreadsheetId.text)
                    viewModel.setRange(range.text)
                    viewModel.importInvoices()
                },
                enabled = importState !is ImportUiState.Loading &&
                         spreadsheetId.text.isNotBlank() &&
                         range.text.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.button_import))
            }
        }
    }
}
