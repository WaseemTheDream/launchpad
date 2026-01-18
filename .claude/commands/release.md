# Release Build

Build a signed release APK for distribution.

---

## Overview

The **Release** command creates a signed release APK ready for distribution or Play Store upload. It handles keystore configuration, builds the release APK, and provides the output location.

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

Explain:
- They will be prompted for a keystore password (remember this!)
- They will be prompted for personal info (can use defaults)
- They will be prompted for a key password (can be same as keystore password)

After creation, create `keystore.properties`:

```properties
storeFile=../.claude/android-keystore/release.jks
storePassword={their-password}
keyAlias=release
keyPassword={their-key-password}
```

### Phase 3: Verify Gradle Configuration

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

### Phase 4: Build Release APK

Run the Gradle build command:

```bash
export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr" && ./gradlew.bat assembleRelease
```

### Phase 5: Report Results

**On success:**

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    ✅ RELEASE BUILD SUCCESSFUL

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📦 APK Location:
   app/build/outputs/apk/release/app-release.apk

📊 Build Info:
   • Package: {package name}
   • Version: {version from build.gradle}
   • Signed: Yes (release keystore)

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
