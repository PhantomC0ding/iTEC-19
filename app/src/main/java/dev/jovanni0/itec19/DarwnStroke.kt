package dev.jovanni0.itec19

import androidx.compose.ui.geometry.Offset



data class DrawnStroke(
    val points: List<Offset>,
    val config: DrawConfig
)