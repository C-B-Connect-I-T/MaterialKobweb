package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.materialdesignsystem.extensions.ButtonSizeXL
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import org.jetbrains.compose.web.css.minus

@Composable
fun DsFloatingActionButton(
    modifier: Modifier,
    size: ButtonSize = ButtonSizeXL,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    FilledIconButton(
        modifier = modifier,
        size = size,
        onClick = onClick,
        content = content
    )
}

@Composable
fun DsAddFloatingActionButton(
    modifier: Modifier,
    size: ButtonSize = ButtonSizeXL,
    onClick: () -> Unit
) {
    DsFloatingActionButton(
        modifier,
        size,
        onClick = onClick,
        content = {
            DsMaterialSymbols(
                modifier = Modifier.fontSize(ButtonVars.Height.value() - ButtonVars.PaddingHorizontal.value()),
                icon = "add"
            )
        }
    )
}