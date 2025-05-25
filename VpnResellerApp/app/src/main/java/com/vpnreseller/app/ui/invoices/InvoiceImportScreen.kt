package com.vpnreseller.app.ui.invoices

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vpnreseller.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceImportScreen(
    viewModel: InvoicesViewModel = hiltViewModel(),
    onImportComplete: () -> Unit
) {
    var spreadsheetId by remember { mutableStateOf(TextFieldValue("")) }
    var range by remember { mutableStateOf(TextFieldValue("")) }
    var isImporting by remember { mutableStateOf(false) }
    var importResult by remember { mutableStateOf<String?>(null) }

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
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = range,
                onValueChange = { range = it },
                label = { Text(stringResource(id = R.string.label_range)) },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    isImporting = true
                    importResult = null
                    viewModel.setSpreadsheetId(spreadsheetId.text)
                    viewModel.setRange(range.text)
                    viewModel.importInvoices()
                    isImporting = false
                    importResult = "Import completed"
                    onImportComplete()
                },
                enabled = !isImporting,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.button_import))
            }
            importResult?.let {
                Text(text = it)
            }
        }
    }
}
