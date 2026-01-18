# Install App

Build and install the Android app to a connected device or emulator.

## Arguments

- `$ARGUMENTS` - Optional: `clean` for a clean build first

## Instructions

Build and install the Launchpad app to the connected device/emulator.

### Step 1: Verify Device Connected

```bash
adb devices
```

If no devices are listed, inform the user:
```
No devices connected. Please start an emulator or connect a device.
```

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

### Step 3: Report Results

**Success:**
```
✓ Build successful
✓ Installed on [device-name]
```

**Failure:**
Show the error and suggest fixes:
- Build errors → Show compilation error details
- Install errors → Suggest `adb uninstall com.example.launchpad` or restart ADB
