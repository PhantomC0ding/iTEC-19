package dev.jovanni0.itec19.stores

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.geometry.Offset
import dev.jovanni0.itec19.DrawConfig

object DrawingStore {
    val drawings = mutableStateMapOf<String, List<Pair<List<Offset>, DrawConfig>>>()
}