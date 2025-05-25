package com.vpnreseller.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for VPN Reseller App
 * Annotated with @HiltAndroidApp to enable Hilt dependency injection
 */
@HiltAndroidApp
class VpnResellerApplication : Application()
