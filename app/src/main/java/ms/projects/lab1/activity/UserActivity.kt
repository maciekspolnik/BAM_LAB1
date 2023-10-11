package ms.projects.lab1.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.textView.text = username
        serviceIntent = Intent(this, CountingService::class.java)
        serviceIntent.putExtra("username", username)
        binding.buttonStart.setOnClickListener {
            startService(serviceIntent)
        }
        binding.buttonStop.setOnClickListener {
            stopService(serviceIntent)
        }
        numberReceiver = registerNumberReceiver()
    }

    private fun registerNumberReceiver(): NumberReceiver {
        val filter = IntentFilter("NumberReceiverData")
        val receiver = NumberReceiver()
        registerReceiver(receiver, filter)
        return receiver
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(numberReceiver)
    }


}