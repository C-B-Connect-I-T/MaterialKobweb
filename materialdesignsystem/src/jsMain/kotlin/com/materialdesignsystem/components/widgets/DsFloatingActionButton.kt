package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.bottom
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.materialdesignsystem.extensions.ButtonSizeXL
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.px

@Composable
fun FGFloatingActionButton(
    onClick: () -> Unit
) {
    val breakpoint = rememberBreakpoint()

    FilledIconButton(
        modifier = Modifier
            .position(Position.Fixed)
            .right(if (breakpoint >= Breakpoint.MD) 100.px else 36.px)
            .bottom(150.px),
        size = ButtonSizeXL,
        onClick = onClick
    ) {
        DsMaterialSymbols(
            modifier = Modifier.fontSize(ButtonVars.Height.value() - ButtonVars.PaddingHorizontal.value()),
            icon = "add"
        )
    }
}
