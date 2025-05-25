package com.vpnreseller.app.ui.representatives

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
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
import com.vpnreseller.core_domain.model.Representative

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepresentativeListScreen(
    viewModel: RepresentativesViewModel = hiltViewModel(),
    onNavigateToRepresentativeDetail: (String?) -> Unit // String for ID, null for new
) {
    val representatives by viewModel.representatives.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.representatives_title)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToRepresentativeDetail(null) }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_representative_fab_description)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                label = { Text(stringResource(id = R.string.search_representatives_hint)) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            if (representatives.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(id = R.string.empty_representatives_list))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(representatives, key = { it.id }) { representative ->
                        RepresentativeListItem(
                            representative = representative,
                            onClick = { onNavigateToRepresentativeDetail(representative.id) }
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun RepresentativeListItem(
    representative: Representative,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = representative.fullName, style = MaterialTheme.typography.titleMedium)
            Text(
                text = representative.adminUsernameMarzban,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        // Optionally, add an icon or indicator for active status, etc.
    }
}
