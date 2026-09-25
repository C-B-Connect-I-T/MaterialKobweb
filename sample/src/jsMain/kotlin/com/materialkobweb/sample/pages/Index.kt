package com.materialkobweb.sample.pages

import androidx.compose.runtime.*
import com.materialkobweb.sample.components.layouts.PageLayout
import com.materialkobweb.styles.MaterialColorVars
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*

@Page("/index")
@Composable
fun HomePage() {
    PageLayout(title = "Component Gallery") {
        SpanText(
            text = "Welcome to the Material Design System Sample",
            modifier = Modifier
                .fontSize(1.2.cssRem)
                .color(MaterialColorVars.OnSurface.value())
                .margin(bottom = 2.cssRem)
        )

        SpanText(
            text = "Explore the component categories:",
            modifier = Modifier
                .fontSize(1.cssRem)
                .color(MaterialColorVars.OnSurfaceVariant.value())
                .margin(bottom = 1.cssRem)
        )

        Column(modifier = Modifier.gap(1.cssRem)) {
            ComponentCard(
                title = "Buttons",
                description = "Explore various button styles including filled, outlined, text, and icon buttons.",
                path = "/components/buttons"
            )

            ComponentCard(
                title = "Button Links",
                description = "Navigate using button-styled links for seamless routing within your application.",
                path = "/components/button-links"
            )

            ComponentCard(
                title = "Cards",
                description = "Card components for displaying grouped content.",
                path = "/components/cards"
            )

            ComponentCard(
                title = "Inputs",
                description = "Text fields, selects, checkboxes, and other input components.",
                path = "/components/inputs"
            )

            ComponentCard(
                title = "Icons",
                description = "Material symbols and icon buttons.",
                path = "/components/icons"
            )

            ComponentCard(
                title = "Toasts",
                description = "Display brief messages to the user with our customizable toast notifications.",
                path = "/components/toasts"
            )
        }
    }
}

@Composable
private fun ComponentCard(
    title: String,
    description: String,
    path: String
) {
    Link(
        path = path,
        modifier = Modifier
            .textDecorationLine(TextDecorationLine.None)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialColorVars.SurfaceContainerLow.value())
                .borderRadius(12.px)
                .padding(1.5.cssRem)
                .border(1.px, LineStyle.Solid, MaterialColorVars.OutlineVariant.value())
        ) {
            Column(modifier = Modifier.gap(0.5.cssRem)) {
                SpanText(
                    text = title,
                    modifier = Modifier
                        .fontSize(1.3.cssRem)
                        .fontWeight(FontWeight.Bold)
                        .color(MaterialColorVars.OnSurface.value())
                )

                SpanText(
                    text = description,
                    modifier = Modifier
                        .fontSize(1.cssRem)
                        .color(MaterialColorVars.OnSurfaceVariant.value())
                )
            }
        }
    }
}

