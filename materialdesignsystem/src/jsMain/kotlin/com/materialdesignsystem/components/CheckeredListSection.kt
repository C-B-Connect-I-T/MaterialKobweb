package com.materialdesignsystem.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.BoxScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.aspectRatio
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.div
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import kotlin.random.Random

@Composable
fun <T> CheckeredListSection(
    modifier: Modifier = Modifier,
    items: List<T>,
    maxItemsPerRow: Int? = null,
    aspectRatios: List<Pair<Int, Int>> = aspectRatios(),
    calculateRandomGroupSize: (maxItemsInRow: Int, leftovers: Int, currentIndex: Int) -> Int,
    content: @Composable BoxScope.(T) -> Unit = {}
) {
    val breakpoint = rememberBreakpoint()

    Box(
        modifier
            .display(DisplayStyle.Flex)
            .flexWrap(FlexWrap.Wrap)
            .justifyContent(JustifyContent.Center)
    ) {
        val maxItems = maxItemsPerRow ?: breakpoint.maxItemsInRow()
        chunkedListWithAtMost3InARow(items, maxItems, calculateRandomGroupSize)
            .forEachIndexed { index, list ->
                val modulo = (1..maxItems).random(Random(list.first().hashCode()))
                val aspectForRow = aspectRatios[index % modulo]

                list.forEach { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(100.percent / list.size)
                            .aspectRatio(aspectForRow.first, aspectForRow.second)
                            .padding(10.px),
                        content = {
                            content(item)
                        }
                    )
                }
            }
    }
}

private fun <T> chunkedListWithAtMost3InARow(
    items: List<T>,
    maxItemsPerRow: Int,
    calculateRandomGroupSize: (maxItemsInRow: Int, leftovers: Int, currentIndex: Int) -> Int
): List<List<T>> {

    // Group the shuffled elements into subLists with a random size between 2 and 3 (never 1)
    val groupedLists = mutableListOf<List<T>>()
    var currentIndex = 0

    while (currentIndex < items.size) {
        val leftovers = items.size - currentIndex

        val randomGroupSize = calculateRandomGroupSize(maxItemsPerRow, leftovers, currentIndex)

        val endIndex = (currentIndex + randomGroupSize).coerceAtMost(items.size)
        val sublist = items.subList(currentIndex, endIndex)
        groupedLists.add(sublist)
        currentIndex = endIndex
    }

    return groupedLists
}

private fun Breakpoint.maxItemsInRow() = when {
    this <= Breakpoint.SM -> 1
    this <= Breakpoint.LG -> 2
    else -> 3
}

private fun aspectRatios() = listOf(16 to 9, 4 to 3, 1 to 1)

@Composable
fun Spacer(modifier: Modifier = Modifier) {
    Div(modifier.toAttrs())
}
