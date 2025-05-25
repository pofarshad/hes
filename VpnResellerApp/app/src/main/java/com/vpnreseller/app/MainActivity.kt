package com.vpnreseller.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.vpnreseller.app.navigation.AppNavigationHost
import com.vpnreseller.core_ui.theme.VpnResellerAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity for VPN Reseller App
 * Entry point of the application with proper theme and RTL support
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VpnResellerAppTheme {
                // Provide RTL layout direction for Persian language support
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        AppNavigationHost(navController = navController)
                    }
                }
            }
        }
    }
}
