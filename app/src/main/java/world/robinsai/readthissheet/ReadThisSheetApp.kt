package world.robinsai.readthissheet

import android.app.Application
import com.google.firebase.FirebaseApp

class ReadThisSheetApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

    companion object {
        const val TAG = "ReadThisSheet"
    }
}
