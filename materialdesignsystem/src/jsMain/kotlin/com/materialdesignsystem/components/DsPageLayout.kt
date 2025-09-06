package com.materialdesignsystem.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gridRow
import com.varabyte.kobweb.compose.ui.modifiers.gridTemplateRows
import kotlinx.browser.document
import org.jetbrains.compose.web.css.fr

@Composable
fun DsPageLayout(
    modifier: Modifier = Modifier,
    title: String,
    header: @Composable () -> Unit,
    overflowMenu: @Composable () -> Unit = {},
    overflowMenuOpened: Boolean = false,
    footer: @Composable () -> Unit,
    sidePanel: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    document.title = title

    Row(
        modifier = modifier.fillMaxSize()
    ) {
        sidePanel()

        // Create a box with two rows: the main content (fills as much space as it can) and the footer (which reserves
        // space at the bottom). "auto" means the use the height of the row. "1fr" means give the rest of the space to
        // that row. Since this box is set to *at least* 100%, the footer will always appear at least on the bottom but
        // can be pushed further down if the first row grows beyond the page.
        Box(
            modifier.fillMaxSize().gridTemplateRows { size(1.fr); size(auto) },
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                header()

                if (overflowMenuOpened) {
                    overflowMenu()
                }

                content()
            }

            // Associate the footer with the row that will get pushed off the bottom of the page if it can't fit.
            Box(Modifier.fillMaxWidth().gridRow(2, 3), content = { footer() })
        }
    }
}
