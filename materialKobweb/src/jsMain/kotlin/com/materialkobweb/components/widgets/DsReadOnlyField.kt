package com.materialkobweb.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier

@Composable
fun DsReadOnlyField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    id: String = defaultReadOnlyFieldId(label)
) {
    DsEditableField(
        modifier = modifier,
        id = id,
        label = label,
        value = value,
        onValueChange = {},
        readOnly = true,
        required = false
    )
}

private fun defaultReadOnlyFieldId(label: String): String {
    val sanitized = label
        .trim()
        .lowercase()
        .replace(Regex("[^a-z0-9]+"), "-")
        .trim('-')

    return if (sanitized.isNotEmpty()) "$sanitized-readonly" else "readonly-field"
}
