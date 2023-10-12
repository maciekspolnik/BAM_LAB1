package ms.projects.lab1.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ms.projects.lab1.database.DatabaseHelper
import ms.projects.lab1.database.UserData

class NumberReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        intent?.extras?.let {
            Log.d(
                "NumberReceiver",
                "Username: ${it.getString("username")}, Stopped at: ${it.getLong("number")}"
            )

            CompositeDisposable().add(
                DatabaseHelper(context).insert(
                    UserData(
                        username = it.getString("username"),
                        number = it.getLong("number")

                    )
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .subscribe()
            )
        }
    }
}