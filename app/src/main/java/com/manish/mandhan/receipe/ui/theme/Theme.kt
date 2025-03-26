package com.manish.mandhan.receipe.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.common.util.Strings
import com.manish.mandhan.presentation.screens.theme.onBlack
import com.manish.mandhan.presentation.screens.theme.onWhite
import com.manish.mandhan.receipe.vm.ThemeViewModel

private val DarkColorScheme = darkColorScheme(
    primary = Color.Black,
    onPrimary = onBlack,
    tertiary = Color.Black,
    secondary = Color.DarkGray
)

private val LightColorScheme = lightColorScheme(
    primary = onWhite,
    onPrimary = Color.White,
    tertiary = Color(0xFDFAFAFA),
    secondary = Color.DarkGray


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ReceipeTheme(
    themeViewModel: ThemeViewModel,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val currentTheme = themeViewModel.currentTheme.collectAsState(null)

    val colorScheme = when (currentTheme.value) {
        "Dark" -> DarkColorScheme
        "Light" -> LightColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
