package com.materialdesignsystem.extensions

import org.w3c.dom.Document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.asList
import org.w3c.files.File

fun Document.fileChooser(
    accept: String = "*/*",
    multiple: Boolean = false,
    onFileSelected: (List<File>) -> Unit
) {
    val input = createElement("input") as HTMLInputElement
    input.type = "file"
    input.accept = accept
    input.multiple = multiple

    input.onchange = {
        onFileSelected(input.files?.asList() ?: emptyList())
    }

    body?.appendChild(input)
    input.click()
    body?.removeChild(input)
}
