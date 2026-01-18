# App Build, Install, and Run Commands

[auto-commit]

## Task

Create Claude Code commands and skills for building, installing, and running the Android app on connected emulators or devices.

## Requirements

### App Manager Skill (`.claude/skills/app.md`)

Create a comprehensive skill for intelligent app lifecycle management:
- Pre-flight checks (device connected, project structure valid)
- Build operation with error analysis
- Install operation with failure handling
- Launch operation with verification
- Complete reporting of success/failure
- Error recovery guidance
- Quick reference command table

### Build Command (`.claude/commands/build.md`)

- Standard debug build: `gradlew.bat assembleDebug`
- Clean build option: `gradlew.bat clean assembleDebug`
- Report build time and APK location
- Show compilation errors with file/line details

### Install Command (`.claude/commands/install.md`)

- Verify device is connected first
- Build and install: `gradlew.bat installDebug`
- Support clean build option
- Report installation status

### Run Command (`.claude/commands/run.md`)

- Full workflow: Build → Install → Launch
- Verify device connected
- Build and install the app
- Launch with: `adb shell am start -n com.example.launchpad/.MainActivity`
- Report complete status

### Technical Requirements

- Set JAVA_HOME to Android Studio's bundled JBR before Gradle commands
- Use full paths for Windows compatibility
- Handle both clean and incremental builds
- Check for `BUILD SUCCESSFUL` / `BUILD FAILED`
- Provide meaningful error messages and suggestions

## Expected Outcome

- `/build` command for quick compilation
- `/install` command for deployment
- `/run` command for full build-install-launch cycle
- `app` skill for conversational app management
- All commands tested and working on Windows with Android Studio

## Commit

Commit: `[0002] chore: Add app build, install, and run commands`
