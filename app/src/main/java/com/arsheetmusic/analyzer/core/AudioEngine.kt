package com.arsheetmusic.analyzer.core

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

class AudioEngine(context: Context) {
    private val player = ExoPlayer.Builder(context).build()
    private val scope = CoroutineScope(Dispatchers.Main)
    private val noteCache = ConcurrentHashMap<String, MediaItem>()

    init {
        // Preload piano note samples
        scope.launch {
            preloadPianoSamples()
        }
    }

    private fun preloadPianoSamples() {
        // Load piano samples for each note
        // These would be stored in the assets folder
    }

    fun playNote(note: SheetMusicAnalyzer.Note) {
        noteCache[note.pitch]?.let { mediaItem ->
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }

    fun playChord(notes: List<SheetMusicAnalyzer.Note>) {
        // Play multiple notes simultaneously
        notes.forEach { playNote(it) }
    }

    fun playMelody(notes: List<SheetMusicAnalyzer.Note>, tempo: Float) {
        // Play a sequence of notes with proper timing
        scope.launch {
            notes.forEach { note ->
                playNote(note)
                kotlinx.coroutines.delay((note.duration * tempo).toLong())
            }
        }
    }

    fun release() {
        player.release()
    }
}
