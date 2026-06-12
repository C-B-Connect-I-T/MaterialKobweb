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
fun ButtonsPage() {
    PageLayout(title = "Buttons") {
        Column(modifier = Modifier.gap(3.cssRem)) {
            // Filled Buttons
            ComponentSection(title = "Filled Buttons") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    FilledButton(
                        size = ButtonSize.SM,
                        onClick = { console.log("Small button clicked") }
                    ) {
                        SpanText("Small")
                    }

                    FilledButton(
                        onClick = { console.log("Filled button clicked") }
                    ) {
                        SpanText("Default")
                    }

                    FilledButton(
                        size = ButtonSize.LG,
                        onClick = { console.log("Large button clicked") }
                    ) {
                        SpanText("Large")
                    }
                    
                    FilledButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        SpanText("Disabled")
                    }
                }
            }
            
            // Filled Tonal Buttons
            ComponentSection(title = "Filled Tonal Buttons") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    FilledTonalButton(
                        size = ButtonSize.SM,
                        onClick = { console.log("Small tonal button clicked") }
                    ) {
                        SpanText("Small")
                    }

                    FilledTonalButton(
                        onClick = { console.log("Tonal button clicked") }
                    ) {
                        SpanText("Default")
                    }

                    FilledTonalButton(
                        size = ButtonSize.LG,
                        onClick = { console.log("Large tonal button clicked") }
                    ) {
                        SpanText("Large")
                    }
                    
                    FilledTonalButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        SpanText("Disabled")
                    }
                }
            }
            
            // Outlined Buttons
            ComponentSection(title = "Outlined Buttons") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    OutlinedButton(
                        size = ButtonSize.SM,
                        onClick = { console.log("Small outlined button clicked") }
                    ) {
                        SpanText("Small")
                    }

                    OutlinedButton(
                        onClick = { console.log("Outlined button clicked") }
                    ) {
                        SpanText("Default")
                    }

                    OutlinedButton(
                        size = ButtonSize.LG,
                        onClick = { console.log("Large outlined button clicked") }
                    ) {
                        SpanText("Large")
                    }
                    
                    OutlinedButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        SpanText("Disabled")
                    }
                }
            }
            
            // Text Buttons
            ComponentSection(title = "Text Buttons") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    TextButton(
                        size = ButtonSize.SM,
                        onClick = { console.log("Small text button clicked") }
                    ) {
                        SpanText("Small")
                    }

                    TextButton(
                        onClick = { console.log("Text button clicked") }
                    ) {
                        SpanText("Default")
                    }

                    TextButton(
                        size = ButtonSize.LG,
                        onClick = { console.log("Large text button clicked") }
                    ) {
                        SpanText("Large")
                    }
                    
                    TextButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        SpanText("Disabled")
                    }
                }
            }
            
            // Elevated Buttons
            ComponentSection(title = "Elevated Buttons") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    ElevatedButton(
                        size = ButtonSize.SM,
                        onClick = { console.log("Small elevated button clicked") }
                    ) {
                        SpanText("Small")
                    }

                    ElevatedButton(
                        onClick = { console.log("Elevated button clicked") }
                    ) {
                        SpanText("Default")
                    }

                    ElevatedButton(
                        size = ButtonSize.LG,
                        onClick = { console.log("Large elevated button clicked") }
                    ) {
                        SpanText("Large")
                    }
                    
                    ElevatedButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        SpanText("Disabled")
                    }
                }
            }
            
            // Danger Filled Button
            ComponentSection(title = "Danger Buttons") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    DangerFilledButton(
                        onClick = { console.log("Danger button clicked") }
                    ) {
                        SpanText("Delete")
                    }
                    
                    DangerFilledButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        SpanText("Disabled")
                    }
                }
            }
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

