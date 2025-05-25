package com.example.featureaccounting.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountingScreen() {
    var paymentInput by remember { mutableStateOf("") }
    var invoiceStatus by remember { mutableStateOf("پرداخت نشده") }
    var transactionLog by remember { mutableStateOf("تراکنشی ثبت نشده است.") }
    
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("مدیریت حسابداری") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            OutlinedTextField(
                value = paymentInput,
                onValueChange = { paymentInput = it },
                label = { Text("مبلغ پرداختی") }
            )
            Button(onClick = {
                // Simulate marking the invoice as paid and log the transaction
                invoiceStatus = "پرداخت شده"
                transactionLog += "\nتراکنش: پرداخت مبلغ $paymentInput در ${System.currentTimeMillis()}"
                paymentInput = ""
            }, modifier = Modifier.padding(top = 8.dp)) {
                Text("ثبت پرداخت")
            }
            Text(
                text = "وضعیت فاکتور: $invoiceStatus",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "سوابق تراکنش‌ها:",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = transactionLog,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
