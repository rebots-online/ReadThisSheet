package world.robinsai.readthissheet.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import world.robinsai.readthissheet.databinding.ActivityMainBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
