# Android Keystore Directory

This folder contains your Android signing keystore for release builds.

**IMPORTANT**: All `.jks`, `.keystore`, and `.properties` files in this directory are automatically ignored by git to protect your credentials.

## Setup Instructions

See the main project README for detailed instructions on:
- Creating a new keystore (first-time Android developers)
- Using an existing keystore (experienced Android developers)

## Required Files

After setup, this folder should contain:
- `release.jks` - Your keystore file
- `keystore.properties` - Your keystore credentials

## Security Notice

Never commit keystore files or credentials to version control. The `.gitignore` in this folder protects these files automatically.
