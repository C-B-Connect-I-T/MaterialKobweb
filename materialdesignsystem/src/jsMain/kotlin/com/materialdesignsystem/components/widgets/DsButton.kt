@file:Suppress("all")

package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.materialdesignsystem.components.UniqueIdGenerator
import com.materialdesignsystem.constants.Constants
import com.materialdesignsystem.toColorScheme
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.thenIfNotNull
import com.varabyte.kobweb.silk.components.forms.ButtonKind
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.shifted
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

val ButtonVars.BorderRadius by StyleVariable<CSSLengthNumericValue>(prefix = "silk", defaultFallback = 100.px)

val ElevatedButtonStyle = ButtonStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, colorScheme.surfaceContainerLow)
            .setVariable(ButtonVars.Color, colorScheme.onSurface)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.surfaceContainerLow.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.surfaceContainerLow.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.surfaceContainerLow.shifted(colorMode, 0.3f))
            .boxShadow(
                BoxShadow.of(
                    offsetX = 0.px,
                    offsetY = 1.px,
                    blurRadius = 2.px,
                    spreadRadius = 0.px,
                    color = Colors.Black.copyf(alpha = 0.2f)
                )
            )
    }
}

val FilledButtonStyle = ButtonStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, colorScheme.primary)
            .setVariable(ButtonVars.Color, colorScheme.onPrimary)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.primary.lightened(0.1f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.primary.lightened(0.1f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.primary.lightened(0.3f))
    }
}

val OutlinedButtonStyle = ButtonStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
            .setVariable(ButtonVars.Color, colorScheme.primary)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.primary.lightened(0.7f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.primary.lightened(0.7f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.primary.lightened(0.5f))
            .border(1.px, LineStyle.Solid, colorScheme.outline)
    }
}

val FilledTonalButtonStyle = ButtonStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, colorScheme.secondaryContainer)
            .setVariable(ButtonVars.Color, colorScheme.onSecondaryContainer)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.secondaryContainer.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.secondaryContainer.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.secondaryContainer.shifted(colorMode, 0.3f))
    }
}

val TextButtonStyle = ButtonStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
            .setVariable(ButtonVars.Color, colorScheme.onSurface)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.primary.shifted(colorMode.opposite, 0.5f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.primary.shifted(colorMode.opposite, 0.5f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.primary.shifted(colorMode.opposite, 0.2f))
    }
}

@Composable
fun DsBaseButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    variant: CssStyleVariant<ButtonKind>? = null,
    borderRadius: DsBorderRadius? = null,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    size: ButtonSize = ButtonSize.MD,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val randomId = remember {
        id ?: UniqueIdGenerator.generateUniqueId(Constants.BASE_BUTTON_ID)
    }

    com.varabyte.kobweb.silk.components.forms.Button(
        modifier = modifier
            .id(randomId)
            .thenIfNotNull(borderRadius) {
                Modifier.borderRadius(it.topLeft, it.topRight, it.bottomRight, it.bottomLeft)
            },
        variant = variant,
        type = type,
        size = size,
        enabled = enabled,
        onClick = { onClick() },
        content = content
    )
}

@Composable
fun ElevatedButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = ElevatedButtonStyle,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    size: ButtonSize = ButtonSize.MD,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButton(
        modifier = modifier,
        id = id,
        variant = variant,
        type = type,
        enabled = enabled,
        borderRadius = borderRadius,
        size = size,
        onClick = onClick,
        content = content
    )
}

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = FilledButtonStyle,
    size: ButtonSize = ButtonSize.MD,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButton(
        modifier = modifier,
        id = id,
        variant = variant,
        type = type,
        enabled = enabled,
        size = size,
        borderRadius = borderRadius,
        onClick = onClick,
        content = content
    )
}

@Composable
fun DangerFilledButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = FilledButtonStyle,
    type: ButtonType = ButtonType.Button,
    size: ButtonSize = ButtonSize.MD,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButton(
        modifier = modifier
            .setVariable(ButtonVars.BackgroundDefaultColor, ColorMode.current.toColorScheme.error)
            .setVariable(ButtonVars.Color, ColorMode.current.toColorScheme.onError)
            .setVariable(ButtonVars.BackgroundHoverColor, ColorMode.current.toColorScheme.error.toRgb().copyf(alpha = 0.9f))
            .setVariable(ButtonVars.BackgroundFocusColor, ColorMode.current.toColorScheme.error.toRgb().copyf(alpha = 0.9f))
            .setVariable(ButtonVars.BackgroundPressedColor, ColorMode.current.toColorScheme.error.toRgb().copyf(alpha = 0.7f)),
        id = id,
        variant = variant,
        type = type,
        enabled = enabled,
        size = size,
        borderRadius = borderRadius,
        onClick = onClick,
        content = content
    )
}

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = OutlinedButtonStyle,
    type: ButtonType = ButtonType.Button,
    size: ButtonSize = ButtonSize.MD,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButton(
        modifier = modifier,
        id = id,
        variant = variant,
        type = type,
        size = size,
        enabled = enabled,
        borderRadius = borderRadius,
        onClick = onClick,
        content = content
    )
}

@Composable
fun FilledTonalButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = FilledTonalButtonStyle,
    type: ButtonType = ButtonType.Button,
    size: ButtonSize = ButtonSize.MD,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButton(
        modifier = modifier,
        id = id,
        variant = variant,
        type = type,
        enabled = enabled,
        size = size,
        borderRadius = borderRadius,
        onClick = onClick,
        content = content
    )
}

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = TextButtonStyle,
    type: ButtonType = ButtonType.Button,
    size: ButtonSize = ButtonSize.MD,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButton(
        modifier = modifier,
        id = id,
        variant = variant,
        type = type,
        size = size,
        enabled = enabled,
        borderRadius = borderRadius,
        onClick = onClick,
        content = content
    )
}

data class DsBorderRadius(
    val topLeft: CSSLengthOrPercentageNumericValue = 0.px,
    val topRight: CSSLengthOrPercentageNumericValue = 0.px,
    val bottomRight: CSSLengthOrPercentageNumericValue = 0.px,
    val bottomLeft: CSSLengthOrPercentageNumericValue = 0.px
) {
    constructor(all: CSSLengthOrPercentageNumericValue = 0.px) : this(
        topLeft = all,
        topRight = all,
        bottomRight = all,
        bottomLeft = all
    )

    val value = "$topLeft $topRight $bottomRight $bottomLeft"
}
