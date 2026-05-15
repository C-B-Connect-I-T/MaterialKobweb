package com.materialdesignsystem.styles

import com.varabyte.kobweb.compose.css.StyleVariable
import org.jetbrains.compose.web.css.CSSColorValue

/**
 * CSS variables for Material Design 3 colors.
 * These are set at stylesheet initialization time, preventing flickering on page load.
 * Usage: Modifier.backgroundColor(MaterialColorVars.SurfaceContainer.value())
 */
object MaterialColorVars {
    // Primary
    val Primary by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnPrimary by StyleVariable<CSSColorValue>(prefix = "ks")
    val PrimaryContainer by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnPrimaryContainer by StyleVariable<CSSColorValue>(prefix = "ks")

    // Secondary
    val Secondary by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnSecondary by StyleVariable<CSSColorValue>(prefix = "ks")
    val SecondaryContainer by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnSecondaryContainer by StyleVariable<CSSColorValue>(prefix = "ks")

    // Tertiary
    val Tertiary by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnTertiary by StyleVariable<CSSColorValue>(prefix = "ks")
    val TertiaryContainer by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnTertiaryContainer by StyleVariable<CSSColorValue>(prefix = "ks")

    // Error
    val Error by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnError by StyleVariable<CSSColorValue>(prefix = "ks")
    val ErrorContainer by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnErrorContainer by StyleVariable<CSSColorValue>(prefix = "ks")

    // Background & Surface
    val Background by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnBackground by StyleVariable<CSSColorValue>(prefix = "ks")
    val Surface by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnSurface by StyleVariable<CSSColorValue>(prefix = "ks")
    val SurfaceVariant by StyleVariable<CSSColorValue>(prefix = "ks")
    val OnSurfaceVariant by StyleVariable<CSSColorValue>(prefix = "ks")

    // Surface Containers
    val SurfaceContainerLowest by StyleVariable<CSSColorValue>(prefix = "ks")
    val SurfaceContainerLow by StyleVariable<CSSColorValue>(prefix = "ks")
    val SurfaceContainer by StyleVariable<CSSColorValue>(prefix = "ks")
    val SurfaceContainerHigh by StyleVariable<CSSColorValue>(prefix = "ks")
    val SurfaceContainerHighest by StyleVariable<CSSColorValue>(prefix = "ks")
    val SurfaceDim by StyleVariable<CSSColorValue>(prefix = "ks")
    val SurfaceBright by StyleVariable<CSSColorValue>(prefix = "ks")

    // Outline
    val Outline by StyleVariable<CSSColorValue>(prefix = "ks")
    val OutlineVariant by StyleVariable<CSSColorValue>(prefix = "ks")

    // Other
    val Scrim by StyleVariable<CSSColorValue>(prefix = "ks")
    val InverseSurface by StyleVariable<CSSColorValue>(prefix = "ks")
    val InverseOnSurface by StyleVariable<CSSColorValue>(prefix = "ks")
    val InversePrimary by StyleVariable<CSSColorValue>(prefix = "ks")
}

