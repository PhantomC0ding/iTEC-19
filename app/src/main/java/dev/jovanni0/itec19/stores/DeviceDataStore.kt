package dev.jovanni0.itec19.stores

import android.content.Context
import java.util.UUID
import androidx.core.content.edit

object AppStore
{
    const val SERVER_IP = "10.209.127.241"
    lateinit var deviceId: String
        private set

    fun init(context: Context)
    {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        deviceId = prefs.getString("device_id", null) ?: UUID.randomUUID().toString().also {
            prefs.edit { putString("device_id", it) }
        }
    }
}