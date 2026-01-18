# Releases

This folder contains versioned release APKs for distribution.

## Structure

```
releases/
├── VERSION              # Current version number (major.minor.patch)
├── CHANGELOG.md         # Release history and notes
├── README.md            # This file
└── {app-name}-v{X.Y.Z}.apk  # Versioned release APKs
```

## Version Format

This project uses [Semantic Versioning](https://semver.org/):

- **Major** (X.0.0): Breaking changes, major new features
- **Minor** (0.X.0): New features, enhancements
- **Patch** (0.0.X): Bug fixes, small improvements

## Creating a Release

Run the `/release` command with an optional version bump type:

```
/release              # Uses current version
/release patch        # Bumps patch: 1.0.0 → 1.0.1
/release minor        # Bumps minor: 1.0.0 → 1.1.0
/release major        # Bumps major: 1.0.0 → 2.0.0
/release 2.0.0        # Sets specific version
```

## Release Artifacts

Each release creates:
1. Signed APK: `{app-name}-v{version}.apk`
2. CHANGELOG.md entry with release notes
3. Git commit and tag: `v{version}`

## Installation

To install a release APK:

1. Transfer the APK to your Android device
2. Enable "Install from unknown sources" in Settings
3. Open the APK file to install

Or use ADB:
```bash
adb install {app-name}-v{version}.apk
```
