package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.Appearance
import com.varabyte.kobweb.compose.css.BackgroundPosition
import com.varabyte.kobweb.compose.css.BackgroundRepeat
import com.varabyte.kobweb.compose.css.BackgroundSize
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.Edge
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.TransformOrigin
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionBehavior
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.disabled
import com.varabyte.kobweb.compose.css.functions.calc
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.appearance
import com.varabyte.kobweb.compose.ui.modifiers.ariaDisabled
import com.varabyte.kobweb.compose.ui.modifiers.ariaInvalid
import com.varabyte.kobweb.compose.ui.modifiers.ariaRequired
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.backgroundPosition
import com.varabyte.kobweb.compose.ui.modifiers.backgroundRepeat
import com.varabyte.kobweb.compose.ui.modifiers.backgroundSize
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexDirection
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.order
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.paddingInline
import com.varabyte.kobweb.compose.ui.modifiers.pointerEvents
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transform
import com.varabyte.kobweb.compose.ui.modifiers.transformOrigin
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.thenIfNotNull
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.disabled
import com.varabyte.kobweb.silk.style.selectors.focus
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.selectors.not
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.BorderColorVar
import com.varabyte.kobweb.silk.style.vars.size.BorderRadiusVars
import com.varabyte.kobweb.silk.style.vars.size.FontSizeVars
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.materialdesignsystem.components.UniqueIdGenerator
import com.materialdesignsystem.styles.bordered
import com.materialdesignsystem.styles.checkMark
import com.materialdesignsystem.styles.chevronDown
import com.materialdesignsystem.styles.errorMark
import com.materialdesignsystem.styles.invalidFeedbackStyle
import com.materialdesignsystem.styles.validFeedbackStyle
import com.materialdesignsystem.toColorScheme
import org.jetbrains.compose.web.attributes.required
import org.jetbrains.compose.web.attributes.selected
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundImage
import org.jetbrains.compose.web.css.backgroundPosition
import org.jetbrains.compose.web.css.backgroundSize
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.plus
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Select
import org.jetbrains.compose.web.dom.Text

sealed interface SelectKind : ComponentKind

object SelectDefaults {
    const val Enabled = true
    const val Required = false
    val Size = SelectSize.MD
    val Variant = OutlinedSelectStyle
}

object SelectVars {
    val FontSize by StyleVariable<CSSLengthNumericValue>(prefix = "silk")
    val Height by StyleVariable<CSSLengthNumericValue>(prefix = "silk")
    val Padding by StyleVariable<CSSLengthNumericValue>(prefix = "silk")
    val BorderRadius by StyleVariable<CSSLengthNumericValue>(prefix = "silk")
}

class SelectSize(
    fontSize: CSSLengthNumericValue,
    height: CSSLengthNumericValue,
    padding: CSSLengthNumericValue,
    borderRadius: CSSLengthNumericValue
) : CssStyle.Restricted.Base(
    Modifier
        .setVariable(SelectVars.FontSize, fontSize)
        .setVariable(SelectVars.Height, height)
        .setVariable(SelectVars.Padding, padding)
        .setVariable(SelectVars.BorderRadius, borderRadius)
) {
    companion object {
        val XS = SelectSize(FontSizeVars.XS.value(), 1.25.cssRem, 0.375.cssRem, BorderRadiusVars.XS.value())
        val SM = SelectSize(FontSizeVars.SM.value(), 1.75.cssRem, 0.5.cssRem, BorderRadiusVars.SM.value())
        val MD = SelectSize(FontSizeVars.MD.value(), 2.25.cssRem, 0.625.cssRem, BorderRadiusVars.MD.value())

        // Border radius intentionally same as MD
        val LG = SelectSize(FontSizeVars.LG.value(), 2.5.cssRem, 0.75.cssRem, BorderRadiusVars.MD.value())
    }
}

