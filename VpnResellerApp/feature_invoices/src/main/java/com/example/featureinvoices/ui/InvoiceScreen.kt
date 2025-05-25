package com.example.featureinvoices.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InvoiceScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("صدور فاکتور") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text("این صفحه جهت ایجاد و مدیریت فاکتورها طراحی شده است.")
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("شناسه مدیر") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("شناسه کاربر") }
            )
            OutlinedButton(onClick = { 
                // TODO: Import structured data from Google Sheets/Excel
            }) {
                Text("وارد کردن داده‌ها")
            }
            Button(onClick = { 
                // TODO: Generate invoice as image/PDF
            }, modifier = Modifier.padding(top = 8.dp)) {
                Text("تولید فاکتور")
            }
        }
    }
}
