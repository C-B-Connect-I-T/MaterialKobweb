@file:Suppress("all")

package com.materialdesignsystem.components.svg

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.SVGFillType
import com.varabyte.kobweb.compose.dom.svg.SVGStrokeLineCap
import com.varabyte.kobweb.compose.dom.svg.SVGStrokeLineJoin
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.px

@Composable
fun mailSvg(
    fill: CSSColorValue,
    modifier: Modifier = Modifier,
) = Svg(attrs = modifier.toAttrs {
    width(24)
    height(24)
    viewBox(0, 0, 24, 24)
    fill(SVGFillType.None)
}) {
    Path {
        stroke(fill)
        strokeWidth(2.px)
        strokeLineCap(SVGStrokeLineCap.Round)
        strokeLineJoin(SVGStrokeLineJoin.Round)
        d("M22 7.13953V17.1395C22 17.8026 21.7366 18.4385 21.2678 18.9073C20.7989 19.3761 20.163 19.6395 19.5 19.6395H4.5C3.83696 19.6395 3.20107 19.3761 2.73223 18.9073C2.26339 18.4385 2 17.8026 2 17.1395V7.13953M22 7.13953C22 6.47649 21.7366 5.8406 21.2678 5.37176C20.7989 4.90292 20.163 4.63953 19.5 4.63953H4.5C3.83696 4.63953 3.20107 4.90292 2.73223 5.37176C2.26339 5.8406 2 6.47649 2 7.13953M22 7.13953L13.325 12.5562C12.9277 12.8045 12.4686 12.9362 12 12.9362C11.5314 12.9362 11.0723 12.8045 10.675 12.5562L2 7.13953")
    }
}
