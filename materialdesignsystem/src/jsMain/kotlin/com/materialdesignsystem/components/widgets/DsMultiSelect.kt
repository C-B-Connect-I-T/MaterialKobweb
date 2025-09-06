package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Appearance
import com.varabyte.kobweb.compose.css.BackgroundPosition
import com.varabyte.kobweb.compose.css.BackgroundRepeat
import com.varabyte.kobweb.compose.css.BackgroundSize
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Edge
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionBehavior
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.css.disabled
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignContent
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
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.columnGap
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.paddingInline
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.rowGap
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.tabIndex
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.userSelect
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.thenIfNotNull
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
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
import com.varabyte.kobweb.silk.theme.shapes.RectF
import com.varabyte.kobweb.silk.theme.shapes.clip
import com.materialdesignsystem.extensions.maxLines
import com.materialdesignsystem.styles.bordered
import com.materialdesignsystem.styles.chevronDown
import com.materialdesignsystem.styles.invalidFeedbackStyle
import com.materialdesignsystem.styles.validFeedbackStyle
import com.materialdesignsystem.toColorScheme
import kotlinx.browser.document
import org.jetbrains.compose.web.css.AlignContent
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.events.Event

sealed interface MultiSelectKind : ComponentKind

object MultiSelectDefaults {
    const val Enabled = true
    const val Required = false
    val Size = MultiSelectSize.MD
    val Variant = OutlinedMultiSelectStyle
}

object MultiSelectVars {
    val FontSize by StyleVariable<CSSLengthNumericValue>(prefix = "silk")
    val Height by StyleVariable<CSSLengthNumericValue>(prefix = "silk")
    val Padding by StyleVariable<CSSLengthNumericValue>(prefix = "silk")
    val BorderRadius by StyleVariable<CSSLengthNumericValue>(prefix = "silk")
}

class MultiSelectSize(
    fontSize: CSSLengthNumericValue,
    height: CSSLengthNumericValue,
    padding: CSSLengthNumericValue,
    borderRadius: CSSLengthNumericValue
) : CssStyle.Restricted.Base(
    Modifier
        .setVariable(MultiSelectVars.FontSize, fontSize)
        .setVariable(MultiSelectVars.Height, height)
        .setVariable(MultiSelectVars.Padding, padding)
        .setVariable(MultiSelectVars.BorderRadius, borderRadius)
) {
    companion object {
        val XS = MultiSelectSize(FontSizeVars.XS.value(), 1.25.cssRem, 0.375.cssRem, BorderRadiusVars.XS.value())
        val SM = MultiSelectSize(FontSizeVars.SM.value(), 1.75.cssRem, 0.5.cssRem, BorderRadiusVars.SM.value())
        val MD = MultiSelectSize(FontSizeVars.MD.value(), 2.25.cssRem, 0.625.cssRem, BorderRadiusVars.MD.value())

        // Border radius intentionally same as MD
        val LG = MultiSelectSize(FontSizeVars.LG.value(), 2.5.cssRem, 0.75.cssRem, BorderRadiusVars.MD.value())
    }
}

