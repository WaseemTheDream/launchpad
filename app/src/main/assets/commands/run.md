# Run App

Build, install, and launch the Android app on a connected device or emulator.

## Arguments

- `$ARGUMENTS` - Optional: `clean` for a clean build first

## Instructions

Complete workflow: Build → Install → Launch the Launchpad app.

### Step 1: Verify Device Connected

```bash
adb devices
```

If no devices are listed:
```
No devices connected. Please start an emulator or connect a device.
```
Stop here if no device.

### Step 2: Build and Install

Set JAVA_HOME and run Gradle:

If `$ARGUMENTS` contains "clean":
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && "C:\Users\Waz\AndroidStudioProjects\launchpad\gradlew.bat" clean installDebug
```

Otherwise:
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && "C:\Users\Waz\AndroidStudioProjects\launchpad\gradlew.bat" installDebug
```

If build/install fails, report the error and stop.

### Step 3: Launch the App

```bash
adb shell am start -n com.example.launchpad/.MainActivity
```

### Step 4: Report Results

**Success:**
```
✓ Build successful
✓ Installed on [device-name]
✓ App launched
```

**With timing:**
```
✓ Build successful (12.3s)
✓ Installed on emulator-5554
✓ App launched

The app should now be visible on your emulator/device.
```
