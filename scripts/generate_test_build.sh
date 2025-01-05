#!/bin/bash

# Create directories if they don't exist
mkdir -p builds/debug
mkdir -p builds/release

# Generate debug APK
echo "Generating debug APK..."
./gradlew assembleDebug
cp app/build/outputs/apk/debug/app-debug.apk builds/debug/

# Generate release APK for testing
echo "Generating release APK..."
./gradlew assembleRelease
cp app/build/outputs/apk/release/app-release.apk builds/release/

# Generate release bundle
echo "Generating release bundle..."
./gradlew bundleRelease
cp app/build/outputs/bundle/release/app-release.aab builds/release/

echo "Build artifacts generated in builds/ directory"
echo "Debug APK: builds/debug/app-debug.apk"
echo "Release APK: builds/release/app-release.apk"
echo "Release Bundle: builds/release/app-release.aab"
