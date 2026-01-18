# App Manager

Comprehensive skill for building, installing, and running the Android app on connected emulators or devices.

## Overview

This skill handles the complete app lifecycle:
- **Build** - Compile the app into an APK
- **Install** - Deploy the APK to a connected device/emulator
- **Run** - Launch the app on the device/emulator
- **Full Cycle** - Build → Install → Run in one operation

## App Configuration

```
Package: com.example.launchpad
Main Activity: com.example.launchpad.MainActivity
Build System: Gradle (Kotlin DSL)
Min SDK: 24
Target SDK: 36
```

## Instructions

### Understanding User Intent

Determine what the user wants:
- "build the app" / "compile" → Build only
- "install the app" / "deploy" → Build + Install
- "run the app" / "launch" / "open" / "start" → Build + Install + Run
- "rebuild" / "clean build" → Clean + Build

### Step 1: Pre-flight Checks

Before any operation, verify the environment:

1. **Check for connected devices/emulators:**
   ```bash
   adb devices
   ```
   - If no devices listed, inform user to start an emulator or connect a device
   - If multiple devices, note which one will be used (first available)

2. **Verify project structure:**
   - Confirm `gradlew.bat` (Windows) or `gradlew` (Unix) exists
   - Confirm `app/build.gradle.kts` exists

### Step 2: Build Operation

**Important:** Always set JAVA_HOME before running Gradle:
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr"
```

**Standard Debug Build:**
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && "C:\Users\Waz\AndroidStudioProjects\launchpad\gradlew.bat" assembleDebug
```

**Clean Build (if requested or after errors):**
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && "C:\Users\Waz\AndroidStudioProjects\launchpad\gradlew.bat" clean assembleDebug
```

**Build Checks:**
- Watch for `BUILD SUCCESSFUL` or `BUILD FAILED`
- If failed, analyze the error output:
  - Compilation errors → Show file and line number
  - Dependency issues → Suggest `.\gradlew.bat --refresh-dependencies`
  - Out of memory → Suggest increasing Gradle heap size
- Report build time and APK location on success

**APK Location:**
```
app/build/outputs/apk/debug/app-debug.apk
```

### Step 3: Install Operation

**Install to connected device:**
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && "C:\Users\Waz\AndroidStudioProjects\launchpad\gradlew.bat" installDebug
```

This automatically:
- Builds if necessary
- Uninstalls previous version if present
- Installs the new APK

**Alternative (direct ADB install):**
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```
The `-r` flag replaces existing installation.

**Install Checks:**
- Verify device is still connected
- Watch for `Installed on X device(s)`
- Handle common errors:
  - `INSTALL_FAILED_UPDATE_INCOMPATIBLE` → Uninstall first: `adb uninstall com.example.launchpad`
  - `INSTALL_FAILED_INSUFFICIENT_STORAGE` → Clear device space
  - Device offline → Reconnect or restart ADB: `adb kill-server && adb start-server`

### Step 4: Run/Launch Operation

**Launch the main activity:**
```bash
adb shell am start -n com.example.launchpad/.MainActivity
```

**Launch with cleared state:**
```bash
adb shell am start -S -n com.example.launchpad/.MainActivity
```
The `-S` flag force-stops the app first.

**Launch Checks:**
- Watch for `Starting: Intent { cmp=com.example.launchpad/.MainActivity }`
- Error `Activity not found` → Verify package name and activity name
- App crashes immediately → Check `adb logcat` for crash logs

### Step 5: Reporting

After operations complete, provide a summary:

```
✓ Build successful (8.3s)
  APK: app/build/outputs/apk/debug/app-debug.apk (3.2 MB)

✓ Installed on emulator-5554

✓ App launched successfully
```

Or on failure:
```
✗ Build failed

Error: Unresolved reference: someFunction
  → app/src/main/java/com/example/launchpad/MainActivity.kt:42

Suggestion: Check if 'someFunction' is defined or imported correctly.
```

## Quick Reference Commands

| Operation | Gradle Command | ADB Command |
|-----------|---------------|-------------|
| Build | `.\gradlew.bat assembleDebug` | - |
| Clean Build | `.\gradlew.bat clean assembleDebug` | - |
| Install | `.\gradlew.bat installDebug` | `adb install -r app/build/outputs/apk/debug/app-debug.apk` |
| Launch | - | `adb shell am start -n com.example.launchpad/.MainActivity` |
| Stop App | - | `adb shell am force-stop com.example.launchpad` |
| Uninstall | `.\gradlew.bat uninstallDebug` | `adb uninstall com.example.launchpad` |
| View Logs | - | `adb logcat --pid=$(adb shell pidof com.example.launchpad)` |

## Error Recovery

**No devices connected:**
```
No emulators or devices detected.

To start an emulator:
1. Open Android Studio
2. Go to Tools → Device Manager
3. Click the Play button on your virtual device

Or connect a physical device via USB with debugging enabled.
```

**Build failures:**
1. Try a clean build first
2. Check for syntax errors in recent changes
3. Verify dependencies in build.gradle.kts
4. Run `.\gradlew.bat --refresh-dependencies` if dependency issues

**Install failures:**
1. Verify device is connected: `adb devices`
2. Restart ADB: `adb kill-server && adb start-server`
3. Uninstall existing app: `adb uninstall com.example.launchpad`
4. Try direct install: `adb install -r <apk-path>`

## Usage Examples

**Automated prompt usage:**
```markdown
## Task
Add a new button to MainActivity, then build and run the app to verify it works.
```

**Conversational:**
- "Build the app"
- "Run the app on the emulator"
- "Do a clean build and install"
- "Launch the app"
- "Rebuild and run"
