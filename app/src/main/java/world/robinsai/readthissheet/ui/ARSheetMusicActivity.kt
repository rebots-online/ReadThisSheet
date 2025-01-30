package world.robinsai.readthissheet.ui

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import world.robinsai.readthissheet.databinding.ActivityArSheetMusicBinding

class ARSheetMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArSheetMusicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArSheetMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize AR components
        binding.surfaceView.setEGLContextClientVersion(2)
        binding.surfaceView.preserveEGLContextOnPause = true
    }

    override fun onResume() {
        super.onResume()
        binding.surfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.surfaceView.onPause()
    }
}
