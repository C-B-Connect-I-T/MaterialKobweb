package com.materialkobweb.sample.pages.components

import androidx.compose.runtime.*
import com.materialkobweb.components.toast.ToastContainer
import com.materialkobweb.components.toast.ToastManager
import com.materialkobweb.components.toast.ToastPosition
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
fun ToastsPage() {
    PageLayout(title = "Toasts") {
        var selectedPosition by remember { mutableStateOf(ToastPosition.DEFAULT) }
        val positionOptions = remember {
            ToastPosition.entries.map { position ->
                position to position.name
                    .lowercase()
                    .split("_")
                    .joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }
            }
        }

        Column(modifier = Modifier.gap(3.cssRem)) {
            ComponentSection(title = "Toast Types") {
                Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap)) {
                    FilledButton(
                        size = ButtonSize.SM,
                        onClick = {
                            ToastManager.success(
                                title = "Success",
                                message = "The action completed successfully."
                            )
                        }
                    ) { SpanText("Success") }

                    FilledButton(
                        size = ButtonSize.SM,
                        onClick = {
                            ToastManager.error(
                                title = "Error",
                                message = "Something went wrong. Please try again."
                            )
                        }
                    ) { SpanText("Error") }

                    FilledButton(
                        size = ButtonSize.SM,
                        onClick = {
                            ToastManager.warning(
                                title = "Warning",
                                message = "Review this change before continuing."
                            )
                        }
                    ) { SpanText("Warning") }

                    FilledButton(
                        size = ButtonSize.SM,
                        onClick = {
                            ToastManager.info(
                                title = "Info",
                                message = "Here is an informational update."
                            )
                        }
                    ) { SpanText("Info") }
                }
            }

            ComponentSection(title = "Toast Position") {
                Column(modifier = Modifier.gap(1.cssRem).maxWidth(320.px)) {
                    DsSelect(
                        preselectedItem = positionOptions.first { it.first == selectedPosition }.second,
                        onItemSelect = { _, item ->
                            selectedPosition = positionOptions.firstOrNull { it.second == item }?.first ?: selectedPosition
                        },
                        label = "Position",
                        items = positionOptions.map { it.second }
                    )

                    TextButton(
                        size = ButtonSize.SM,
                        onClick = { ToastManager.clear() }
                    ) {
                        SpanText("Clear Active Toasts")
                    }
                }
            }
        }

        ToastContainer(position = selectedPosition)
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
