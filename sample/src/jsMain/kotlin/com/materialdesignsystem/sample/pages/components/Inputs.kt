package com.materialdesignsystem.sample.pages.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.materialdesignsystem.components.widgets.DsCheckboxInput
import com.materialdesignsystem.components.widgets.DsEditableField
import com.materialdesignsystem.components.widgets.DsReadOnlyField
import com.materialdesignsystem.components.widgets.DsSelect
import com.materialdesignsystem.sample.components.layouts.PageLayout
import com.materialdesignsystem.toColorScheme
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun InputsPage() {
    PageLayout(title = "Inputs") {
        val colorScheme = ColorMode.current.toColorScheme
        var textFieldValue by remember { mutableStateOf("") }
        var readOnlyValue by remember { mutableStateOf("Read-only value") }
        var checkboxValue by remember { mutableStateOf(false) }
        var selectValue by remember { mutableStateOf("") }

        Column(modifier = Modifier.gap(3.cssRem)) {
            // Text Fields
            ComponentSection(title = "Editable Fields") {
                Column(modifier = Modifier.gap(1.cssRem).maxWidth(400.px)) {
                    DsEditableField(
                        id = "text_field",
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        label = "Text Field",
                        placeholder = "Enter text..."
                    )

                    DsEditableField(
                        id = "text_field_with_helper",
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        label = "With Helper Text",
                        placeholder = "Enter text...",
                        modifier = Modifier.margin(top = 1.cssRem)
                    )
                }
            }

            // Read-only Fields
            ComponentSection(title = "Read-only Fields") {
                Column(modifier = Modifier.gap(1.cssRem).maxWidth(400.px)) {
                    DsReadOnlyField(
                        value = readOnlyValue,
                        label = "Read-only Field"
                    )
                }
            }

            // Checkboxes
            ComponentSection(title = "Checkboxes") {
                Column(modifier = Modifier.gap(1.cssRem)) {
                    DsCheckboxInput(
                        checked = checkboxValue,
                        onCheckedChange = { checkboxValue = it },
                        label = "Checkbox Label"
                    )

                    DsCheckboxInput(
                        checked = true,
                        onCheckedChange = {},
                        label = "Checked Checkbox"
                    )

                    DsCheckboxInput(
                        checked = false,
                        onCheckedChange = {},
                        label = "Unchecked Checkbox"
                    )
                }
            }

            // Select Dropdown
            ComponentSection(title = "Select Dropdown") {
                Column(modifier = Modifier.gap(1.cssRem).maxWidth(400.px)) {
                    DsSelect(
                        preselectedItem = selectValue,
                        onItemSelect = { _, item -> if (item != null) selectValue = item },
                        label = "Select an option",
                        items = listOf(
                            "Option 1",
                            "Option 2",
                            "Option 3"
                        )
                    )
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

