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
fun phoneSvg(
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
        d(
            "M14.7019 20.8622L14.7128 20.8699C15.6573 21.4712 16.7786 21.7324 17.8916 21.6101C19.0046 21.4879 20.0425 20.9896 20.8339 20.1976L21.5215 19.5101C21.6738 19.3578 21.7947 19.177 21.8772 18.9781C21.9596 18.7791 22.0021 18.5658 22.0021 18.3504C22.0021 18.135 21.9596 17.9217 21.8772 17.7227C21.7947 17.5237 21.6738 17.3429 21.5215 17.1906L18.6216 14.2929C18.4693 14.1406 18.2885 14.0197 18.0895 13.9373C17.8906 13.8548 17.6773 13.8123 17.4619 13.8123C17.2465 13.8123 17.0332 13.8548 16.8342 13.9373C16.6352 14.0197 16.4544 14.1406 16.3021 14.2929C15.9947 14.6003 15.5777 14.773 15.1429 14.773C14.7082 14.773 14.2912 14.6003 13.9838 14.2929L9.34701 9.6551C9.03964 9.34764 8.86696 8.93068 8.86696 8.49592C8.86696 8.06116 9.03964 7.6442 9.34701 7.33673C9.49937 7.18447 9.62024 7.00367 9.7027 6.80468C9.78516 6.60569 9.82761 6.3924 9.82761 6.177C9.82761 5.9616 9.78516 5.74831 9.7027 5.54931C9.62024 5.35032 9.49937 5.16953 9.34701 5.01727L6.44823 2.11957C6.14076 1.8122 5.7238 1.63953 5.28904 1.63953C4.85428 1.63953 4.43732 1.8122 4.12986 2.11957L3.44123 2.80711C2.64941 3.59867 2.15134 4.6367 2.02931 5.74965C1.90728 6.8626 2.1686 7.98389 2.77009 8.92822L2.77665 8.93915C5.95327 13.6391 10.0014 17.6865 14.7019 20.8622Z"
        )
    }
}
