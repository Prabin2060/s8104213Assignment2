package com.example.s8104213assignment2.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Our new custom LIGHT theme
private val LightColorScheme = lightColorScheme(
    primary = VibrantBlue,
    onPrimary = PureWhite,
    secondary = VibrantBlue, // Use primary for secondary
    onSecondary = PureWhite,
    background = PureWhite,
    onBackground = DarkText,
    surface = PureWhite, // Cards, bottom sheets
    onSurface = DarkText, // Text on cards
    onSurfaceVariant = Color.Gray, // For subtitles
    error = ErrorRed
)

// Our new custom DARK theme
private val DarkColorScheme = darkColorScheme(
    primary = VibrantBlueDark,
    onPrimary = Color.Black,
    secondary = VibrantBlueDark,
    onSecondary = Color.Black,
    background = SurfaceDark,
    onBackground = LightText,
    surface = SurfaceDarkVariant, // Cards will be a slightly lighter gray
    onSurface = LightText,
    onSurfaceVariant = Color.LightGray, // For subtitles
    error = ErrorRedDark
)

@Composable
fun S8104213assignment2Theme( // Make sure this is lowercase
    darkTheme: Boolean = isSystemInDarkTheme(),
    // THIS IS THE FIX! We force dynamicColor to be false.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        // It will now always pick our custom themes
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // Set status bar color to match the theme's background
            window.statusBarColor = colorScheme.background.toArgb()

            // This makes the app draw behind the status bar (edge-to-edge)
            // WindowCompat.setDecorFitsSystemWindows(window, false) // <-- Let's disable this for a simpler, solid look

            // This automatically changes status bar icons (clock, battery) to dark or light
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}