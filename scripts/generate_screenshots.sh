#!/bin/bash

# Configuration
PROJECT_ID="readthissheet"
DEVICES=(
    "model=Pixel6,version=33,locale=en,orientation=portrait"
    "model=Pixel6,version=33,locale=en,orientation=landscape"
    "model=Nexus10,version=30,locale=en,orientation=landscape"
)

# Build the test APK
echo "Building test APK..."
./gradlew assembleDebugAndroidTest

# Run tests on Firebase Test Lab
for device in "${DEVICES[@]}"
do
    echo "Running tests on $device..."
    gcloud firebase test android run \
        --type instrumentation \
        --app app/build/outputs/apk/debug/app-debug.apk \
        --test app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk \
        --device "$device" \
        --directories-to-pull /sdcard/screenshots \
        --results-dir="test_results_$(date +%Y%m%d_%H%M%S)"
done

# Download and organize screenshots
echo "Downloading screenshots..."
mkdir -p store_assets/screenshots/phone
mkdir -p store_assets/screenshots/tablet

# Process and resize screenshots
echo "Processing screenshots..."
for file in store_assets/screenshots/raw/*; do
    if [[ $file == *"Pixel"* ]]; then
        convert "$file" -resize 1920x1080 "store_assets/screenshots/phone/$(basename "$file")"
    elif [[ $file == *"Nexus10"* ]]; then
        convert "$file" -resize 2560x1440 "store_assets/screenshots/tablet/$(basename "$file")"
    fi
done

echo "Screenshots generated and processed!"
