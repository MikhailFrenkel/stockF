package com.frenkel.stockf

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher

fun tagContains(substring: String): SemanticsMatcher {
    return SemanticsMatcher("$substring is a substring") { semanticsNode ->
        val tag = semanticsNode.config.getOrNull(SemanticsProperties.TestTag)
        tag?.contains(substring) == true
    }
}