package world.robinsai.arsheetmusic

import android.app.Application
import com.google.firebase.FirebaseApp

class ARSheetMusicApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

    companion object {
        const val TAG = "ARSheetMusic"
    }
}
