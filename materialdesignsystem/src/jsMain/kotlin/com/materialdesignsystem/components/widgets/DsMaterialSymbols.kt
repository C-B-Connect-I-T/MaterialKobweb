package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

enum class MaterialSymbolType(val className: String) {
    Sharp("material-symbols-sharp"),
    Outlined("material-symbols-outlined"),
    Rounded("material-symbols-rounded"),
}

@Composable
fun DsMaterialSymbols(
    modifier: Modifier = Modifier,
    icon: String,
    type: MaterialSymbolType = MaterialSymbolType.Outlined,
    weight: Int = 300,
    fill: Boolean = false,
    color: Color? = null
) {
    Span(
        attrs = modifier
            .thenIf(color != null) { Modifier.color(color!!) }
            .classNames(type.className)
            .styleModifier {
                property("font-variation-settings", "'wght' $weight, 'FILL' ${if (fill) 1 else 0}")
            }
            .toAttrs()
    ) {
        Text(icon)
    }
}
