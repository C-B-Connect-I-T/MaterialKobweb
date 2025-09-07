package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.materialdesignsystem.extensions.ButtonSizeXL
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.silk.components.forms.ButtonKind
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.style.CssStyleVariant
import org.jetbrains.compose.web.css.minus

@Composable
fun DsFloatingActionButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    variant: CssStyleVariant<ButtonKind>? = null,
    enabled: Boolean = true,
    size: ButtonSize = ButtonSizeXL,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    DsBaseIconButton(
        modifier = modifier,
        id = id,
        variant = variant,
        size = size,
        enabled = enabled,
        onClick = onClick,
        content = content
    )
}

@Composable
fun DsAddFloatingActionButton(
    modifier: Modifier = Modifier,
    id: String? = null,
    variant: CssStyleVariant<ButtonKind>? = null,
    enabled: Boolean = true,
    size: ButtonSize = ButtonSizeXL,
    onClick: () -> Unit
) {
    DsFloatingActionButton(
        modifier = modifier,
        id = id,
        variant = variant,
        size = size,
        enabled = enabled,
        onClick = onClick,
        content = {
            DsMaterialSymbols(
                modifier = Modifier.fontSize(ButtonVars.Height.value() - ButtonVars.PaddingHorizontal.value()),
                icon = "add"
            )
        }
    )
}