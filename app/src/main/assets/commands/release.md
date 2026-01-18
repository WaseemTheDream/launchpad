# Release Build

Build, version, and publish a signed release APK.

---

## Overview

The **Release** command creates a signed release APK, stores it in the `.claude/releases/` folder with semantic versioning, updates the changelog, and commits everything to git. This provides a complete release management workflow.

---

## Usage

```
/release              # Build with current version
/release patch        # Bump patch version: 1.0.0 → 1.0.1
/release minor        # Bump minor version: 1.0.0 → 1.1.0
/release major        # Bump major version: 1.0.0 → 2.0.0
/release 2.1.0        # Set specific version
```

---

## Instructions

### Phase 1: Display Header

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    📦 R E L E A S E   B U I L D

    Creating signed release APK...

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```

### Phase 2: Check Keystore Setup

Check if the required files exist:
1. `.claude/android-keystore/release.jks` (or any `.jks` file)
2. `.claude/android-keystore/keystore.properties`

**If keystore.properties is missing:**

```
⚠️  KEYSTORE NOT CONFIGURED

To build a release APK, you need to configure your signing keystore.

See the README for detailed instructions on:
• Creating a new keystore (first-time setup)
• Using an existing keystore

Quick setup for existing keystore:
1. Copy your .jks file to: .claude/android-keystore/
2. Create keystore.properties with your credentials

Would you like me to help you set up the keystore now?
```

Use AskUserQuestion:
- "Do you have an existing Android keystore (.jks file)?"
  - "Yes, I have a .jks file" → Guide to copy and configure
  - "No, I need to create one" → Guide through keytool command
  - "Cancel release build" → Exit gracefully

**If user has existing keystore:**

1. Ask them to copy their `.jks` file to `.claude/android-keystore/`
2. Get the filename they used
3. Ask for keystore password
4. Ask for key alias
5. Ask for key password
6. Create `keystore.properties` file:

```properties
storeFile=../.claude/android-keystore/{filename}.jks
storePassword={password}
keyAlias={alias}
keyPassword={keyPassword}
```

**If user needs to create a new keystore:**

Guide them to run this command in terminal:

```bash
keytool -genkey -v -keystore .claude/android-keystore/release.jks -keyalg RSA -keysize 2048 -validity 10000 -alias release
```

### Phase 3: Determine Version

1. Read current version from `.claude/releases/VERSION`
2. Parse the command argument:
   - No argument: Use current version
   - `patch`: Increment patch (1.0.0 → 1.0.1)
   - `minor`: Increment minor, reset patch (1.0.1 → 1.1.0)
   - `major`: Increment major, reset minor and patch (1.2.3 → 2.0.0)
   - `X.Y.Z`: Use specific version provided

3. Display version info:
```
📋 Version Info:
   Current: {current_version}
   Release: {new_version}
```

4. If version already exists (APK file present), warn and ask to confirm overwrite.

### Phase 4: Verify Gradle Configuration

Check if `app/build.gradle.kts` has the signingConfigs block. If not, add it:

```kotlin
import java.util.Properties
import java.io.FileInputStream

// Add at the top of the android block
val keystorePropertiesFile = rootProject.file(".claude/android-keystore/keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    // ... existing config ...

    signingConfigs {
        create("release") {
            if (keystorePropertiesFile.exists()) {
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}
```

### Phase 5: Update Version in build.gradle.kts

Update the `versionName` in `app/build.gradle.kts` to match the release version:

```kotlin
defaultConfig {
    // ...
    versionName = "{new_version}"
    // ...
}
```

Also increment `versionCode` by 1.

### Phase 6: Build Release APK

Run the Gradle build command:

```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && ./gradlew.bat assembleRelease
```

### Phase 7: Store Release APK

1. Determine app name from package (e.g., `launchpad` from `com.example.launchpad`)
2. Copy APK to releases folder:
   ```bash
   cp app/build/outputs/apk/release/app-release.apk .claude/releases/{app-name}-v{version}.apk
   ```

3. Update `.claude/releases/VERSION` with new version

### Phase 8: Update Changelog

Ask user for release notes using AskUserQuestion:
- "What's included in this release? (brief description)"

Update `.claude/releases/CHANGELOG.md`:

```markdown
## [X.Y.Z] - YYYY-MM-DD

### Added/Changed/Fixed
- {User's release notes}

---
```

Insert this entry after the `## [Unreleased]` section.

### Phase 9: Commit and Push

Create a git commit with all release changes:

```bash
git add .claude/releases/ app/build.gradle.kts
git commit -m "[NNNN] release: v{version}

- Built signed release APK
- {Brief release notes}

Co-Authored-By: Claude <noreply@anthropic.com>"
```

Create a git tag:
```bash
git tag -a v{version} -m "Release v{version}"
```

Push to remote:
```bash
git push && git push --tags
```

### Phase 10: Report Results

**On success:**

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    ✅ RELEASE v{version} PUBLISHED

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📦 Release Artifacts:
   APK: .claude/releases/{app-name}-v{version}.apk
   Size: {file_size}

📊 Build Info:
   • Package: {package name}
   • Version: {version}
   • Version Code: {version_code}
   • Signed: Yes (release keystore)

📝 Git:
   • Commit: {commit_hash}
   • Tag: v{version}
   • Pushed: Yes

🚀 Next Steps:
   • Test the APK on a physical device
   • Upload to Google Play Console
   • Share via Firebase App Distribution

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```

**On failure:**

Display the error message and suggest fixes:
- Missing keystore: Guide to setup
- Invalid credentials: Ask user to verify keystore.properties
- Build errors: Show error details

---

## Version Management

### VERSION File

The `.claude/releases/VERSION` file contains a single line with the current version:
```
1.0.0
```

### Semantic Versioning Rules

| Bump Type | When to Use | Example |
|-----------|-------------|---------|
| `major` | Breaking changes, major redesigns | 1.2.3 → 2.0.0 |
| `minor` | New features, enhancements | 1.2.3 → 1.3.0 |
| `patch` | Bug fixes, small improvements | 1.2.3 → 1.2.4 |

### Version Code

The `versionCode` in build.gradle.kts is automatically incremented with each release. This is required for Play Store updates.

---

## Release Artifacts

Each release creates these files (all tracked in git):

| File | Purpose |
|------|---------|
| `.claude/releases/{app}-v{X.Y.Z}.apk` | Signed release APK |
| `.claude/releases/VERSION` | Current version number |
| `.claude/releases/CHANGELOG.md` | Release history |

---

## Security Reminders

After helping users set up their keystore, remind them:

```
🔒 SECURITY REMINDER

Your keystore credentials are stored in:
   .claude/android-keystore/keystore.properties

This file is git-ignored for your protection.

IMPORTANT:
• Back up your .jks file securely
• Never share your keystore passwords
• If you lose your keystore, you cannot update your app on Play Store
```

---

## Error Recovery

- If keystore.properties has wrong path, help fix it
- If passwords are wrong, ask user to re-enter them
- If keystore is corrupted, guide to create new one (warn about consequences)
- If version already exists, offer to overwrite or use different version
