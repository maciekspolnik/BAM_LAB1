package ms.projects.lab1.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ms.projects.lab1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { openUserActivity(binding.editTextText.text.toString()) }

    }

    private fun openUserActivity(user: String) {
        if (user.isEmpty()) {
            return
        }
        val intent =
            Intent(this@MainActivity, UserActivity::class.java).apply { putExtra("username", user) }
        startActivity(intent)
    }

}