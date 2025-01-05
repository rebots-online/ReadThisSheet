# Default ProGuard rules
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep the application class
-keep class world.robinsai.readthissheet.ReadThisSheetApp { *; }

# Keep all Fragment classes and constructors
-keep class * extends androidx.fragment.app.Fragment { *; }

# ARCore
-keep class com.google.ar.core.** { *; }
-keep class com.google.ar.sceneform.** { *; }

# ML Kit
-keep class com.google.mlkit.** { *; }
-keep class com.google.android.gms.vision.** { *; }

# Firebase
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# ExoPlayer
-keep class com.google.android.exoplayer2.** { *; }

# Model Context Protocol
-keep class com.modelcontextprotocol.** { *; }

# Billing
-keep class com.android.billingclient.** { *; }

# Keep Kotlin Coroutines
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Keep data classes
-keep class world.robinsai.readthissheet.data.** { *; }

# Keep API models
-keep class world.robinsai.readthissheet.api.** { *; }

# Keep Enum classes
-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep custom views
-keep class world.robinsai.readthissheet.ui.views.** { *; }

# Keep JavaScript interface methods
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep Parcelable classes
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep Serializable classes
-keep class * implements java.io.Serializable { *; }

# Keep R8 full mode
-allowaccessmodification
-repackageclasses 'world.robinsai.readthissheet'

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}
