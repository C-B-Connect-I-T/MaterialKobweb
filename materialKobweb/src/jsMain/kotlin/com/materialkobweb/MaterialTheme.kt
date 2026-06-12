package com.materialkobweb

import com.materialkobweb.extensions.ButtonSizeXL
import com.materialkobweb.styles.MaterialColorVars
import com.materialkobweb.theme.ColorScheme
import com.materialkobweb.theme.darkColorScheme
import com.materialkobweb.theme.lightColorScheme
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.cssClass
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.border
import com.varabyte.kobweb.silk.theme.colors.palette.button
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.input
import com.varabyte.kobweb.silk.theme.colors.palette.link

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

@InitSilk
fun updateTheme(ctx: InitSilkContext) {
    ctx.theme.registerStyle("silk-button-size_xl", ButtonSizeXL)

    ColorMode.entries.forEach { colorM ->
        ctx.stylesheet.registerStyleBase(".${colorM.cssClass}") {
            val colorScheme = colorM.toColorScheme

            Modifier
                .setVariable(MaterialColorVars.Primary, colorScheme.primary)
                .setVariable(MaterialColorVars.OnPrimary, colorScheme.onPrimary)
                .setVariable(MaterialColorVars.PrimaryContainer, colorScheme.primaryContainer)
                .setVariable(MaterialColorVars.OnPrimaryContainer, colorScheme.onPrimaryContainer)
                .setVariable(MaterialColorVars.Secondary, colorScheme.secondary)
                .setVariable(MaterialColorVars.OnSecondary, colorScheme.onSecondary)
                .setVariable(MaterialColorVars.SecondaryContainer, colorScheme.secondaryContainer)
                .setVariable(MaterialColorVars.OnSecondaryContainer, colorScheme.onSecondaryContainer)
                .setVariable(MaterialColorVars.Tertiary, colorScheme.tertiary)
                .setVariable(MaterialColorVars.OnTertiary, colorScheme.onTertiary)
                .setVariable(MaterialColorVars.TertiaryContainer, colorScheme.tertiaryContainer)
                .setVariable(MaterialColorVars.OnTertiaryContainer, colorScheme.onTertiaryContainer)
                .setVariable(MaterialColorVars.Error, colorScheme.error)
                .setVariable(MaterialColorVars.OnError, colorScheme.onError)
                .setVariable(MaterialColorVars.ErrorContainer, colorScheme.errorContainer)
                .setVariable(MaterialColorVars.OnErrorContainer, colorScheme.onErrorContainer)
                .setVariable(MaterialColorVars.Background, colorScheme.background)
                .setVariable(MaterialColorVars.OnBackground, colorScheme.onBackground)
                .setVariable(MaterialColorVars.Surface, colorScheme.surface)
                .setVariable(MaterialColorVars.OnSurface, colorScheme.onSurface)
                .setVariable(MaterialColorVars.SurfaceVariant, colorScheme.surfaceVariant)
                .setVariable(MaterialColorVars.OnSurfaceVariant, colorScheme.onSurfaceVariant)
                .setVariable(MaterialColorVars.SurfaceContainerLowest, colorScheme.surfaceContainerLowest)
                .setVariable(MaterialColorVars.SurfaceContainerLow, colorScheme.surfaceContainerLow)
                .setVariable(MaterialColorVars.SurfaceContainer, colorScheme.surfaceContainer)
                .setVariable(MaterialColorVars.SurfaceContainerHigh, colorScheme.surfaceContainerHigh)
                .setVariable(MaterialColorVars.SurfaceContainerHighest, colorScheme.surfaceContainerHighest)
                .setVariable(MaterialColorVars.SurfaceDim, colorScheme.surfaceDim)
                .setVariable(MaterialColorVars.SurfaceBright, colorScheme.surfaceBright)
                .setVariable(MaterialColorVars.Outline, colorScheme.outline)
                .setVariable(MaterialColorVars.OutlineVariant, colorScheme.outlineVariant)
                .setVariable(MaterialColorVars.Scrim, colorScheme.scrim)
                .setVariable(MaterialColorVars.InverseSurface, colorScheme.inverseSurface)
                .setVariable(MaterialColorVars.InverseOnSurface, colorScheme.inverseOnSurface)
                .setVariable(MaterialColorVars.InversePrimary, colorScheme.inversePrimary)
        }
    }

    val lightColorScheme = ColorMode.LIGHT.toColorScheme
    val darkColorScheme = ColorMode.DARK.toColorScheme

    // Background
    ctx.theme.palettes.light.background = lightColorScheme.background
    ctx.theme.palettes.dark.background = darkColorScheme.background

    // Color
    ctx.theme.palettes.light.color = lightColorScheme.onBackground
    ctx.theme.palettes.dark.color = darkColorScheme.onBackground

    // border
    ctx.theme.palettes.light.border = lightColorScheme.outline
    ctx.theme.palettes.dark.border = darkColorScheme.outline

    // Button
    ctx.theme.palettes.light.button.set(
        default = lightColorScheme.primary,
        hover = lightColorScheme.primary.darkened(0.08f),
        focus = lightColorScheme.primary.darkened(0.12f),
        pressed = lightColorScheme.primary.darkened(0.12f),
    )
    ctx.theme.palettes.dark.button.set(
        default = darkColorScheme.primary,
        hover = darkColorScheme.primary.lightened(0.08f),
        focus = darkColorScheme.primary.lightened(0.12f),
        pressed = darkColorScheme.primary.lightened(0.12f),
    )

    // Link
    ctx.theme.palettes.light.link.set(
        lightColorScheme.onBackground,
        lightColorScheme.onBackground
    )
    ctx.theme.palettes.dark.link.set(
        darkColorScheme.onBackground,
        darkColorScheme.onBackground
    )

    // Input palette - following Material Design 3 guidelines
    // Dark mode
    ctx.theme.palettes.dark.input.set(
        hoveredBorder = darkColorScheme.outlineVariant,
        invalidBorder = darkColorScheme.error.toRgb(),
        filled = darkColorScheme.surfaceContainerHighest.toRgb(),
        filledHover = darkColorScheme.surfaceContainerHighest.toRgb().lightened(0.04f),
        filledFocus = darkColorScheme.surfaceContainerHighest.toRgb().lightened(0.08f)
    )
    // Light mode
    ctx.theme.palettes.light.input.set(
        hoveredBorder = lightColorScheme.outlineVariant,
        invalidBorder = lightColorScheme.error.toRgb(),
        filled = lightColorScheme.surfaceContainerHighest.toRgb(),
        filledHover = lightColorScheme.surfaceContainerHighest.toRgb().darkened(0.04f),
        filledFocus = lightColorScheme.surfaceContainerHighest.toRgb().darkened(0.08f)
    )
}