package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.columnGap
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.materialdesignsystem.toColorScheme
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Composable
fun DsExternalLink(
    href: String,
    text: String,
    icon: @Composable () -> Unit
) {
    Anchor(
        href = href,
        attrs = DsExternalLinkStyle.toModifier().toAttrs()
    ) {
        Row(
            modifier = Modifier.columnGap(12.px),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Text(text)
        }
    }
}

// When a Text is in an "A", the Text will automatically be seen as a Link and get the default Link style.
// We don't need to add specific child styles (on the id) but rather just the default style.
val DsExternalLinkStyle = CssStyle {
    base {
        Modifier.textDecorationLine(TextDecorationLine.None)
            .color(colorMode.toColorScheme.onBackground)
    }

    hover {
        Modifier
            .textDecorationLine(TextDecorationLine.Underline)
            .color(colorMode.toColorScheme.primary)
    }
}
