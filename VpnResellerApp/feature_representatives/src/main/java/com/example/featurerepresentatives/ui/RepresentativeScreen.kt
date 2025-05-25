package com.example.featurerepresentatives.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepresentativeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("پنل نمایندگان") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text("این صفحه جهت مدیریت اطلاعات نمایندگان طراحی شده است.")
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("نام کامل") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("نام کاربری مدیر") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("لینک تماس تلگرام") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("قیمت به ازای هر گیگابایت") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("قیمت اشتراک ماهیانه نامحدود") }
            )
        }
    }
}
