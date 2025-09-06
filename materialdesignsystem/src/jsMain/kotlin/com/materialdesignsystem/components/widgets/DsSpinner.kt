package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.BoxSizing
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxSizing
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.rotate
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.materialdesignsystem.toColorScheme
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.dom.Div

val rotationKeyFrames = Keyframes {
    0.percent { Modifier.rotate(0.deg) }
    100.percent { Modifier.rotate(360.deg) }
}

@Composable
fun DsSpinner(
    modifier: Modifier = Modifier,
    size: SpinnerSize = SpinnerSize.Normal,
) {
    val colorScheme = ColorMode.current.toColorScheme

    Div(
        attrs = modifier
            .size(size.size)
            .border(size.borderWidth, LineStyle.Solid, colorScheme.primary)
            .borderBottom {
                color(Colors.Transparent)
            }
            .borderRadius(50.percent)
            .display(DisplayStyle.InlineBlock)
            .boxSizing(BoxSizing.BorderBox)
            .animation(
                rotationKeyFrames.toAnimation(
                    duration = 1.s,
                    timingFunction = AnimationTimingFunction.Linear,
                    iterationCount = AnimationIterationCount.Infinite
                )
            )
            .toAttrs()
    )
}

enum class SpinnerSize(val size: CSSLengthOrPercentageNumericValue, val borderWidth: CSSLengthNumericValue) {
    Small(
        size = 1.cssRem,
        borderWidth = 0.2.em
    ),
    Normal(
        size = 2.cssRem,
        borderWidth = 0.25.em
    ),
    Large(
        size = 3.cssRem,
        borderWidth = 0.25.em
    )
}
