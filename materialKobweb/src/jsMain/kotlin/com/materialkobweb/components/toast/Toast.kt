package com.materialkobweb.components.toast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.materialkobweb.toColorScheme
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.overflowWrap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.translateX
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import kotlin.time.Duration.Companion.milliseconds

val SlideIn = Keyframes {
    from { Modifier.translateX((100).percent).opacity(0) }
    to { Modifier.translateX((0).percent).opacity(1) }
}

val SlideOut = Keyframes {
    from { Modifier.translateX((0).percent).opacity(1) }
    to { Modifier.translateX((120).percent).opacity(0) }
}

data class ToastColors(
    val successColor: Color,
    val onSuccessColor: Color,
    val warningColor: Color,
    val onWarningColor: Color,
    val errorColor: Color,
    val onErrorColor: Color,
    val infoColor: Color,
    val onInfoColor: Color
)

object ToastDefaults {
    val colors = ToastColors(
        successColor = Color.rgb(0x4CAF50),
        onSuccessColor = Color.rgb(0xFFFFFF),
        warningColor = Color.rgb(0xFF9800),
        onWarningColor = Color.rgb(0xFFFFFF),
        errorColor = Color.rgb(0xF44336),
        onErrorColor = Color.rgb(0xFFFFFF),
        infoColor = Color.rgb(0x2196F3),
        onInfoColor = Color.rgb(0xFFFFFF)
    )
}

/**
 * Individual toast notification component with slide-in/slide-out animations.
 *
 * @param toast The toast data to display
 * @param onDismiss Callback when the toast should be dismissed
 */
@Composable
fun Toast(
    toast: ToastData,
    onDismiss: () -> Unit
) {
    val colorMode = ColorMode.current
    val colorScheme = colorMode.toColorScheme

    var isVisible by remember { mutableStateOf(false) }
    var isExiting by remember { mutableStateOf(false) }

    // Auto-hide after duration
    LaunchedEffect(toast.id) {
        // Trigger slide-in animation
        delay(50.milliseconds)
        isVisible = true

        // Wait for auto-hide duration
        delay(ToastManager.AUTO_HIDE_DURATION_MS.milliseconds)

        // Trigger slide-out animation
        isExiting = true
        delay(300.milliseconds) // Wait for animation to complete
        onDismiss()
    }

    val (containerColor, contentColor) = when (toast.type) {
        ToastType.SUCCESS -> ToastManager.colors.successColor to ToastManager.colors.onSuccessColor
        ToastType.ERROR -> ToastManager.colors.errorColor to ToastManager.colors.onErrorColor
        ToastType.WARNING -> ToastManager.colors.warningColor to ToastManager.colors.onWarningColor
        ToastType.INFO -> ToastManager.colors.infoColor to ToastManager.colors.onInfoColor
    }

    Box(
        modifier = Modifier
            .minWidth(280.px)
            .maxWidth(350.px)
            .backgroundColor(containerColor)
            .borderRadius(8.px)
            .boxShadow(
                offsetX = 0.px,
                offsetY = 4.px,
                blurRadius = 12.px,
                spreadRadius = 0.px,
                color = rgba(0, 0, 0, 0.15)
            )
            .padding(leftRight = 16.px, topBottom = 12.px)
            .margin(bottom = 8.px)
            .overflow(Overflow.Hidden) // Prevent horizontal overflow
            .overflowWrap(OverflowWrap.BreakWord)
            .then(
                when {
                    isExiting -> Modifier.animation(SlideOut.toAnimation(duration = 300.ms, timingFunction = AnimationTimingFunction.EaseOut, iterationCount = AnimationIterationCount.of(1)))
                    isVisible -> Modifier.animation(SlideIn.toAnimation(duration = 300.ms, timingFunction = AnimationTimingFunction.EaseIn, iterationCount = AnimationIterationCount.of(1)))
                    else -> Modifier.opacity(0)
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .gap(4.px)
        ) {
            // Title (only if provided)
            toast.title?.let { title ->
                SpanText(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .color(contentColor)
                        .fontSize(14.px)
                        .fontWeight(FontWeight.SemiBold)
                )
            }

            // Message
            SpanText(
                text = toast.message,
                modifier = Modifier
                    .fillMaxWidth()
                    .color(contentColor)
                    .fontSize(13.px)
                    .fontWeight(if (toast.title == null) FontWeight.Medium else FontWeight.Normal)
            )
        }
    }
}
