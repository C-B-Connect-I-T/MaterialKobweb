package com.materialdesignsystem.sample.pages

import androidx.compose.runtime.*
import com.materialdesignsystem.sample.components.layouts.PageLayout
import com.materialdesignsystem.toColorScheme
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*

@Page("/index")
@Composable
fun HomePage() {
    PageLayout(title = "Component Gallery") {
        val colorScheme = ColorMode.current.toColorScheme

        SpanText(
            text = "Welcome to the Material Design System Sample",
            modifier = Modifier
                .fontSize(1.2.cssRem)
                .color(colorScheme.onSurface)
                .margin(bottom = 2.cssRem)
        )

        SpanText(
            text = "Explore the component categories:",
            modifier = Modifier
                .fontSize(1.cssRem)
                .color(colorScheme.onSurfaceVariant)
                .margin(bottom = 1.cssRem)
        )

        Column(modifier = Modifier.gap(1.cssRem)) {
            ComponentCard(
                title = "Buttons",
                description = "Explore various button styles including filled, outlined, text, and icon buttons.",
                path = "/components/buttons",
                colorScheme = colorScheme
            )

            ComponentCard(
                title = "Cards",
                description = "Card components for displaying grouped content.",
                path = "/components/cards",
                colorScheme = colorScheme
            )

            ComponentCard(
                title = "Inputs",
                description = "Text fields, selects, checkboxes, and other input components.",
                path = "/components/inputs",
                colorScheme = colorScheme
            )

            ComponentCard(
                title = "Icons",
                description = "Material symbols and icon buttons.",
                path = "/components/icons",
                colorScheme = colorScheme
            )
        }
    }
}

@Composable
private fun ComponentCard(
    title: String,
    description: String,
    path: String,
    colorScheme: com.materialdesignsystem.theme.ColorScheme
) {
    Link(
        path = path,
        modifier = Modifier
            .textDecorationLine(TextDecorationLine.None)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.surfaceContainerLow)
                .borderRadius(12.px)
                .padding(1.5.cssRem)
                .border(1.px, LineStyle.Solid, colorScheme.outlineVariant)
        ) {
            Column(modifier = Modifier.gap(0.5.cssRem)) {
                SpanText(
                    text = title,
                    modifier = Modifier
                        .fontSize(1.3.cssRem)
                        .fontWeight(FontWeight.Bold)
                        .color(colorScheme.onSurface)
                )

                SpanText(
                    text = description,
                    modifier = Modifier
                        .fontSize(1.cssRem)
                        .color(colorScheme.onSurfaceVariant)
                )
            }
        }
    }
}

