package dev.jovanni0.itec19.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector



sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object AR : BottomNavItem(AppDestinations.AR, "AR View", Icons.Filled.Home)
    object Gallery : BottomNavItem(AppDestinations.GALLERY, "Gallery", Icons.Filled.List)

    companion object {
        val all = listOf(AR, Gallery)
    }
}