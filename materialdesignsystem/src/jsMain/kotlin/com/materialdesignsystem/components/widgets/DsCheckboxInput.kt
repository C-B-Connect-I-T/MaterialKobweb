package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.Appearance
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.appearance
import com.varabyte.kobweb.compose.ui.modifiers.aspectRatio
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.flexDirection
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.checked
import com.varabyte.kobweb.silk.style.selectors.focus
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import com.materialdesignsystem.components.UniqueIdGenerator
import com.materialdesignsystem.toColorScheme
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text

sealed interface CheckboxInputKind : ComponentKind

val CheckedInputStyle = CssStyle<CheckboxInputKind> {
    base {
        Modifier
            .appearance(Appearance.None)
            .backgroundColor(colorMode.toColorScheme.surfaceContainer)
            .size(1.em)
            .borderRadius(.25.em)
            .border(1.px, LineStyle.Solid, colorMode.toColorScheme.primary.toRgb().copyf(alpha = 0.6f))
            .transition(
                Transition.group(
                    listOf("background-color", "border-color"),
                    duration = TransitionDurationVars.Normal.value()
                )
            )
    }

    checked {
        Modifier
            .border(1.px, LineStyle.Solid, colorMode.toColorScheme.primary)
            .backgroundColor(colorMode.toColorScheme.primary)
            .backgroundImage(url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 20'%3e%3cpath fill='none' stroke='%23fff' stroke-linecap='round' stroke-linejoin='round' stroke-width='3' d='m6 10 3 3 6-6'/%3e%3c/svg%3e"))
    }

    focus {
        Modifier
            .boxShadow(0.px, 0.px, 0.px, 0.25.em, colorMode.toColorScheme.primary.toRgb().copyf(alpha = 0.25f))
    }
}

@Composable
fun DsCheckboxInput(
    modifier: Modifier = Modifier,
    id: String? = null,
    label: String,
    reverse: Boolean = false,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    val randomId = remember {
        id ?: UniqueIdGenerator.generateUniqueId("checkbox")
    }
    Div(
        attrs = modifier
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .gap(0.5.cssRem) // spacing between checkbox and label
            .flexDirection(if (reverse) FlexDirection.RowReverse else FlexDirection.Row)
            .toAttrs()
    ) {
        Input(
            attrs = CheckedInputStyle.toModifier()
                .id(randomId)
                .aspectRatio(1)
                .cursor(Cursor.Pointer)
                .toAttrs {
                    checked(checked)
                    onChange {
                        onCheckedChange(it.value)
                    }
                },
            type = InputType.Checkbox,
        )

        Label(
            attrs = Modifier
                .toAttrs(),
            forId = randomId
        ) {
            Text(value = label)
        }
    }
}
