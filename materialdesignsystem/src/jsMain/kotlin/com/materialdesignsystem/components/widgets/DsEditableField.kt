package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onFocusOut
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.forms.Input
import com.varabyte.kobweb.silk.components.forms.InputDefaults
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.materialdesignsystem.constants.Attributes
import com.materialdesignsystem.toColorScheme
import org.jetbrains.compose.web.attributes.AutoComplete
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text

@Suppress("LongParameterList")
@Composable
fun DsEditableField(
    modifier: Modifier = Modifier,
    id: String,
    label: String,
    placeholder: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = InputDefaults.ReadOnly,
    required: Boolean = InputDefaults.Required,
    valid: Boolean = InputDefaults.Valid,
    backgroundColor: CSSColorValue? = null,
    focusBorderColor: CSSColorValue? = null,
    autoComplete: AutoComplete? = null,
    type: InputType<String> = InputType.Text,
    onCommit: () -> Unit = {},
) {
    val colorScheme = ColorMode.current.toColorScheme
    val shouldValidate = remember { mutableStateOf(false) }
    // State for toggling password visibility
    var isPasswordVisible by remember { mutableStateOf(false) }

    // TODO: Investigate the variants!!
    //  What is the `InputGroupStyle`? Should `OutlinedInputVariant` be something I need?
    Column(
        modifier = modifier
    ) {
        Label(
            forId = id,
            attrs = Modifier
                .margin(bottom = 0.5.cssRem)
                .toAttrs()
        ) {
            Text(label)
        }

        Box(
            Modifier
                .fillMaxWidth()
                .position(Position.Relative)
        ) {
            Input(
                modifier = Modifier
                    .id(id)
                    .fillMaxWidth()
                    .onFocusOut {
                        shouldValidate.value = true
                    }
                    .padding(
                        left = 0.625.cssRem,
                        right = if (type == InputType.Password) 40.px else 0.px
                    )
                    .backgroundColor(backgroundColor ?: colorScheme.surfaceContainer)
                    .color(colorScheme.onSurface)
                    .attrsModifier {
                        attr(Attributes.Name, label)
                    },
                type = if (isPasswordVisible) InputType.Text else type,
                autoComplete = autoComplete,
                value = value,
                onValueChange = onValueChange,
                required = required,
                readOnly = readOnly,
                valid = if (shouldValidate.value) valid else InputDefaults.Valid,
                placeholder = placeholder,
                focusBorderColor = focusBorderColor ?: colorScheme.primary.toRgb().copyf(alpha = 0.6f),
                onCommit = onCommit
            )

            if (type == InputType.Password && !readOnly) {
                // Add a toggle button (simple text or icon)
                IconButton(
                    modifier = Modifier
                        .margin(top = 2.px)
                        .position(Position.Absolute)
                        .right(2.px),
                    size = ButtonSize.SM,
                    borderRadius = DsBorderRadius(6.px),
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    DsMaterialSymbols(
                        modifier = Modifier.fontSize(ButtonVars.Height.value() - ButtonVars.PaddingHorizontal.value()),
                        icon = if (isPasswordVisible) "visibility_off" else "visibility",
                    )
                }
            }
        }
    }
}
