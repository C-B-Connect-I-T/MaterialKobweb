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
fun locationSvg(
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
        d("M20.2334 10.7475C20.2334 17.2021 13.6224 21.1786 12.2485 21.936C12.1724 21.978 12.0869 22 12 22C11.9131 22 11.8276 21.978 11.7514 21.936C10.3765 21.1786 3.76758 17.2021 3.76758 10.7475C3.76758 5.6019 6.85492 2 12.0005 2C17.1461 2 20.2334 5.6019 20.2334 10.7475Z")
    }
    Path {
        stroke(fill)
        strokeWidth(2.px)
        strokeLineCap(SVGStrokeLineCap.Round)
        strokeLineJoin(SVGStrokeLineJoin.Round)
        d("M7.88379 10.2329C7.88379 11.3247 8.31749 12.3717 9.08947 13.1437C9.86146 13.9157 10.9085 14.3494 12.0002 14.3494C13.092 14.3494 14.139 13.9157 14.911 13.1437C15.683 12.3717 16.1167 11.3247 16.1167 10.2329C16.1167 9.14116 15.683 8.09412 14.911 7.32214C14.139 6.55015 13.092 6.11646 12.0002 6.11646C10.9085 6.11646 9.86146 6.55015 9.08947 7.32214C8.31749 8.09412 7.88379 9.14116 7.88379 10.2329Z")
    }
}
