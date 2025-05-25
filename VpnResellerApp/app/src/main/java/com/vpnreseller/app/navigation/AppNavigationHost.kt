package com.vpnreseller.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vpnreseller.app.ui.invoices.InvoiceImportScreen
import com.vpnreseller.app.ui.invoices.InvoiceListScreen

@Composable
fun AppNavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "invoiceList") {
        composable("invoiceList") {
            InvoiceListScreen(
                onNavigateToInvoiceDetail = { invoiceId ->
                    // TODO: Implement invoice detail navigation
                },
                onNavigateToImport = {
                    navController.navigate("invoiceImport")
                }
            )
        }
        composable("invoiceImport") {
            InvoiceImportScreen(
                onImportComplete = {
                    navController.popBackStack()
                }
            )
        }
        // Other composable destinations...
    }
}
