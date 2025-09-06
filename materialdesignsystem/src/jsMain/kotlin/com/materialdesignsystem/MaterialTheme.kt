package com.materialdesignsystem

import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.materialdesignsystem.theme.ColorScheme
import com.materialdesignsystem.theme.darkColorScheme
import com.materialdesignsystem.theme.lightColorScheme

object MaterialTheme {

    internal var light: ColorScheme = lightColorScheme()
        private set

    internal var dark: ColorScheme = darkColorScheme()
        private set

    fun setSchemes(
        lightScheme: ColorScheme = lightColorScheme(),
        darkScheme: ColorScheme = darkColorScheme()
    ) {
        light = lightScheme
        dark = darkScheme
    }
}

val ColorMode.toColorScheme get() = if (isDark) MaterialTheme.dark else MaterialTheme.light
