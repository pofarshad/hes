package com.vpnreseller.core_ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Primary colors for VPN Reseller App
val VpnBlue = Color(0xFF1976D2)
val VpnBlueVariant = Color(0xFF1565C0)
val VpnLightBlue = Color(0xFF42A5F5)

// Secondary colors
val VpnGreen = Color(0xFF4CAF50)
val VpnGreenVariant = Color(0xFF388E3C)

// Error colors
val VpnRed = Color(0xFFE53935)
val VpnRedVariant = Color(0xFFD32F2F)

// Surface colors
val VpnSurfaceLight = Color(0xFFFFFBFE)
val VpnSurfaceDark = Color(0xFF1C1B1F)

// Background colors
val VpnBackgroundLight = Color(0xFFFFFBFE)
val VpnBackgroundDark = Color(0xFF1C1B1F)

// On colors
val VpnOnPrimaryLight = Color(0xFFFFFFFF)
val VpnOnPrimaryDark = Color(0xFF000000)
val VpnOnBackgroundLight = Color(0xFF1C1B1F)
val VpnOnBackgroundDark = Color(0xFFE6E1E5)
val VpnOnSurfaceLight = Color(0xFF1C1B1F)
val VpnOnSurfaceDark = Color(0xFFE6E1E5)

/**
 * Light color scheme for VPN Reseller App
 * Ensures proper contrast for visibility
 */
val LightColorScheme = lightColorScheme(
    primary = VpnBlue,
    onPrimary = VpnOnPrimaryLight,
    primaryContainer = VpnLightBlue,
    onPrimaryContainer = Color(0xFF001D36),
    secondary = VpnGreen,
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFC8E6C9),
    onSecondaryContainer = Color(0xFF002106),
    tertiary = Color(0xFF7D5260),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFD8E4),
    onTertiaryContainer = Color(0xFF31111D),
    error = VpnRed,
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = VpnBackgroundLight,
    onBackground = VpnOnBackgroundLight,
    surface = VpnSurfaceLight,
    onSurface = VpnOnSurfaceLight,
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF4EFF4),
    inversePrimary = Color(0xFF90CAF9)
)

/**
 * Dark color scheme for VPN Reseller App
 * Ensures proper contrast for visibility in dark mode
 */
val DarkColorScheme = darkColorScheme(
    primary = VpnLightBlue,
    onPrimary = VpnOnPrimaryDark,
    primaryContainer = VpnBlueVariant,
    onPrimaryContainer = Color(0xFF90CAF9),
    secondary = Color(0xFF81C784),
    onSecondary = Color(0xFF003A00),
    secondaryContainer = VpnGreenVariant,
    onSecondaryContainer = Color(0xFFC8E6C9),
    tertiary = Color(0xFFEFB8C8),
    onTertiary = Color(0xFF492532),
    tertiaryContainer = Color(0xFF633B48),
    onTertiaryContainer = Color(0xFFFFD8E4),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = VpnBackgroundDark,
    onBackground = VpnOnBackgroundDark,
    surface = VpnSurfaceDark,
    onSurface = VpnOnSurfaceDark,
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFFE6E1E5),
    inverseOnSurface = Color(0xFF313033),
    inversePrimary = VpnBlue
)
