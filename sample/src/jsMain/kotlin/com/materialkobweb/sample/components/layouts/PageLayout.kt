package com.materialkobweb.sample.components.layouts

import androidx.compose.runtime.*
import com.materialkobweb.components.widgets.DsMaterialSymbols
import com.materialkobweb.components.widgets.IconButton
import com.materialkobweb.toColorScheme
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*

@Composable
fun PageLayout(title: String, content: @Composable () -> Unit) {
    var colorMode by ColorMode.currentState
    val colorScheme = ColorMode.current.toColorScheme
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.surfaceContainer)
                .padding(1.cssRem)
                .borderBottom(1.px, LineStyle.Solid, colorScheme.outline)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().gap(2.cssRem),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Link(
                    path = "/",
                    modifier = Modifier.textDecorationLine(TextDecorationLine.None)
                ) {
                    SpanText(
                        text = "Material Design System",
                        modifier = Modifier
                            .fontSize(1.5.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .color(colorScheme.onSurface)
                    )
                }
                
                Link(path = "/components/buttons", modifier = Modifier.color(colorScheme.onSurface)) {
                    SpanText("Buttons")
                }
                Link(path = "/components/cards", modifier = Modifier.color(colorScheme.onSurface)) {
                    SpanText("Cards")
                }
                Link(path = "/components/inputs", modifier = Modifier.color(colorScheme.onSurface)) {
                    SpanText("Inputs")
                }
                Link(path = "/components/icons", modifier = Modifier.color(colorScheme.onSurface)) {
                    SpanText("Icons")
                }

                Spacer() // Push the toggle button to the right

                // Color mode toggle button
                IconButton(
                    onClick = {
                        colorMode = colorMode.opposite
                    }
                ) {
                    DsMaterialSymbols(
                        icon = if (colorMode.isDark) "light_mode" else "dark_mode",
                        modifier = Modifier.color(colorScheme.onSurface)
                    )
                }
            }
        }
        
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.cssRem)
        ) {
            SpanText(
                text = title,
                modifier = Modifier
                    .fontSize(2.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .color(colorScheme.onSurface)
                    .margin(bottom = 2.cssRem)
            )
            
            content()
        }
    }
}

