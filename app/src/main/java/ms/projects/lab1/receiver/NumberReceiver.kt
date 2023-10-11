package ms.projects.lab1.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NumberReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.extras?.let {
            Log.d(
                "NumberReceiver",
                "Username: ${it.getString("username")}, Stopped at: ${it.getLong("number")}"
            )
        }
    }
}