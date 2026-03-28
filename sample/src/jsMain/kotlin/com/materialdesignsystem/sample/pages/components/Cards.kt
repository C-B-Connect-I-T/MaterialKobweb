package com.materialdesignsystem.sample.pages.components

import androidx.compose.runtime.*
import com.materialdesignsystem.components.widgets.DsCard
import com.materialdesignsystem.sample.components.layouts.PageLayout
import com.materialdesignsystem.toColorScheme
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*

@Page
@Composable
fun CardsPage() {
    PageLayout(title = "Cards") {
        val colorScheme = ColorMode.current.toColorScheme
        
        Column(modifier = Modifier.gap(3.cssRem)) {
            ComponentSection(title = "Basic Cards") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    DsCard(
                        modifier = Modifier.width(300.px).padding(1.5.cssRem)
                    ) {
                        Column(modifier = Modifier.gap(0.5.cssRem)) {
                            SpanText(
                                text = "Card Title",
                                modifier = Modifier
                                    .fontSize(1.2.cssRem)
                                    .fontWeight(FontWeight.Bold)
                                    .color(colorScheme.onSurface)
                            )
                            SpanText(
                                text = "This is a basic card component with some content inside.",
                                modifier = Modifier
                                    .fontSize(1.cssRem)
                                    .color(colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                    
                    DsCard(
                        modifier = Modifier.width(300.px).padding(1.5.cssRem)
                    ) {
                        Column(modifier = Modifier.gap(0.5.cssRem)) {
                            SpanText(
                                text = "Another Card",
                                modifier = Modifier
                                    .fontSize(1.2.cssRem)
                                    .fontWeight(FontWeight.Bold)
                                    .color(colorScheme.onSurface)
                            )
                            SpanText(
                                text = "Cards are great for grouping related content and actions.",
                                modifier = Modifier
                                    .fontSize(1.cssRem)
                                    .color(colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                    
                    DsCard(
                        modifier = Modifier.width(300.px).padding(1.5.cssRem)
                    ) {
                        Column(modifier = Modifier.gap(0.5.cssRem)) {
                            SpanText(
                                text = "Flexible Layout",
                                modifier = Modifier
                                    .fontSize(1.2.cssRem)
                                    .fontWeight(FontWeight.Bold)
                                    .color(colorScheme.onSurface)
                            )
                            SpanText(
                                text = "You can put any composable content inside a card.",
                                modifier = Modifier
                                    .fontSize(1.cssRem)
                                    .color(colorScheme.onSurfaceVariant)
                            )
                        }
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

