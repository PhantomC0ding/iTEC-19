package dev.jovanni0.itec19.server_connection

import android.util.Log
import dev.jovanni0.itec19.StrokePayload
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


object WebSocketManager
{
    private var client: WebSocketClient? = null
    private var currentPosterId: String? = null
    private var isConnected: Boolean = false


    fun connect(
        posterId: String,
        deviceId: String,
        serverIp: String,
        lastStrokeId: String,
        scope: CoroutineScope,
    ) {
        if (currentPosterId == posterId) return

        scope.launch {
            client?.close()
            currentPosterId = posterId
            client = WebSocketClient(
                posterId,
                deviceId,
                serverIp,
                lastStrokeId = lastStrokeId,
                onConnected = { isConnected = true },
                onDisconnected = { isConnected = false },
                onError = { isConnected = false }
            )
            client?.connect()
        }
        Log.d("State", "WebSocketManager tried connecting to room $currentPosterId")
    }


    fun sendStroke(stroke: StrokePayload, scope: CoroutineScope)
    {
        if (!isConnected) {
            Log.d("WebSocket", "Could not send stroke because not connected to server.")
            return
        }

        scope.launch {
            client?.sendStroke(stroke)
        }

        Log.d("State", "Sent stroke to server in room $currentPosterId")
    }


//    fun close(scope: CoroutineScope)
//    {
//        scope.launch {
//            client?.close()
//
//            Log.d("State", "WebSocketManager disconnected from room $currentPosterId")
//
//            client = null
//            currentPosterId = null
//        }
//    }
// In WebSocketManager
    fun close()
    {
        runBlocking {
            client?.close()
        }

        Log.d("State", "WebSocketManager disconnected from room $currentPosterId")

        client = null
        currentPosterId = null
    }
}