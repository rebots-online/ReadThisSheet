package com.arsheetmusic.analyzer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.ar.core.Config
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArNode
import kotlinx.coroutines.launch
import com.arsheetmusic.analyzer.databinding.ActivityArSheetMusicBinding
import com.arsheetmusic.analyzer.core.SheetMusicAnalyzer
import com.arsheetmusic.analyzer.core.MusicTheoryEngine
import com.arsheetmusic.analyzer.core.AudioEngine

class ARSheetMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArSheetMusicBinding
    private lateinit var sceneView: ArSceneView
    private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    private lateinit var sheetMusicAnalyzer: SheetMusicAnalyzer
    private lateinit var musicTheoryEngine: MusicTheoryEngine
    private lateinit var audioEngine: AudioEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArSheetMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAR()
        setupAnalyzers()
        setupTouchHandling()
    }

    private fun setupAR() {
        sceneView = binding.sceneView
        sceneView.lightEstimationMode = Config.LightEstimationMode.AMBIENT_INTENSITY
        
        lifecycleScope.launch {
            sceneView.cameraStream?.let { input ->
                // Process camera frames for music notation recognition
                textRecognizer.process(input)
                    .addOnSuccessListener { text ->
                        sheetMusicAnalyzer.analyzeNotation(text)
                    }
            }
        }
    }

    private fun setupAnalyzers() {
        sheetMusicAnalyzer = SheetMusicAnalyzer()
        musicTheoryEngine = MusicTheoryEngine()
        audioEngine = AudioEngine(this)
    }

    private fun setupTouchHandling() {
        sceneView.setOnTouchListener { view, motionEvent ->
            // Handle touch events for note identification
            // and audio playback
            true
        }
    }

    private fun addAnnotation(x: Float, y: Float, text: String) {
        val node = ArNode()
        // Add 3D text annotation at the specified position
        sceneView.addChild(node)
    }

    override fun onDestroy() {
        super.onDestroy()
        textRecognizer.close()
        audioEngine.release()
    }
}
