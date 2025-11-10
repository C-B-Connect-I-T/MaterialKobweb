@file:Suppress("all")

package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.thenIfNotNull
import com.varabyte.kobweb.silk.components.forms.ButtonKind
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.theme.colors.shifted
import com.materialdesignsystem.components.UniqueIdGenerator
import com.materialdesignsystem.constants.Constants
import com.materialdesignsystem.toColorScheme
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.px

val ElevatedIconButtonStyle = ButtonStyle.addVariant {
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

val FilledIconButtonStyle = ButtonStyle.addVariant {
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

val OutlinedIconButtonStyle = ButtonStyle.addVariant {
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

val FilledTonalIconButtonStyle = ButtonStyle.addVariant {
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

val IconButtonStyle = ButtonStyle.addVariant {
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
fun DsBaseIconButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    variant: CssStyleVariant<ButtonKind>? = null,
    size: ButtonSize = ButtonSize.MD,
    borderRadius: DsBorderRadius? = null,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val randomId = remember {
        id ?: UniqueIdGenerator.generateUniqueId(Constants.BASE_BUTTON_ID)
    }

    com.varabyte.kobweb.silk.components.forms.Button(
        modifier =
            Modifier
                .then(modifier)
                .padding(0.px)
                .id(randomId)
                .thenIfNotNull(borderRadius) {
                    Modifier.borderRadius(it.topLeft, it.topRight, it.bottomRight, it.bottomLeft)
                }
                .size(ButtonVars.Height.value())
                .fontSize(ButtonVars.Height.value() - ButtonVars.PaddingHorizontal.value()),
        variant = variant,
        type = type,
        size = size,
        enabled = enabled,
        onClick = { onClick() },
        content = {
            content()
        }
    )
}

@Composable
fun ElevatedIconButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    size: ButtonSize = ButtonSize.MD,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = ElevatedIconButtonStyle,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    DsBaseIconButton(
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
fun FilledIconButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    size: ButtonSize = ButtonSize.MD,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = FilledIconButtonStyle,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    DsBaseIconButton(
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
fun OutlinedIconButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    size: ButtonSize = ButtonSize.MD,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = OutlinedIconButtonStyle,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    DsBaseIconButton(
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
fun FilledTonalIconButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    size: ButtonSize = ButtonSize.MD,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = FilledTonalIconButtonStyle,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    DsBaseIconButton(
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
fun IconButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    size: ButtonSize = ButtonSize.MD,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonKind> = IconButtonStyle,
    type: ButtonType = ButtonType.Button,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    DsBaseIconButton(
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
