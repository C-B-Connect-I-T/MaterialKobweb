package com.materialdesignsystem.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backdropFilter
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.translateX
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.materialdesignsystem.components.widgets.DsMaterialSymbols
import com.materialdesignsystem.constants.Constants
import com.materialdesignsystem.constants.Identifiers
import com.materialdesignsystem.constants.ListenerTypes
import com.materialdesignsystem.constants.Properties
import com.materialdesignsystem.toColorScheme
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.times
import org.jetbrains.compose.web.css.vh

@Composable
fun BaseHeader(
    backgroundColor: Color,
    content: @Composable RowScope.() -> Unit
) {
    var scroll: Double? by remember { mutableStateOf(null) }
    val colorMode by ColorMode.currentState

    LaunchedEffect(Unit) {
        window.addEventListener(type = ListenerTypes.Scroll, callback = {
            scroll = document.documentElement?.scrollTop
        })
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Constants.HEADER_HEIGHT.px)
            .top(0.percent) // Make it work with sticky!
            .position(Position.Sticky)
            .zIndex(1)
            .backgroundImage(
                linearGradient(
                    backgroundColor,
                    backgroundColor.toRgb().copyf(alpha = 0.5f),
                    LinearGradient.Direction.ToBottom
                )
            )
            .backdropFilter(blur(5.px))
            .thenIf((scroll ?: 0.0) >= 50) {
                Modifier.boxShadow(0.px, 1.px, 5.px, 0.px, colorMode.toColorScheme.primary)
            },
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    icon: String? = null,
    href: String,
    onClick: (() -> Unit)? = null
) {
    Link(
        path = href,
        variant = UndecoratedLinkVariant,
        modifier = modifier.thenIf(onClick != null) {
            Modifier.onClick {
                onClick?.invoke()
            }
        }
    ) {
        Row(
            modifier = NavigationItemStyle.toModifier()
//                .then(modifier)
                .cursor(Cursor.Pointer),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (icon != null) {
                DsMaterialSymbols(
                    modifier = Modifier
                        .id(Identifiers.vectorIcon)
                        .size(24.px)
                        .margin(right = 10.px),
                    icon = icon,
                    color = if (selected) ColorMode.current.toColorScheme.primary else null
                )
            }

            SpanText(
                modifier = Modifier
                    .id(Identifiers.navigationText)
                    .fontSize(16.px)
                    .thenIf(selected) {
                        Modifier.color(ColorMode.current.toColorScheme.primary)
                            .fontWeight(FontWeight.Bold)
                    },
                text = title
            )
        }
    }
}

@Composable
fun OverflowMenu(
    onMenuClosed: () -> Unit,
    content: @Composable (closeMenu: () -> Unit) -> Unit
) {
    val scope = rememberCoroutineScope()
    val breakpoint = rememberBreakpoint()
    var translateX by remember { mutableStateOf((-100).percent) }
    var opacity by remember { mutableStateOf(0.percent) }

    fun CoroutineScope.closeMenu() {
        launch {
            translateX = (-100).percent
            opacity = 0.percent
            delay(500)
            onMenuClosed()
        }
    }

    LaunchedEffect(breakpoint) {
        delay(100) // This delay is needed for the translateX
        translateX = 0.percent
        opacity = 100.percent

        if (breakpoint >= Breakpoint.MD) {
            scope.closeMenu()
        }
    }

    Overlay(
        modifier = Modifier
            .zIndex(2)
            .opacity(opacity)
            .transition(
                Transition.of(Properties.Opacity, 500.ms)
            )
            .onClick { scope.closeMenu() }) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.vh)
                .position(Position.Fixed)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(leftRight = 25.px, bottom = 25.px)
                    .width(if (breakpoint < Breakpoint.MD) 50.percent else 35.percent)
                    .minWidth(300.px)
                    .overflow(Overflow.Auto)
                    .scrollBehavior(ScrollBehavior.Smooth)
                    .backgroundColor(ColorMode.current.toColorScheme.background)
                    .translateX(tx = translateX)
                    .transition(Transition.of(Properties.Translate, 500.ms))
            ) {
                Row(
                    modifier = Modifier
                        .height(Constants.HEADER_HEIGHT.px * 2)
                        .margin(bottom = 60.px),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FaXmark(
                        modifier = Modifier
                            .cursor(Cursor.Pointer)
                            .margin(right = 20.px, bottom = 3.px)
                            .onClick {
                                scope.closeMenu()
                            },
                        size = IconSize.LG
                    )

                    Image(
                        modifier = Modifier.height(Constants.HEADER_COLLAPSED_LOGO_HEIGHT.px),
                        src = "/logo.svg",
                        alt = "Logo image"
                    )
                }

                content { scope.closeMenu() }
            }
        }
    }
}

val NavigationItemStyle = CssStyle {
    cssRule(" > #${Identifiers.vectorIcon}") {
        Modifier
            .transition(Transition.of(property = TransitionProperty.All, duration = 300.ms))
            .color(colorMode.toColorScheme.onSecondaryContainer)
            .styleModifier {
                property("stroke", colorMode.toColorScheme.onSecondaryContainer)
            }
    }

    cssRule(":hover > #${Identifiers.vectorIcon}") {
        Modifier
            .color(colorMode.toColorScheme.primary)
            .styleModifier {
                property("stroke", colorMode.toColorScheme.primary)
            }
    }

    cssRule(" > #${Identifiers.navigationText}") {
        Modifier
            .transition(Transition.of(property = TransitionProperty.All, duration = 300.ms))
            .color(colorMode.toColorScheme.onSecondaryContainer)
    }

    cssRule(":hover > #${Identifiers.navigationText}") {
        Modifier.color(colorMode.toColorScheme.primary)
            .fontWeight(FontWeight.Bold)
    }
}
