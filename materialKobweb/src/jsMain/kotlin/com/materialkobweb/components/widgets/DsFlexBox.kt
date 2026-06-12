package com.materialkobweb.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.BoxScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.flexDirection
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.px

@Composable
fun DsFlexBox(
    modifier: Modifier = Modifier,
    flexDirection: FlexDirection = FlexDirection.Row,
    justifyContent: JustifyContent = JustifyContent.FlexStart,
    alignItems: AlignItems = AlignItems.Stretch,
    gap: CSSLengthOrPercentageNumericValue = 0.px,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val flexModifier = modifier
        .display(DisplayStyle.Flex)
        .flexDirection(flexDirection)
        .justifyContent(justifyContent)
        .alignItems(alignItems)
        .gap(gap)

    Box(
        modifier = flexModifier,
        content = content
    )
}
