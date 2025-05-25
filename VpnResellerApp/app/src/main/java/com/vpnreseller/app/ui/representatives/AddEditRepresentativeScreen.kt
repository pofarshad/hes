package com.vpnreseller.app.ui.representatives

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vpnreseller.app.R
import com.vpnreseller.core_domain.model.DiscountType
import com.vpnreseller.core_domain.model.Representative
import com.vpnreseller.core_domain.model.SubscriptionType
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditRepresentativeScreen(
    viewModel: RepresentativesViewModel = hiltViewModel(),
    representativeId: String?, // Null for new, non-null for edit
    onNavigateUp: () -> Unit
) {
    val selectedRepresentative by viewModel.selectedRepresentative.collectAsState()

    // Form state holders
    var fullName by remember { mutableStateOf("") }
    var adminUsernameMarzban by remember { mutableStateOf("") }
    var telegramLink by remember { mutableStateOf("") }
    var pricePerGbLimited by remember { mutableStateOf("") }
    var monthlyUnlimitedPrice by remember { mutableStateOf("") }
    var discountType by remember { mutableStateOf(DiscountType.NONE) }
    var discountValue by remember { mutableStateOf("") }
    var parentRepresentativeId by remember { mutableStateOf("") } // Simplified for now
    var defaultSubscriptionType by remember { mutableStateOf(SubscriptionType.LIMITED) }
    var defaultDurationMonths by remember { mutableStateOf("1") }
    var isActive by remember { mutableStateOf(true) }
    var notes by remember { mutableStateOf("") }

    // Used to identify if we are in edit mode and have loaded data
    var isEditMode by remember { mutableStateOf(representativeId != null) }
    var dataLoaded by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = representativeId) {
        viewModel.loadRepresentativeDetails(representativeId)
    }

    LaunchedEffect(selectedRepresentative, isEditMode) {
        if (isEditMode && selectedRepresentative != null && !dataLoaded) {
            selectedRepresentative?.let { rep ->
                fullName = rep.fullName
                adminUsernameMarzban = rep.adminUsernameMarzban
                telegramLink = rep.telegramLink ?: ""
                pricePerGbLimited = rep.pricePerGbLimited.toString()
                monthlyUnlimitedPrice = rep.monthlyUnlimitedPrice.toString()
                discountType = rep.discountType
                discountValue = rep.discountValue.toString()
                parentRepresentativeId = rep.parentRepresentativeId ?: ""
                defaultSubscriptionType = rep.defaultSubscriptionType
                defaultDurationMonths = rep.defaultDurationMonths.toString()
                isActive = rep.isActive
                notes = rep.notes ?: ""
                dataLoaded = true // Mark data as loaded to prevent re-initialization on recompose
            }
        } else if (!isEditMode) {
            // Reset fields for new entry form if needed, or rely on initial mutableStateOf
            dataLoaded = true // For new mode, consider data "loaded" as default values
        }
    }
    
    val screenTitle = if (isEditMode) stringResource(R.string.edit_representative_title)
                      else stringResource(R.string.add_representative_title)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(screenTitle) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text(stringResource(R.string.label_full_name)) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = adminUsernameMarzban, onValueChange = { adminUsernameMarzban = it }, label = { Text(stringResource(R.string.label_admin_username_marzban)) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = telegramLink, onValueChange = { telegramLink = it }, label = { Text(stringResource(R.string.label_telegram_link)) }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = pricePerGbLimited, onValueChange = { pricePerGbLimited = it }, label = { Text(stringResource(R.string.label_price_per_gb_limited)) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = monthlyUnlimitedPrice, onValueChange = { monthlyUnlimitedPrice = it }, label = { Text(stringResource(R.string.label_monthly_unlimited_price)) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            // Discount Type Dropdown
            Text(stringResource(R.string.label_discount_type), style = MaterialTheme.typography.labelMedium)
            EnumDropdown(
                options = DiscountType.values().toList(),
                selectedOption = discountType,
                onOptionSelected = { discountType = it },
                optionToResId = { it.toStringRes() }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = discountValue, onValueChange = { discountValue = it }, label = { Text(stringResource(R.string.label_discount_value)) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            
            // Parent Representative (Simplified - could be a searchable dropdown later)
            // OutlinedTextField(value = parentRepresentativeId, onValueChange = { parentRepresentativeId = it }, label = { Text(stringResource(R.string.label_parent_representative)) }, modifier = Modifier.fillMaxWidth())
            // Spacer(modifier = Modifier.height(8.dp))

            // Subscription Type Dropdown
            Text(stringResource(R.string.label_default_subscription_type), style = MaterialTheme.typography.labelMedium)
            EnumDropdown(
                options = SubscriptionType.values().toList(),
                selectedOption = defaultSubscriptionType,
                onOptionSelected = { defaultSubscriptionType = it },
                optionToResId = { it.toStringRes() }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = defaultDurationMonths, onValueChange = { defaultDurationMonths = it }, label = { Text(stringResource(R.string.label_default_duration_months)) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isActive, onCheckedChange = { isActive = it })
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.label_is_active))
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text(stringResource(R.string.label_notes)) }, modifier = Modifier.fillMaxWidth(), minLines = 3)
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val rep = Representative(
                        id = representativeId ?: UUID.randomUUID().toString(),
                        fullName = fullName,
                        adminUsernameMarzban = adminUsernameMarzban,
                        telegramLink = telegramLink.ifBlank { null },
                        pricePerGbLimited = pricePerGbLimited.toDoubleOrNull() ?: 0.0,
                        monthlyUnlimitedPrice = monthlyUnlimitedPrice.toDoubleOrNull() ?: 0.0,
                        discountType = discountType,
                        discountValue = discountValue.toDoubleOrNull() ?: 0.0,
                        parentRepresentativeId = parentRepresentativeId.ifBlank { null },
                        defaultSubscriptionType = defaultSubscriptionType,
                        defaultDurationMonths = defaultDurationMonths.toIntOrNull() ?: 1,
                        isActive = isActive,
                        notes = notes.ifBlank { null }
                    )
                    viewModel.saveRepresentative(rep)
                    onNavigateUp()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.button_save))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // Add OptIn here
@Composable
fun <T> EnumDropdown(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    optionToResId: (T) -> Int, // Changed parameter name and type
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = stringResource(id = optionToResId(selectedOption)), // Use stringResource here
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(stringResource(id = optionToResId(option))) }, // Use stringResource here
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

// Helper functions to get string resources for enums
fun DiscountType.toStringRes(): Int {
    return when (this) {
        DiscountType.NONE -> R.string.discount_type_none
        DiscountType.PERCENTAGE -> R.string.discount_type_percentage
        DiscountType.TIME_BASED -> R.string.discount_type_time_based
    }
}

fun SubscriptionType.toStringRes(): Int {
    return when (this) {
        SubscriptionType.LIMITED -> R.string.subscription_type_limited
        SubscriptionType.UNLIMITED -> R.string.subscription_type_unlimited
    }
}
