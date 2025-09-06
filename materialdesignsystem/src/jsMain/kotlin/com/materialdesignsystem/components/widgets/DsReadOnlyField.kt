package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Composable
fun DsReadOnlyField(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.px)
    ) {
        Text(label)
        Box(
            modifier = Modifier
                .background(Color.rgb(245, 245, 245))
                .border(1.px, LineStyle.Solid, Colors.LightGray)
                .borderRadius(0.375.cssRem)
                .padding(
                    top = 0.625.cssRem,
                    bottom = 0.625.cssRem,
                    left = 0.625.cssRem,
                    right = 0.px

                )
                .fillMaxWidth()
        ) {
            Text(value)
        }
    }
}
