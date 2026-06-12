package com.materialkobweb.sample.pages.components

import androidx.compose.runtime.*
import com.materialkobweb.components.widgets.*
import com.materialkobweb.sample.components.layouts.PageLayout
import com.materialkobweb.toColorScheme
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*

@Page
@Composable
fun ButtonLinksPage() {
    PageLayout(title = "Button Links") {
        Column(modifier = Modifier.gap(3.cssRem)) {
            // Filled Button Links
            ComponentSection(title = "Filled Button Links") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    FilledButtonLink(
                        path = "",
                        size = ButtonSize.SM,
                    ) {
                        SpanText("Small")
                    }

                    FilledButtonLink(
                        path = "",
                    ) {
                        SpanText("Default")
                    }

                    FilledButtonLink(
                        path = "",
                        size = ButtonSize.LG,
                    ) {
                        SpanText("Large")
                    }

                    FilledButtonLink(
                        path = "",
                    ) {
                        SpanText("Disabled")
                    }
                }
            }

            // Filled Tonal Button Links
            ComponentSection(title = "Filled Tonal Button Links") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    FilledTonalButtonLink(
                        path = "",
                        size = ButtonSize.SM,
                    ) {
                        SpanText("Small")
                    }

                    FilledTonalButtonLink(
                        path = "",
                    ) {
                        SpanText("Default")
                    }

                    FilledTonalButtonLink(
                        path = "",
                        size = ButtonSize.LG,
                    ) {
                        SpanText("Large")
                    }

                    FilledTonalButtonLink(
                        path = "",
                    ) {
                        SpanText("Disabled")
                    }
                }
            }

            // Outlined Button Links
            ComponentSection(title = "Outlined Button Links") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    OutlinedButtonLink(
                        path = "",
                        size = ButtonSize.SM,
                    ) {
                        SpanText("Small")
                    }

                    OutlinedButtonLink(
                        path = "",
                    ) {
                        SpanText("Default")
                    }

                    OutlinedButtonLink(
                        path = "",
                        size = ButtonSize.LG,
                    ) {
                        SpanText("Large")
                    }

                    OutlinedButtonLink(
                        path = "",
                    ) {
                        SpanText("Disabled")
                    }
                }
            }

            // Text Button Links
            ComponentSection(title = "Text Button Links") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    TextButtonLink(
                        path = "",
                        size = ButtonSize.SM,
                    ) {
                        SpanText("Small")
                    }

                    TextButtonLink(
                        path = "",
                    ) {
                        SpanText("Default")
                    }

                    TextButtonLink(
                        path = "",
                        size = ButtonSize.LG,
                    ) {
                        SpanText("Large")
                    }

                    TextButtonLink(
                        path = "",
                    ) {
                        SpanText("Disabled")
                    }
                }
            }

            // Elevated Button Links
            ComponentSection(title = "Elevated Button Links") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    ElevatedButtonLink(
                        path = "",
                        size = ButtonSize.SM,
                    ) {
                        SpanText("Small")
                    }

                    ElevatedButtonLink(
                        path = "",
                    ) {
                        SpanText("Default")
                    }

                    ElevatedButtonLink(
                        path = "",
                        size = ButtonSize.LG,
                    ) {
                        SpanText("Large")
                    }

                    ElevatedButtonLink(
                        path = "",
                    ) {
                        SpanText("Disabled")
                    }
                }
            }

//            // Danger Filled Button
//            ComponentSection(title = "Danger Buttons") {
//                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
//                    DangerFilledButtonLink(
//                        path = "",
//                    ) {
//                        SpanText("Delete")
//                    }
//
//                    DangerFilledButtonLink(
//                        path = "",
//                    ) {
//                        SpanText("Disabled")
//                    }
//                }
//            }
        }
    }
}

@Composable
private fun ComponentSection(
    title: String,
    content: @Composable () -> Unit
) {
    val colorScheme = ColorMode.current.toColorScheme

    Column(modifier = Modifier.gap(1.cssRem)) {
        SpanText(
            text = title,
            modifier = Modifier
                .fontSize(1.5.cssRem)
                .fontWeight(FontWeight.SemiBold)
                .color(colorScheme.onSurface)
        )
        content()
    }
}

