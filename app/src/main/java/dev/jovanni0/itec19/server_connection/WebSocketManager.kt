package dev.jovanni0.itec19.server_connection

import android.util.Log
import dev.jovanni0.itec19.StrokePayload
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


object WebSocketManager
{
    private var client: WebSocketClient? = null
    private var currentPosterId: String? = null
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun connect(posterId: String, deviceId: String, serverIp: String, lastStrokeId: String)
    {
        if (currentPosterId == posterId) return

        Log.d("WebSocket", "Connecting to room $posterId")

        scope.launch {
            client?.close()
            currentPosterId = posterId
            client = WebSocketClient(posterId, deviceId, serverIp, lastStrokeId)
            client?.connect()
        }
    }

    fun sendStroke(stroke: StrokePayload)
    {
        if (client?.isConnected != true) {
            Log.d("WebSocket", "Could not send stroke, not connected.")
            return
        }
        scope.launch { client?.sendStroke(stroke) }
    }

    fun close() {
        scope.launch {
            client?.close()
            client = null

            Log.d("WebSocket", "Disconnected from room $currentPosterId")

            currentPosterId = null
        }
    }
}