package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Appearance
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.appearance
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.heightIn
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.paddingInline
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.spellCheck
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.widthIn
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.InputSize
import com.varabyte.kobweb.silk.components.forms.InputVars
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssPrefix
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.ariaInvalid
import com.varabyte.kobweb.silk.style.selectors.disabled
import com.varabyte.kobweb.silk.style.selectors.focusVisible
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.selectors.not
import com.varabyte.kobweb.silk.style.selectors.placeholder
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.materialdesignsystem.constants.Attributes
import com.materialdesignsystem.constants.ClassNames
import com.materialdesignsystem.constants.Identifiers
import com.materialdesignsystem.constants.Properties
import com.materialdesignsystem.toColorScheme
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.required
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextArea

@CssPrefix("silk")
val TextAreaStyle = CssStyle<TextAreaKind> {
    base {
        Modifier
            .appearance(Appearance.None) // Disable browser styles
            .color(ColorVar.value())
            .height(InputVars.Height.value())
            .fontSize(InputVars.FontSize.value())
            .backgroundColor(Colors.Transparent)
            .outline(0.px, LineStyle.Solid, Colors.Transparent) // Disable, we'll use box shadow instead
            .border(0.px, LineStyle.Solid, Colors.Transparent) // Overridden by variants
            .transition(
                transitions = Transition.group(
                    properties = listOf(Properties.BorderColor, Properties.BoxShadow, Properties.BackgroundColor),
                    duration = InputVars.ColorTransitionDuration.value()
                )
            )
    }

    placeholder {
        Modifier
            .opacity(InputVars.PlaceholderOpacity.value())
            .color(InputVars.PlaceholderColor.value())
    }
}

sealed interface TextAreaKind : ComponentKind

val OutlinedTextAreaVariant = TextAreaStyle.addVariant {
    fun Modifier.bordered(color: CSSColorValue): Modifier {
        return this.border(1.px, LineStyle.Solid, color).boxShadow(spreadRadius = 1.px, color = color)
    }

    base {
        Modifier
            .padding(InputVars.Padding.value())
            .borderRadius(InputVars.BorderRadius.value())
            .border(1.px, LineStyle.Solid, InputVars.BorderColor.value())
    }

    ariaInvalid { Modifier.bordered(InputVars.BorderInvalidColor.value()) }
    (hover + not(disabled)) { Modifier.border { color(InputVars.BorderHoverColor.value()) } }
    (focusVisible + not(disabled)) { Modifier.bordered(InputVars.BorderFocusColor.value()) }
}

@Composable
fun DsEditableArea(
    modifier: Modifier = Modifier,
    id: String,
    label: String,
    placeholder: String,
    value: String,
    variant: CssStyleVariant<TextAreaKind> = OutlinedTextAreaVariant,
    onValueChange: (String) -> Unit
) {
    val colorScheme = ColorMode.current.toColorScheme

    Column(
        modifier = modifier
    ) {
        Label(
            attrs = Modifier
                .margin(bottom = 0.5.cssRem)
                .toAttrs(),
            forId = id
        ) {
            Text(value = label)
        }

        TextArea(
            value = value,
            attrs = TextAreaStyle
                .toModifier(variant)
                .then(InputSize.MD.toModifier())
                .id(id)
                .fontFamily("inherit")
                .setVariable(
                    InputVars.BorderFocusColor,
                    colorScheme.primary.toRgb().copyf(alpha = 0.6f)
                )
                .setVariable(InputVars.BorderInvalidColor, null)
                .fillMaxWidth()
                .height(100.px)
                .heightIn(50.px, 280.px)
                .widthIn(100.percent, 100.percent)
                .backgroundColor(colorScheme.surfaceContainer)
                .spellCheck(true)
                .toAttrs {
                    attr(Attributes.Name, "Message")
                    required()
                    placeholder(placeholder)
                    onInput { onValueChange(it.value) }
                }
        )
    }
}
