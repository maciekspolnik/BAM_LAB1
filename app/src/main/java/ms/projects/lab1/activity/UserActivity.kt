package ms.projects.lab1.activity

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ms.projects.lab1.database.DatabaseHelper
import ms.projects.lab1.databinding.ActivityUserBinding
import ms.projects.lab1.receiver.NumberReceiver
import ms.projects.lab1.service.CountingService

class UserActivity : AppCompatActivity() {

    private lateinit var serviceIntent: Intent
    private lateinit var binding: ActivityUserBinding
    private lateinit var numberReceiver: NumberReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra("username")

        numberReceiver = registerNumberReceiver()

        serviceIntent =
            Intent(this, CountingService::class.java).apply { putExtra("username", username) }

        binding.textView.text = username
        binding.buttonStart.setOnClickListener { startService(serviceIntent) }
        binding.buttonStop.setOnClickListener { stopService(serviceIntent) }
        binding.buttonDatabase.setOnClickListener { showDatabaseContent() }
    }

    private fun showDatabaseContent() {
        CompositeDisposable().add(
            DatabaseHelper(applicationContext).getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                    { userDataList ->
                        Log.d("Database", "Userdata Table contains:")
                        userDataList.forEach { userData ->

                            Log.d(
                                "Database",
                                "ID: ${userData.id}  USERNAME: ${userData.username}  NUMBER: ${userData.number}"
                            )
                        }
                    }, { error -> Log.d("Database", error.toString()) })
        )
    }

    private fun registerNumberReceiver(): NumberReceiver {
        val filter = IntentFilter("NumberReceiverData")
        val receiver = NumberReceiver()

        registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
        return receiver
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(numberReceiver)
    }


}