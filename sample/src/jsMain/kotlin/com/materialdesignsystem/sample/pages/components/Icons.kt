package com.materialdesignsystem.sample.pages.components

import androidx.compose.runtime.*
import com.materialdesignsystem.components.widgets.*
import com.materialdesignsystem.sample.components.layouts.PageLayout
import com.materialdesignsystem.toColorScheme
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
fun IconsPage() {
    PageLayout(title = "Icons") {
        val colorScheme = ColorMode.current.toColorScheme
        
        Column(modifier = Modifier.gap(3.cssRem)) {
            // Material Symbols
            ComponentSection(title = "Material Symbols") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    DsMaterialSymbols(
                        icon = "home",
                        modifier = Modifier.fontSize(2.cssRem).color(colorScheme.onSurface)
                    )
                    DsMaterialSymbols(
                        icon = "search",
                        modifier = Modifier.fontSize(2.cssRem).color(colorScheme.onSurface)
                    )
                    DsMaterialSymbols(
                        icon = "settings",
                        modifier = Modifier.fontSize(2.cssRem).color(colorScheme.onSurface)
                    )
                    DsMaterialSymbols(
                        icon = "favorite",
                        modifier = Modifier.fontSize(2.cssRem).color(colorScheme.error)
                    )
                    DsMaterialSymbols(
                        icon = "star",
                        modifier = Modifier.fontSize(2.cssRem).color(colorScheme.primary)
                    )
                }
            }
            
            // Icon Buttons
            ComponentSection(title = "Icon Buttons - Filled") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    FilledIconButton(
                        onClick = { console.log("Home clicked") }
                    ) {
                        DsMaterialSymbols(icon = "home")
                    }
                    
                    FilledIconButton(
                        onClick = { console.log("Search clicked") }
                    ) {
                        DsMaterialSymbols(icon = "search")
                    }
                    
                    FilledIconButton(
                        onClick = { console.log("Settings clicked") }
                    ) {
                        DsMaterialSymbols(icon = "settings")
                    }
                    
                    FilledIconButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        DsMaterialSymbols(icon = "lock")
                    }
                }
            }
            
            ComponentSection(title = "Icon Buttons - Outlined") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    OutlinedIconButton(
                        onClick = { console.log("Edit clicked") }
                    ) {
                        DsMaterialSymbols(icon = "edit")
                    }
                    
                    OutlinedIconButton(
                        onClick = { console.log("Delete clicked") }
                    ) {
                        DsMaterialSymbols(icon = "delete")
                    }
                    
                    OutlinedIconButton(
                        onClick = { console.log("Share clicked") }
                    ) {
                        DsMaterialSymbols(icon = "share")
                    }
                    
                    OutlinedIconButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        DsMaterialSymbols(icon = "block")
                    }
                }
            }
            
            ComponentSection(title = "Icon Buttons - Elevated") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    ElevatedIconButton(
                        onClick = { console.log("Add clicked") }
                    ) {
                        DsMaterialSymbols(icon = "add")
                    }
                    
                    ElevatedIconButton(
                        onClick = { console.log("Remove clicked") }
                    ) {
                        DsMaterialSymbols(icon = "remove")
                    }
                    
                    ElevatedIconButton(
                        onClick = { console.log("Download clicked") }
                    ) {
                        DsMaterialSymbols(icon = "download")
                    }
                    
                    ElevatedIconButton(
                        enabled = false,
                        onClick = {}
                    ) {
                        DsMaterialSymbols(icon = "upload")
                    }
                }
            }
            
            ComponentSection(title = "Floating Action Button") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    DsFloatingActionButton(
                        variant = FilledIconButtonStyle,
                        onClick = { console.log("FAB clicked") }
                    ) {
                        DsMaterialSymbols(icon = "add", modifier = Modifier.fontSize(1.5.cssRem))
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

