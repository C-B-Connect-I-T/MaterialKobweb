package com.materialdesignsystem.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.materialdesignsystem.constants.AttributeValue
import com.materialdesignsystem.constants.Attributes
import org.jetbrains.compose.web.dom.Video
import org.w3c.dom.HTMLVideoElement

@Composable
fun DsVideo(
    videoUrl: String,
    modifier: Modifier = Modifier,
    poster: String? = null,
    autoPlay: Boolean = false,
    loop: Boolean = false,
    controls: Boolean = true,
    ref: ElementRefScope<HTMLVideoElement>? = null,
    content: @Composable () -> Unit = {}
) {
    Video(
        attrs = modifier
            .toAttrs {
                if (controls) attr(Attributes.Controls, "")
                if (loop) attr(Attributes.Loop, "")
                if (autoPlay) attr(Attributes.Autoplay, "")
                if (poster != null) attr(Attributes.Poster, poster)

                attr(Attributes.Muted, AttributeValue.Muted)
                attr(Attributes.OnCanPlay, AttributeValue.MutedTrue)
                attr(Attributes.Preload, AttributeValue.Auto)
                attr(Attributes.Src, videoUrl)
            },
        content = {
            registerRefScope(ref)
            content()
        }
    )
}
