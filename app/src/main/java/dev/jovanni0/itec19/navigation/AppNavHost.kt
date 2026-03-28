package dev.jovanni0.itec19.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import dev.jovanni0.itec19.ar.ArOverlayScene
import dev.jovanni0.itec19.screen.GalleryScreen

@Composable
fun AppNavHost(onPosterDetected: (String) -> Unit) {
    var currentRoute by remember { mutableStateOf(AppDestinations.AR) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavItem.all.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = { currentRoute = item.route },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

            // Always in composition, only visibility toggled
            Box(modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = if (currentRoute == AppDestinations.AR) 1f else 0f }
            ) {
                ArOverlayScene(onPosterDetected = onPosterDetected)
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = if (currentRoute == AppDestinations.GALLERY) 1f else 0f }
            ) {
                GalleryScreen()
            }
        }
    }
}