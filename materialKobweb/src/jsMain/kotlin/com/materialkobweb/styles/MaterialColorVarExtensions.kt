package com.materialkobweb.styles

import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Color
import kotlin.math.abs

/**
 * Extension functions for deriving shaded/transparent colors from a [MaterialColorVars] CSS variable
 * (or any other `StyleVariable.PropertyValue<CSSColorValue>`) using CSS `color-mix()`.
 *
 * Since these variables are resolved by the browser at paint time (not by Kotlin at style-registration time),
 * we can't manipulate their underlying RGB channels the way we can with a plain [Color]. Instead, we describe
 * the desired shade as a `color-mix()` expression, which the browser resolves lazily - this is what lets us
 * avoid the flicker/incorrect-color issues that come from resolving colors at Kotlin compose/style-eval time.
 */

/**
 * Applies an alpha (opacity) value to a CSS color variable using color-mix.
 * @param alpha The opacity level (0.0 = fully transparent, 1.0 = fully opaque)
 * @return A CSSColorValue with the alpha applied
 */
fun StyleVariable.PropertyValue<CSSColorValue>.withAlpha(alpha: Float): CSSColorValue {
    require(alpha in 0f..1f)

    val percentage = ((1 - alpha) * 100).toInt()
    val colorMix = "color-mix(in srgb, ${this.value()}, transparent $percentage%)"
    return Color(colorMix)
}

/**
 * Lightens a CSS color variable towards white using color-mix.
 * @param byPercent How much to shift towards white (0.0 = unchanged, 1.0 = fully white)
 * @return A CSSColorValue lightened by the given percentage
 */
fun StyleVariable.PropertyValue<CSSColorValue>.lightened(byPercent: Float): CSSColorValue {
    require(byPercent in 0f..1f)
    if (byPercent == 0f) return this.value()

    val percentage = (byPercent * 100).toInt()
    val colorMix = "color-mix(in srgb, ${this.value()}, white $percentage%)"
    return Color(colorMix)
}

/**
 * Darkens a CSS color variable towards black using color-mix.
 * @param byPercent How much to shift towards black (0.0 = unchanged, 1.0 = fully black)
 * @return A CSSColorValue darkened by the given percentage
 */
fun StyleVariable.PropertyValue<CSSColorValue>.darkened(byPercent: Float): CSSColorValue {
    require(byPercent in 0f..1f)
    if (byPercent == 0f) return this.value()

    val percentage = (byPercent * 100).toInt()
    val colorMix = "color-mix(in srgb, ${this.value()}, black $percentage%)"
    return Color(colorMix)
}

/**
 * Shifts a CSS color variable lighter in dark mode or darker in light mode (mirroring
 * [com.varabyte.kobweb.silk.theme.colors.shifted] for plain [com.varabyte.kobweb.compose.ui.graphics.Color]s).
 * @param colorMode The current color mode, used to decide whether to lighten or darken.
 * @param byPercent Positive values lighten in dark mode / darken in light mode; negative values invert that.
 */
fun StyleVariable.PropertyValue<CSSColorValue>.shifted(colorMode: ColorMode, byPercent: Float): CSSColorValue {
    if (byPercent == 0f) return this.value()
    val shouldLighten = when {
        colorMode == ColorMode.DARK && byPercent > 0f -> true
        colorMode == ColorMode.LIGHT && byPercent < 0f -> true
        else -> false
    }
    val absPercent = abs(byPercent)
    return if (shouldLighten) this.lightened(absPercent) else this.darkened(absPercent)
}
