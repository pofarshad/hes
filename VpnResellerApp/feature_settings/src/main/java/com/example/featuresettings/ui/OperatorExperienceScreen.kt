package com.example.featuresettings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperatorExperienceScreen() {
    var lastRefreshTime by remember { mutableStateOf("هنوز تازه‌سازی نشده") }
    
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("تجربه اپراتور") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Button(onClick = {
                // Simulate data refresh
                lastRefreshTime = "آخرین تازه‌سازی: ${System.currentTimeMillis()}"
            }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "تازه‌سازی")
                Text("تازه‌سازی")
            }
            Text(
                text = "آخرین زمان تازه‌سازی: $lastRefreshTime",
                modifier = Modifier.padding(top = 8.dp)
            )
            // Display smart entry shortcuts as a demonstration
            Text(
                text = "میانبرهای هوشمند: ورود سریع فاکتور / پرداخت سریع / گزارش فوری",
                modifier = Modifier.padding(top = 16.dp)
            )
            // Simulate notification reminder
            Button(
                onClick = { /* trigger notification reminder simulation */ },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("اعلان یادآوری پرداخت")
            }
            // Embedded help section
            Text(
                text = "راهنمای کاربری: برای استفاده از این صفحه دکمه تازه‌سازی را فشار دهید. از میانبرها برای ورود سریع به بخش‌ها استفاده کنید.",
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
