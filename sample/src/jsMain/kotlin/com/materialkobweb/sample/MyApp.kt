package com.materialkobweb.sample

import androidx.compose.runtime.Composable
import com.materialkobweb.MaterialTheme
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.AppGlobals
import com.varabyte.kobweb.core.isExporting
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.loadFromLocalStorage
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import kotlinx.browser.document
import org.jetbrains.compose.web.css.vh

private const val COLOR_MODE_KEY = "materialKobweb:colorMode"

@InitSilk
fun initSilk(ctx: InitSilkContext) {
    // Initialize Material Theme
    MaterialTheme.setSchemes(lightScheme = KsLightColorScheme, darkScheme = KsDarkColorScheme)

    val colorMode = ColorMode.loadFromLocalStorage(COLOR_MODE_KEY) ?: ColorMode.systemPreference

    ctx.config.initialColorMode = colorMode

    // Script which runs at load time that needs to be kept in sync with `initialColorMode` above. This code checks
    // if the user's local color mode preference is different from what was exported by Kobweb, replacing it if
    // different to prevent a flash of color after the page loads.
    if (AppGlobals.isExporting) {
        val node = document.createElement("script").apply {
            textContent = """
                {
                    const storedColor = localStorage.getItem('${COLOR_MODE_KEY}'); // 'LIGHT', 'DARK', or null
                    const desiredColor = storedColor ? `silk-${'$'}{storedColor.toLowerCase()}` : 'silk-dark';
                    const oppositeColor = desiredColor === 'silk-dark' ? 'silk-light' : 'silk-dark';
                    document.documentElement.classList.replace(oppositeColor, desiredColor);
                }
                """.trimIndent()
        }
        document.head?.appendChild(node)
    }
}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {

    SilkApp {
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}