val SelectStyle = CssStyle<SelectKind> {
    base {
        Modifier
            .appearance(Appearance.None)
            .height(SelectVars.Height.value())
            .display(DisplayStyle.Block)
            .fontSize(SelectVars.FontSize.value())
            .fontWeight(400)
            .backgroundImage(url(chevronDown))
            .backgroundRepeat(BackgroundRepeat.NoRepeat)
            .backgroundPosition(BackgroundPosition.of(CSSPosition(Edge.Right(0.75.cssRem), Edge.CenterY)))
            .backgroundSize(BackgroundSize.of(16.px, 12.px))
            .outline(0.px, LineStyle.Solid, Colors.Transparent)
            .border(0.px, LineStyle.Solid, Colors.Transparent)
            .transition(Transition.group(properties = listOf("border-color", "box-shadow"), duration = 0.15.s, timingFunction = TransitionTimingFunction.EaseInOut, delay = 0.s, TransitionBehavior.Normal))
    }
}

private fun Modifier.selectPadding(): Modifier {
    val padding = SelectVars.Padding.value()
    return this.paddingInline(start = padding, end = padding)
}

val OutlinedSelectStyle = SelectStyle.addVariant {
    base {
        Modifier
            .selectPadding()
            .borderRadius(SelectVars.BorderRadius.value())
            .border(1.px, LineStyle.Solid, BorderColorVar.value())
    }

    (hover + not(disabled)) { Modifier.border { color(Colors.Gray) } } // TODO: Use a better color here!!
    (focus + not(disabled)) { Modifier.bordered(colorMode.toColorScheme.primary) } // TODO: maybe use a variable color here!!
}

private fun baseValidationStyle(
    color: Color,
    validationMark: String
): Modifier {
    val firstPosition = BackgroundPosition.of(CSSPosition(Edge.Right(0.75.cssRem), Edge.CenterY))
    val secondPosition = BackgroundPosition.of(CSSPosition(Edge.Right(2.25.cssRem), Edge.CenterY))

    return Modifier
        .border { color(color) }
        .padding { right(calc { 1.5.em + .75.em }) }
        .styleModifier {
            backgroundImage("""url("$chevronDown"), url("$validationMark")""".trimIndent())
            backgroundPosition("$firstPosition, $secondPosition")
            backgroundSize("16px 12px, ${calc { .75.em + .375.em }} ${calc { .75.em + .375.em }}")
        }
}

val validSelectStyle = CssStyle {
    base {
        baseValidationStyle(color = Colors.ForestGreen, validationMark = checkMark)
    }

    focus {
        Modifier.bordered(Colors.ForestGreen)
    }
}

val invalidSelectStyle = CssStyle {
    base {
        baseValidationStyle(color = colorMode.toColorScheme.error, validationMark = errorMark)
    }

    focus {
        Modifier.bordered(colorMode.toColorScheme.error)
    }
}

val UnstyledSelectStyle = SelectStyle.addVariant {}

val SelectFormFloating = CssStyle {
    base {
        Modifier.position(Position.Relative)
    }
    cssRule("> select") {
        Modifier
            .padding {
                top(1.625.cssRem)
                bottom(.625.cssRem)
            }
            .height(calc { 2.px + 1.75.cssRem + SelectVars.Height.value() })
            .lineHeight(1.25)
    }

    cssRule("> label") {
        Modifier
            .position(Position.Absolute)
            .top(0.px)
            .left(0.px)
            .fillMaxHeight()
            .padding(1.cssRem, SelectVars.Padding.value())
            .pointerEvents(PointerEvents.None)
            .border(1.px, LineStyle.Solid, Colors.Transparent)
            .transformOrigin(TransformOrigin.of(0.px, 0.px))
            .transition(
                Transition.group(properties = listOf("opacity", "transform"), duration = 0.1.s, timingFunction = TransitionTimingFunction.EaseInOut, delay = 0.s, TransitionBehavior.Normal)
            )
    }

    cssRule("> select ~ label, > input:not(:placeholder-shown) ~ label") {
        Modifier
            .opacity(0.65)
            .transform {
                scale(0.85)
                translateY((-0.5).cssRem)
                translateX(.15.cssRem)
            }
    }
}

/**
 * This component allows you to make a single selection from a list of predefined
 * options. Select components are widely used for various purposes, such as selecting
 * a country in a registration form, choosing a product category in an e-commerce site, etc.
 * @param id A unique identifier of the component.
 * @param items Here you define a list of items to be displayed inside this component.
 * @param placeholder Placeholder that that will be displayed by default.
 * @param size The size of the component.
 * @param enabled Whether this component should be enabled or not.
 * @param floating Whether to use the floating style of the component.
 * @param label The label that will be displayed on top of the component
 * @param onItemSelect Lambda which is triggered when you select an option from this
 * component. It provides an index value and the text of the selected option.
 * */
