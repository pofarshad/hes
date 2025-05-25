package com.vpnreseller.app.ui.invoices

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vpnreseller.app.R
import com.vpnreseller.core_domain.model.Invoice
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceListScreen(
    viewModel: InvoicesViewModel = hiltViewModel(),
    onNavigateToInvoiceDetail: (String) -> Unit,
    onNavigateToImport: () -> Unit
) {
    val invoices by viewModel.invoices.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.invoices_title)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToImport) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.import_invoices_fab_description)
                )
            }
        }
    ) { paddingValues ->
        if (invoices.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(id = R.string.empty_invoices_list))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(invoices, key = { it.id }) { invoice ->
                    InvoiceListItem(invoice = invoice, onClick = { onNavigateToInvoiceDetail(invoice.id) })
                    Divider()
                }
            }
        }
    }
}

@Composable
fun InvoiceListItem(invoice: Invoice, onClick: () -> Unit) {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Invoice ID: ${invoice.id}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Representative ID: ${invoice.representativeId}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Date: ${dateFormat.format(invoice.generationDate)}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Total: ${invoice.totalAmount}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Status: ${invoice.status}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
