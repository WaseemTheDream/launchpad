# Build App

Build the Android app.

## Arguments

- `$ARGUMENTS` - Optional: `clean` for a clean build

## Instructions

Build the Launchpad Android app.

### Set JAVA_HOME first (required for Gradle):
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr"
```

### If `$ARGUMENTS` contains "clean":

Run a clean build:
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && "C:\Users\Waz\AndroidStudioProjects\launchpad\gradlew.bat" clean assembleDebug
```

### Otherwise:

Run a standard debug build:
```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && "C:\Users\Waz\AndroidStudioProjects\launchpad\gradlew.bat" assembleDebug
```

### After Build:

1. Check if build succeeded (`BUILD SUCCESSFUL`) or failed (`BUILD FAILED`)
2. If successful:
   - Report build time
   - Report APK location: `app/build/outputs/apk/debug/app-debug.apk`
3. If failed:
   - Show the error message
   - Identify the file and line number if it's a compilation error
   - Suggest fixes if possible

### Output Format

**Success:**
```
✓ Build successful (Xs)
  APK: app/build/outputs/apk/debug/app-debug.apk
```

**Failure:**
```
✗ Build failed

[Error details]

Suggestion: [Fix suggestion]
```
