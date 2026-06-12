package com.materialkobweb.components.widgets

/**
 * ButtonLink Components - Link variants styled as Material Design buttons
 *
 * These components combine Kobweb's Link navigation with Material Design button styling.
 * They provide SPA navigation without onClick handlers, using href/path instead.
 *
 * Available variants:
 * - ElevatedButtonLink - Elevated surface with shadow
 * - FilledButtonLink - Solid primary color fill
 * - OutlinedButtonLink - Outlined with transparent background
 * - FilledTonalButtonLink - Tonal fill with secondary colors
 * - TextButtonLink - Text-only with transparent background
 *
 * Usage example:
 * ```
 * FilledButtonLink(
 *     path = "/search",
 *     size = ButtonSize.MD
 * ) {
 *     Text("Search")
 * }
 * ```
 *
 * @see com.varabyte.kobweb.silk.components.navigation.Link
 * @see com.varabyte.kobweb.silk.components.forms.ButtonSize
 */

import androidx.compose.runtime.Composable
import com.materialkobweb.toColorScheme
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.userSelect
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIfNotNull
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.navigation.UpdateHistoryMode
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkVars
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.focus
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.shifted
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px

// Define custom ComponentKind for ButtonLink
sealed interface ButtonLinkKind : ComponentKind

// Link style variables
object ButtonLinkVars {
    val BorderRadius by StyleVariable<CSSLengthNumericValue>(prefix = "silk", defaultFallback = 100.px)
}

// Base ButtonLink style that mimics ButtonStyle
val ButtonLinkStyle = CssStyle<ButtonLinkKind> {
    base {
        Modifier
            .color(ButtonVars.Color.value())
            .backgroundColor(ButtonVars.BackgroundDefaultColor.value())
            .lineHeight(1.2)
            .height(ButtonVars.Height.value())
            .minWidth(ButtonVars.Height.value())
            .fontSize(ButtonVars.FontSize.value())
            .fontWeight(FontWeight.SemiBold)
            .whiteSpace(WhiteSpace.NoWrap)
            .padding(leftRight = ButtonVars.PaddingHorizontal.value())
            .styleModifier {
                property("vertical-align", "middle")
            }
            .borderRadius(ButtonLinkVars.BorderRadius.value())
            .border(0.px)
            .userSelect(UserSelect.None)
            .textDecorationLine(TextDecorationLine.None)
            .styleModifier {
                property("transition", "background-color ${ButtonVars.ColorTransitionDuration.value()}")
                property("display", "inline-flex")
                property("align-items", "center")
                property("justify-content", "center")
            }
    }

    hover {
        Modifier
            .textDecorationLine(TextDecorationLine.None)
            .backgroundColor(ButtonVars.BackgroundHoverColor.value())
            .cursor(Cursor.Pointer)
    }

    focus {
        Modifier
            .outline(2.px, LineStyle.Solid, Colors.Transparent)
            .boxShadow(spreadRadius = 0.1875.cssRem, color = ButtonVars.BackgroundFocusColor.value())
    }
}

// Elevated variant
val ElevatedButtonLinkVariant = ButtonLinkStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonLinkVars.BorderRadius.value())
            .setVariable(ButtonLinkVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, colorScheme.surfaceContainerLow)
            .setVariable(ButtonVars.Color, colorScheme.onSurface)
            .setVariable(LinkVars.DefaultColor, colorScheme.onSurface)
            .setVariable(LinkVars.VisitedColor, colorScheme.onSurface)
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

// Filled variant
val FilledButtonLinkVariant = ButtonLinkStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonLinkVars.BorderRadius.value())
            .setVariable(ButtonLinkVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, colorScheme.primary)
            .setVariable(ButtonVars.Color, colorScheme.onPrimary)
            .setVariable(LinkVars.DefaultColor, colorScheme.onPrimary)
            .setVariable(LinkVars.VisitedColor, colorScheme.onPrimary)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.primary.lightened(0.1f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.primary.lightened(0.1f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.primary.lightened(0.3f))
    }
}

