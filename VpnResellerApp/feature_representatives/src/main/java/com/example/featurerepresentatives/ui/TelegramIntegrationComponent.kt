package com.example.featurerepresentatives.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler

@Composable
fun TelegramIntegrationComponent(telegramUrl: String, status: String) {
    val uriHandler = LocalUriHandler.current
    Button(onClick = { uriHandler.openUri(telegramUrl) }) {
        Text(text = "تماس با تلگرام (وضعیت: $status)")
    }
}
