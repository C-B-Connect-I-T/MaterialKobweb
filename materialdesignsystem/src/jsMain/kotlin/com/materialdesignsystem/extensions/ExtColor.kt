package com.materialdesignsystem.extensions

import com.varabyte.kobweb.compose.ui.graphics.Color

@OptIn(ExperimentalStdlibApi::class)
fun Color.toHex() = "#" + this.toRgb().value.toHexString(HexFormat.UpperCase).drop(2)

@OptIn(ExperimentalStdlibApi::class)
fun Color.toHexf() = "#" + this.toRgb().value.toHexString(HexFormat.UpperCase)