// Outlined variant
val OutlinedButtonLinkVariant = ButtonLinkStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonLinkVars.BorderRadius.value())
            .setVariable(ButtonLinkVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
            .setVariable(ButtonVars.Color, colorScheme.primary)
            .setVariable(LinkVars.DefaultColor, colorScheme.primary)
            .setVariable(LinkVars.VisitedColor, colorScheme.primary)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.primary.lightened(0.7f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.primary.lightened(0.7f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.primary.lightened(0.5f))
            .border(1.px, LineStyle.Solid, colorScheme.outline)
    }
}

// Filled Tonal variant
val FilledTonalButtonLinkVariant = ButtonLinkStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonLinkVars.BorderRadius.value())
            .setVariable(ButtonLinkVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, colorScheme.secondaryContainer)
            .setVariable(ButtonVars.Color, colorScheme.onSecondaryContainer)
            .setVariable(LinkVars.DefaultColor, colorScheme.onSecondaryContainer)
            .setVariable(LinkVars.VisitedColor, colorScheme.onSecondaryContainer)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.secondaryContainer.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.secondaryContainer.shifted(colorMode, 0.1f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.secondaryContainer.shifted(colorMode, 0.3f))
    }
}

// Text variant
val TextButtonLinkVariant = ButtonLinkStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonLinkVars.BorderRadius.value())
            .setVariable(ButtonLinkVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
            .setVariable(ButtonVars.Color, colorScheme.onSurface)
            .setVariable(LinkVars.DefaultColor, colorScheme.onSurface)
            .setVariable(LinkVars.VisitedColor, colorScheme.onSurface)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.primary.shifted(colorMode.opposite, 0.5f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.primary.shifted(colorMode.opposite, 0.5f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.primary.shifted(colorMode.opposite, 0.2f))
    }
}

val PrimaryTextButtonLinkStyle = ButtonLinkStyle.addVariant {
    val colorScheme = colorMode.toColorScheme

    base {
        Modifier
            .borderRadius(ButtonLinkVars.BorderRadius.value())
            .setVariable(ButtonLinkVars.BorderRadius, 999.px)
            .setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
            .setVariable(ButtonVars.Color, colorScheme.primary)
            .setVariable(LinkVars.DefaultColor, colorScheme.primary)
            .setVariable(LinkVars.VisitedColor, colorScheme.primary)
            .setVariable(ButtonVars.BackgroundHoverColor, colorScheme.primaryContainer.shifted(colorMode.opposite, 0.5f))
            .setVariable(ButtonVars.BackgroundFocusColor, colorScheme.primaryContainer.shifted(colorMode.opposite, 0.5f))
            .setVariable(ButtonVars.BackgroundPressedColor, colorScheme.primaryContainer.shifted(colorMode.opposite, 0.2f))
    }
}

