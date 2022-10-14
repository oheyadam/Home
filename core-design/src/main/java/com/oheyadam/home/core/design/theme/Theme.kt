package com.oheyadam.home.core.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeTheme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable() () -> Unit
) {
  val colors = when {
    dynamicColor && useDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
    dynamicColor && !useDarkTheme -> dynamicLightColorScheme(LocalContext.current)
    useDarkTheme -> darkColors
    else -> lightColors
  }

  MaterialTheme(
    colorScheme = colors,
    shapes = shapes,
    content = content
  )
}
