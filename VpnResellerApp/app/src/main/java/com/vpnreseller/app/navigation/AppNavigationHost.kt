package com.vpnreseller.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vpnreseller.app.ui.home.HomeScreen

/**
 * Main navigation host for the VPN Reseller App
 * Manages navigation between different screens
 */
@Composable
fun AppNavigationHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        
        // TODO: Add other navigation destinations
        // composable("representatives") { RepresentativesScreen(navController) }
        // composable("invoices") { InvoicesScreen(navController) }
        // composable("accounting") { AccountingScreen(navController) }
        // composable("settings") { SettingsScreen(navController) }
    }
}