// Base ButtonLink component
@Composable
fun DsBaseButtonLink(
    path: String,
    modifier: Modifier = Modifier,
    id: String? = null,
    variant: CssStyleVariant<ButtonLinkKind>? = null,
    borderRadius: DsBorderRadius? = null,
    size: ButtonSize = ButtonSize.MD,
    openInternalLinksStrategy: OpenLinkStrategy? = null,
    openExternalLinksStrategy: OpenLinkStrategy? = null,
    updateHistoryMode: UpdateHistoryMode? = null,
    content: @Composable RowScope.() -> Unit
) {
    Link(
        path = path,
        modifier = ButtonLinkStyle.toModifier(variant)
            .then(size.toModifier())
            .thenIfNotNull(id) { Modifier.id(it) }
            .thenIfNotNull(borderRadius) {
                Modifier.borderRadius(it.topLeft, it.topRight, it.bottomRight, it.bottomLeft)
            }
            .then(modifier),
        openInternalLinksStrategy = openInternalLinksStrategy,
        openExternalLinksStrategy = openExternalLinksStrategy,
        updateHistoryMode = updateHistoryMode
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

// ElevatedButtonLink
@Composable
fun ElevatedButtonLink(
    path: String,
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonLinkKind> = ElevatedButtonLinkVariant,
    size: ButtonSize = ButtonSize.MD,
    openInternalLinksStrategy: OpenLinkStrategy? = null,
    openExternalLinksStrategy: OpenLinkStrategy? = null,
    updateHistoryMode: UpdateHistoryMode? = null,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButtonLink(
        path = path,
        modifier = modifier,
        id = id,
        variant = variant,
        borderRadius = borderRadius,
        size = size,
        openInternalLinksStrategy = openInternalLinksStrategy,
        openExternalLinksStrategy = openExternalLinksStrategy,
        updateHistoryMode = updateHistoryMode,
        content = content
    )
}

// FilledButtonLink
@Composable
fun FilledButtonLink(
    path: String,
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonLinkKind> = FilledButtonLinkVariant,
    size: ButtonSize = ButtonSize.MD,
    openInternalLinksStrategy: OpenLinkStrategy? = null,
    openExternalLinksStrategy: OpenLinkStrategy? = null,
    updateHistoryMode: UpdateHistoryMode? = null,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButtonLink(
        path = path,
        modifier = modifier,
        id = id,
        variant = variant,
        size = size,
        borderRadius = borderRadius,
        openInternalLinksStrategy = openInternalLinksStrategy,
        openExternalLinksStrategy = openExternalLinksStrategy,
        updateHistoryMode = updateHistoryMode,
        content = content
    )
}

// OutlinedButtonLink
@Composable
fun OutlinedButtonLink(
    path: String,
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonLinkKind> = OutlinedButtonLinkVariant,
    size: ButtonSize = ButtonSize.MD,
    openInternalLinksStrategy: OpenLinkStrategy? = null,
    openExternalLinksStrategy: OpenLinkStrategy? = null,
    updateHistoryMode: UpdateHistoryMode? = null,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButtonLink(
        path = path,
        modifier = modifier,
        id = id,
        variant = variant,
        size = size,
        borderRadius = borderRadius,
        openInternalLinksStrategy = openInternalLinksStrategy,
        openExternalLinksStrategy = openExternalLinksStrategy,
        updateHistoryMode = updateHistoryMode,
        content = content
    )
}

// FilledTonalButtonLink
@Composable
fun FilledTonalButtonLink(
    path: String,
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonLinkKind> = FilledTonalButtonLinkVariant,
    size: ButtonSize = ButtonSize.MD,
    openInternalLinksStrategy: OpenLinkStrategy? = null,
    openExternalLinksStrategy: OpenLinkStrategy? = null,
    updateHistoryMode: UpdateHistoryMode? = null,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButtonLink(
        path = path,
        modifier = modifier,
        id = id,
        variant = variant,
        borderRadius = borderRadius,
        size = size,
        openInternalLinksStrategy = openInternalLinksStrategy,
        openExternalLinksStrategy = openExternalLinksStrategy,
        updateHistoryMode = updateHistoryMode,
        content = content
    )
}

// TextButtonLink
@Composable
fun TextButtonLink(
    path: String,
    modifier: Modifier = Modifier,
    id: String? = null,
    borderRadius: DsBorderRadius? = null,
    variant: CssStyleVariant<ButtonLinkKind> = TextButtonLinkVariant,
    size: ButtonSize = ButtonSize.MD,
    openInternalLinksStrategy: OpenLinkStrategy? = null,
    openExternalLinksStrategy: OpenLinkStrategy? = null,
    updateHistoryMode: UpdateHistoryMode? = null,
    content: @Composable RowScope.() -> Unit
) {
    DsBaseButtonLink(
        path = path,
        modifier = modifier,
        id = id,
        variant = variant,
        size = size,
        borderRadius = borderRadius,
        openInternalLinksStrategy = openInternalLinksStrategy,
        openExternalLinksStrategy = openExternalLinksStrategy,
        updateHistoryMode = updateHistoryMode,
        content = content
    )
}