@Composable
fun DsSelect(
    modifier: Modifier = Modifier,
    id: String? = null,
    variant: CssStyleVariant<SelectKind>? = SelectDefaults.Variant,
    size: SelectSize = SelectDefaults.Size,
    items: List<String>,
    preselectedItem: String? = null,
    placeholder: String? = null,
    enabled: Boolean = SelectDefaults.Enabled,
    required: Boolean = SelectDefaults.Required,
    valid: Boolean? = null,
    validText: String? = null,
    invalidText: String? = null,
    floating: Boolean = false,
    label: String? = null,
    onItemSelect: (Int, String?) -> Unit
) {
    val randomId = remember {
        id ?: UniqueIdGenerator.generateUniqueId("select")
    }

    Div(
        attrs = size.toModifier()
            .thenIf(floating) {
                SelectFormFloating.toModifier()
            }.thenIf(!floating) {
                Modifier
                    .display(DisplayStyle.Flex)
                    .flexDirection(FlexDirection.Column)
            }
            .then(modifier)
            .toAttrs()
    ) {
        DsSelectInternal(
            modifier = Modifier.order(if (floating) 0 else 1),
            id = randomId,
            variant = variant,
            items = items,
            preselectedItem = preselectedItem,
            placeholder = placeholder,
            enabled = enabled,
            required = required,
            valid = valid,
            validText = validText,
            invalidText = invalidText,
            onItemSelect = onItemSelect
        )

        if (label != null) {
            // In floating mode, the label should come after the `select` component in the DOM
            // so that it can use the sibling selector to move when the select is focused
            // or has a value. In non-floating mode, it should come before to be read
            Label(
                attrs = Modifier
                    .margin(bottom = 0.5.cssRem)
                    .toAttrs(),
                forId = randomId
            ) {
                Text(label)
            }
        }
    }
}

@Composable
private fun DsSelectInternal(
    modifier: Modifier = Modifier,
    id: String,
    variant: CssStyleVariant<SelectKind>? = SelectDefaults.Variant,
    items: List<String>,
    preselectedItem: String? = null,
    placeholder: String? = null,
    enabled: Boolean = SelectDefaults.Enabled,
    required: Boolean = SelectDefaults.Required,
    valid: Boolean? = null,
    validText: String? = null,
    invalidText: String? = null,
    onItemSelect: (Int, String?) -> Unit
) {
    val colorScheme = ColorMode.current.toColorScheme

    Select(
        attrs = SelectStyle
            .toModifier(variant)
            .fillMaxWidth()
            .id(id)
            .backgroundColor(colorScheme.surfaceContainer)
            .color(colorScheme.onSurface)
            .thenIfNotNull(valid) {
                if (it) {
                    validSelectStyle.toModifier()
                } else {
                    invalidSelectStyle.toModifier()
                        .ariaInvalid()
                }
            }
            .thenIf(!enabled) { Modifier.ariaDisabled() }
            .thenIf(required) { Modifier.ariaRequired() }
            .then(modifier)
            .toAttrs {
                if (!enabled) disabled()
                if (required) required()

                onChange {
                    it.value?.let { text ->
                        if (text != placeholder) {
                            onItemSelect(items.indexOf(text), text)
                        } else {
                            onItemSelect(-1, null) // Placeholder selected
                        }
                    }
                }
            }
    ) {
        if (!placeholder.isNullOrEmpty()) {
            Option(
                attrs = Modifier.toAttrs { if (preselectedItem == null) selected() },
                value = placeholder
            ) {
                Text(placeholder)
            }
        }
        items.forEach { text ->
            Option(
                attrs = Modifier.toAttrs { if (text == preselectedItem) selected() },
                value = text
            ) {
                Text(value = text)
            }
        }
    }

    val modifier = if (valid == true && !validText.isNullOrBlank()) {
        validFeedbackStyle.toModifier()
    } else if (valid == false && !invalidText.isNullOrBlank()) {
        invalidFeedbackStyle.toModifier()
    } else null

    if (modifier != null && valid != null && (!validText.isNullOrBlank() || !invalidText.isNullOrBlank())) {
        Div(attrs = modifier.toAttrs()) {
            Text(value = if (valid) validText ?: "" else invalidText ?: "")
        }
    }
}