val MultiSelectStyle = CssStyle<MultiSelectKind> {
    base {
        Modifier
            .appearance(Appearance.None)
            .height(MultiSelectVars.Height.value())
            .display(DisplayStyle.Block)
            .fontSize(MultiSelectVars.FontSize.value())
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
    val padding = MultiSelectVars.Padding.value()
    return this.paddingInline(start = padding, end = padding)
}

val OutlinedMultiSelectStyle = MultiSelectStyle.addVariant {
    base {
        Modifier
            .selectPadding()
            .borderRadius(MultiSelectVars.BorderRadius.value())
            .border(1.px, LineStyle.Solid, BorderColorVar.value())
    }

    (hover + not(disabled)) { Modifier.border { color(Colors.Gray) } } // TODO: Use a better color here!!
    (focus + not(disabled)) { Modifier.bordered(colorMode.toColorScheme.primary) } // TODO: maybe use a variable color here!!
}


@Composable
fun DsMultiSelect(
    modifier: Modifier = Modifier,
    id: String,
    variant: CssStyleVariant<MultiSelectKind> = MultiSelectDefaults.Variant,
    size: MultiSelectSize = MultiSelectDefaults.Size,
    label: String,
    placeholder: String,
    items: List<String>,
    valid: Boolean? = null,
    validText: String? = null,
    invalidText: String? = null,
    enabled: Boolean = SelectDefaults.Enabled,
    required: Boolean = SelectDefaults.Required,
    selectedItems: List<String> = emptyList(),
    onItemSelect: (String) -> Unit
) {
    val colorScheme = ColorMode.current.toColorScheme
    var showDropDownMenu by remember { mutableStateOf(false) }

    // Handle "click outside"
    DisposableEffect(showDropDownMenu) {
        if (showDropDownMenu) {
            val listener: (Event) -> Unit = { event ->
                val target = event.target as? org.w3c.dom.Element
                // Get the element that wraps the custom select and the dropdown menu
                // Because the select input has 2 parentElements, we need to go up twice
                val dropdownEl = document.getElementById(id)?.parentElement?.parentElement

                if (target != null && dropdownEl != null) {
                    if (!dropdownEl.contains(target)) {
                        showDropDownMenu = false
                    }
                }
            }
            document.addEventListener("click", listener)

            onDispose {
                document.removeEventListener("click", listener)
            }
        } else {
            onDispose { }
        }
    }

    Column(
        modifier = size.toModifier().fillMaxWidth()
    ) {
        Label(
            attrs = Modifier
                .margin(bottom = 0.5.cssRem)
                .toAttrs(),
            forId = id
        ) {
            Text(value = label)
        }

        Box(
            modifier = Modifier.fillMaxWidth()
                .position(Position.Relative)
                .display(DisplayStyle.InlineBlock),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = MultiSelectStyle
                        .toModifier(variant)
                        .fillMaxWidth()
                        .id(id)
                        .tabIndex(0)
                        .backgroundColor(colorScheme.surfaceContainer)
                        .color(colorScheme.onSurface)
                        .thenIfNotNull(valid) {
                            if (it) {
                                validSelectStyle.toModifier()
                            } else {
                                invalidSelectStyle.toModifier().ariaInvalid()
                            }
                        }
                        .thenIf(!enabled) { Modifier.ariaDisabled() }
                        .thenIf(required) { Modifier.ariaRequired() }
                        .then(modifier)
                        .alignContent(AlignContent.Center)
                        .onClick { showDropDownMenu = !showDropDownMenu }
                        .attrsModifier {
                            if (!enabled) disabled()
                            if (required) attr("required", "")
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val format = if (selectedItems.isEmpty()) {
                        placeholder
                    } else {
                        items.filter { selectedItems.contains(it) }.joinToString(", ")
                    }

                    Span(
                        attrs = Modifier
                            .id("$id-dropBtn")
                            .maxLines(1)
                            .margin(top = 0.px, bottom = 0.px, left = 0.px, right = 24.px)
                            .userSelect(UserSelect.None)
                            .cursor(Cursor.Pointer)
                            .toAttrs()
                    ) {
                        Text(value = format)
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

            Box(
                modifier = Modifier
                    .display(if (showDropDownMenu) DisplayStyle.Flex else DisplayStyle.None)
                    .margin(top = 8.px)
                    .position(Position.Absolute)
                    .backgroundColor(colorScheme.surfaceVariant)
                    .borderRadius(16.px)
                    .color(colorScheme.onSurfaceVariant)
                    .boxShadow(0.px, 8.px, 16.px, 0.px, Colors.Black.copyf(alpha = 0.2f))
                    .zIndex(1)
            ) {
                SimpleGrid(
                    numColumns = numColumns(1, 2, 3),
                    modifier = Modifier
                        .clip(RectF(16.px))
                        .columnGap(100.px)
                        .rowGap(10.px)
                        .padding(12.px),
                ) {
                    items.forEach { tag ->
                        DsCheckboxInput(
                            label = tag,
                            checked = selectedItems.contains(tag),
                            onCheckedChange = { onItemSelect(tag) }
                        )
                    }
                }
            }
        }
    }
}
