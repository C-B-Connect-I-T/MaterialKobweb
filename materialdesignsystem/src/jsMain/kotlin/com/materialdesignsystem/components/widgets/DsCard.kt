package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.foundation.layout.columnClasses
import com.varabyte.kobweb.compose.foundation.layout.rowClasses
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.columnGap
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.materialdesignsystem.toColorScheme
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A

@Composable
fun DsCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    contentColor: Color? = null,
    contentPadding: CSSLengthOrPercentageNumericValue = 30.px,
    selectableMode: Boolean = false,
    borderLineStyle: LineStyle = LineStyle.Solid,
    onCheckedChanged: (Boolean) -> Unit = {},
    onClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    val colorScheme = ColorMode.current.toColorScheme
    var checked by remember(selectableMode) { mutableStateOf(false) }

    Column(
        modifier = DsCardStyle.toModifier()
            .then(modifier)
            .background(backgroundColor ?: colorScheme.background)
            .color(contentColor ?: colorScheme.onBackground)
            .padding(contentPadding)
            .borderRadius(10.px)
            .border(
                width = if (selectableMode) 2.px else 1.px,
                style = if (selectableMode) LineStyle.Solid else borderLineStyle,
                color = if (selectableMode) colorScheme.primary else colorScheme.outline.toRgb().copyf(alpha = 0.5f)
            )
            .onClick {
                if (selectableMode) {
                    checked = !checked
                    onCheckedChanged(checked)
                } else {
                    onClick()
                }
            }
            .transition(Transition.of(TransitionProperty.All, 300.ms))
            .cursor(Cursor.Pointer),
        verticalArrangement = Arrangement.spacedBy(16.px),
        content = content
    )
}

// TODO: use a variant for the `create Card` style to easily switch between them...
// Had to use the name `DsCardStyle` to avoid conflicts with the Bootstrap `Card` css style...
val DsCardStyle = CssStyle {
    base {
        Modifier
            .scale(100.percent)
            .transition(Transition.of(property = TransitionProperty.All, duration = 100.ms))
    }

    hover {
        Modifier
            .boxShadow(
                offsetY = 0.px,
                offsetX = 0.px,
                blurRadius = 8.px,
                spreadRadius = 6.px,
                color = colorMode.toColorScheme.primary.toRgb().copyf(alpha = 0.15f)
            )
            .scale(102.percent)
    }
}

@Composable
fun DsCreateCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    contentColor: Color? = null,
    text: String,
    iconSize: CSSLengthOrPercentageNumericValue? = null,
    isColumn: Boolean = true,
    href: String? = null,
    onClick: () -> Unit = {}
) {
    if (href == null) {
        internalCard(
            modifier = modifier,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            text = text,
            iconSize = iconSize,
            isColumn = isColumn,
            onClick = onClick
        )
    } else {
        A(
            href = href,
            attrs = modifier
                .textDecorationLine(TextDecorationLine.None)
                .onClick { onClick() }
                .toAttrs(),
            content = {
                internalCard(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,
                    text = text,
                    iconSize = iconSize,
                    isColumn = isColumn
                )
            }
        )
    }
}

@Composable
private fun internalCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    contentColor: Color? = null,
    text: String,
    iconSize: CSSLengthOrPercentageNumericValue? = null,
    isColumn: Boolean = true,
    onClick: () -> Unit = {}
) = DsCard(
    modifier = modifier,
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    borderLineStyle = LineStyle.Dashed,
    contentPadding = 12.px,
    onClick = onClick
) {
    val colorScheme = ColorMode.current.toColorScheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .thenIf(isColumn, { Modifier.columnClasses(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) })
            .thenIf(!isColumn, { Modifier.rowClasses(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center).columnGap(16.px) })
    ) {
        DsMaterialSymbols(
            modifier = Modifier.fontSize(iconSize ?: 24.px).margin(bottom = if (isColumn) 6.px else 0.px),
            icon = "add_circle",
            color = colorScheme.outline
        )

        SpanText(
            modifier = Modifier
                .fillMaxWidth()
                .textAlign(if (isColumn) TextAlign.Center else TextAlign.Start),
            text = text
        )
    }
}
