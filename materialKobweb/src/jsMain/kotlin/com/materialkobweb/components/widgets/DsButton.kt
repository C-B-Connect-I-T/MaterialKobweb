@file:Suppress("all")

package com.materialkobweb.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.materialkobweb.components.UniqueIdGenerator
import com.materialkobweb.constants.Constants
import com.materialkobweb.styles.MaterialColorVars
import com.materialkobweb.styles.lightened
import com.materialkobweb.styles.shifted
import com.materialkobweb.styles.withAlpha
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
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
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

val ButtonVars.BorderRadius by StyleVariable<CSSLengthNumericValue>(prefix = "silk", defaultFallback = 100.px)

val ElevatedButtonStyle = ButtonStyle.addVariant {
    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, MaterialColorVars.SurfaceContainerLow.value())
            .setVariable(ButtonVars.Color, MaterialColorVars.OnSurface.value())
            .setVariable(ButtonVars.BackgroundHoverColor, MaterialColorVars.SurfaceContainerLow.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundFocusColor, MaterialColorVars.SurfaceContainerLow.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundPressedColor, MaterialColorVars.SurfaceContainerLow.shifted(colorMode, 0.3f))
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
    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, MaterialColorVars.Primary.value())
            .setVariable(ButtonVars.Color, MaterialColorVars.OnPrimary.value())
            .setVariable(ButtonVars.BackgroundHoverColor, MaterialColorVars.Primary.lightened(0.1f))
            .setVariable(ButtonVars.BackgroundFocusColor, MaterialColorVars.Primary.lightened(0.1f))
            .setVariable(ButtonVars.BackgroundPressedColor, MaterialColorVars.Primary.lightened(0.3f))
    }
}

val OutlinedButtonStyle = ButtonStyle.addVariant {
    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
            .setVariable(ButtonVars.Color, MaterialColorVars.Primary.value())
            .setVariable(ButtonVars.BackgroundHoverColor, MaterialColorVars.Primary.lightened(0.7f))
            .setVariable(ButtonVars.BackgroundFocusColor, MaterialColorVars.Primary.lightened(0.7f))
            .setVariable(ButtonVars.BackgroundPressedColor, MaterialColorVars.Primary.lightened(0.5f))
            .border(1.px, LineStyle.Solid, MaterialColorVars.Outline.value())
    }
}

val FilledTonalButtonStyle = ButtonStyle.addVariant {
    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, MaterialColorVars.SecondaryContainer.value())
            .setVariable(ButtonVars.Color, MaterialColorVars.OnSecondaryContainer.value())
            .setVariable(ButtonVars.BackgroundHoverColor, MaterialColorVars.SecondaryContainer.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundFocusColor, MaterialColorVars.SecondaryContainer.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundPressedColor, MaterialColorVars.SecondaryContainer.shifted(colorMode, 0.3f))
    }
}

val TextButtonStyle = ButtonStyle.addVariant {
    base {
        Modifier
            .borderRadius(ButtonVars.BorderRadius.value())
            .setVariable(ButtonVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
            .setVariable(ButtonVars.Color, MaterialColorVars.OnSurface.value())
            .setVariable(ButtonVars.BackgroundHoverColor, MaterialColorVars.Primary.shifted(colorMode.opposite, 0.5f))
            .setVariable(ButtonVars.BackgroundFocusColor, MaterialColorVars.Primary.shifted(colorMode.opposite, 0.5f))
            .setVariable(ButtonVars.BackgroundPressedColor, MaterialColorVars.Primary.shifted(colorMode.opposite, 0.2f))
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
            .setVariable(ButtonVars.BackgroundDefaultColor, MaterialColorVars.Error.value())
            .setVariable(ButtonVars.Color, MaterialColorVars.OnError.value())
            .setVariable(ButtonVars.BackgroundHoverColor, MaterialColorVars.Error.withAlpha(alpha = 0.9f))
            .setVariable(ButtonVars.BackgroundFocusColor, MaterialColorVars.Error.withAlpha(alpha = 0.9f))
            .setVariable(ButtonVars.BackgroundPressedColor, MaterialColorVars.Error.withAlpha(alpha = 0.7f)),
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
