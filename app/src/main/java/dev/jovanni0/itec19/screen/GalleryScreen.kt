package dev.jovanni0.itec19.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jovanni0.itec19.DrawingStore


@Composable
fun GalleryScreen() {
    val posters = DrawingStore.drawings.keys.toList()

    if (posters.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No saved drawings yet.\nScan a poster in the AR view.", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Saved Drawings", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 8.dp))
        }
        items(posters) { posterName ->
            val count = DrawingStore.drawings[posterName]?.size ?: 0
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(posterName, style = MaterialTheme.typography.titleMedium)
                    Text("$count stroke(s)", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}