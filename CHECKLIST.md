# ReadThisSheet! - APK Run Checklist

**Objective:** Successfully build and run the ReadThisSheet! APK on an Android device.

**Status:** [ ] Incomplete | [ ] Complete

**Steps:**

*   [ ] **1. Firebase Configuration Check:**
    *   [ ] Verify `google-services.json` presence in `app/src/main`.
    *   [ ] If missing, create Firebase project and add `google-services.json` to `app/src/main`.
*   [ ] **2. Build APK:**
    *   [ ] Execute `./gradlew assembleDebug` command.
    *   [ ] Verify successful APK build in `app/build/outputs/apk/debug/`.
*   [ ] **3. Device Setup:**
    *   [ ] Ensure Android device meets requirements (Android 7.0+, ARCore).
    *   [ ] Enable "USB debugging" on the device.
    *   [ ] Connect device to computer via USB.
*   [ ] **4. Install and Run APK:**
    *   [ ] Install APK using Android Studio or `adb install`.
    *   [ ] Launch "ReadThisSheet!" app on the device.
    *   [ ] Verify app runs without crashing and basic features are functional.

**Timeline:**

*   **Start Date:** 2025-01-29
*   **Estimated Completion Date:** 2025-01-30 (Assuming Firebase setup is straightforward)

**Notes:**

*   If Firebase configuration is missing, allocate additional time for Firebase project setup.
*   Refer to `README.md` for detailed instructions and troubleshooting.