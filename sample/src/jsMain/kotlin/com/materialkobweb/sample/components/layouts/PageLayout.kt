package com.materialkobweb.sample.components.layouts

import androidx.compose.runtime.*
import com.materialkobweb.components.widgets.DsMaterialSymbols
import com.materialkobweb.components.widgets.IconButton
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
import com.materialkobweb.sample.COLOR_MODE_KEY
import com.materialkobweb.styles.MaterialColorVars
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobweb.silk.theme.colors.saveToLocalStorage
import org.jetbrains.compose.web.css.*

@Composable
fun PageLayout(title: String, content: @Composable () -> Unit) {
    var colorMode by ColorMode.currentState
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialColorVars.Surface.value())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialColorVars.SurfaceContainer.value())
                .padding(1.cssRem)
                .borderBottom(1.px, LineStyle.Solid, MaterialColorVars.Outline.value())
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
                            .color(MaterialColorVars.OnSurface.value())
                    )
                }
                
                Link(path = "/components/buttons", modifier = Modifier.color(MaterialColorVars.OnSurface.value())) {
                    SpanText("Buttons")
                }
                Link(path = "/components/cards", modifier = Modifier.color(MaterialColorVars.OnSurface.value())) {
                    SpanText("Cards")
                }
                Link(path = "/components/inputs", modifier = Modifier.color(MaterialColorVars.OnSurface.value())) {
                    SpanText("Inputs")
                }
                Link(path = "/components/icons", modifier = Modifier.color(MaterialColorVars.OnSurface.value())) {
                    SpanText("Icons")
                }

                Spacer() // Push the toggle button to the right

                // Color mode toggle button
                IconButton(
                    onClick = {
                        colorMode = colorMode.opposite.also { it.saveToLocalStorage(COLOR_MODE_KEY) }
                    }
                ) {
                    DsMaterialSymbols(
                        icon = if (colorMode.isDark) "light_mode" else "dark_mode",
                        modifier = Modifier.color(MaterialColorVars.OnSurface.value())
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
                    .color(MaterialColorVars.OnSurface.value())
                    .margin(bottom = 2.cssRem)
            )
            
            content()
        }
    }
}

