package com.yourcompany.vpnresellerapp

import android.app.Application
// import dagger.hilt.android.HiltAndroidApp // Uncomment if Hilt is chosen for DI

// @HiltAndroidApp // Uncomment if Hilt is chosen for DI
class VpnResellerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Dependency Injection (Hilt, Koin, etc.)
        // Initialize Logging libraries
        // Other application-wide initializations
    }
}
