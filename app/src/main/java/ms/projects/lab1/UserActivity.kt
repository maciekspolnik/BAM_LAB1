package ms.projects.lab1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ms.projects.lab1.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var serviceIntent: Intent
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = intent.getStringExtra("User")
        serviceIntent = Intent(this, CountingService::class.java)
        binding.buttonStart.setOnClickListener {
            startService(serviceIntent)
        }
        binding.buttonStop.setOnClickListener {
            stopService(serviceIntent)
        }
    }

}