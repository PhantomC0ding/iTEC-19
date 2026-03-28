package dev.jovanni0.itec19

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.graphics.Path



object DrawingStore {
    val drawings = mutableStateMapOf<String, List<Pair<Path, DrawConfig>>>()
}