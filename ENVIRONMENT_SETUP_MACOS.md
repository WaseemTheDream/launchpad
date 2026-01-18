# Android Development Environment Setup Guide (macOS)

This guide walks you through setting up a complete Android development environment on macOS.

---

## Overview

| Section | What You'll Install | Purpose |
|---------|---------------------|---------|
| [1. Homebrew](#1-installing-homebrew) | Package manager | Install other tools easily |
| [2. Android Studio](#2-installing-android-studio) | Android Studio + SDK | IDE for Android development |
| [3. Git](#3-installing-git) | Git | Version control |
| [4. Claude Code](#4-installing-claude-code) | Node.js + Claude Code CLI | AI-powered coding assistant |
| [5. GitHub CLI](#5-installing-github-cli) | `gh` command-line tool | GitHub integration |
| [6. Python](#6-installing-python-3) | Python 3 | Scripting and automation |
| [7. Emulator Setup](#7-setting-up-an-emulator) | Android Virtual Device | Run and test your app |
| [8. Mobile MCP](#8-installing-mobile-mcp) | Mobile MCP server | Device automation via Claude |

---

## Prerequisites

- macOS 12 (Monterey) or later
- At least 16 GB RAM (recommended)
- At least 50 GB free disk space
- Administrator access

---

## 1. Installing Homebrew

Homebrew is the de-facto package manager for macOS. It makes installing other tools simple.

### Install Homebrew

Open **Terminal** and run:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Follow the prompts to complete installation.

### Verify Installation

```bash
brew --version
```

---

## 2. Installing Android Studio

### Step 1: Download

1. Navigate to: https://developer.android.com/studio
2. Click **Download Android Studio**
3. Select the **Mac with Apple chip** or **Mac with Intel chip** option as appropriate
4. Accept the terms and download

### Step 2: Install

1. Open the downloaded `.dmg` file
2. Drag **Android Studio** to the **Applications** folder
3. Launch Android Studio from Applications

### Step 3: Initial Setup

1. Select **Do not import settings**
2. Choose your UI theme
3. Select **Standard** installation type
4. Accept all licenses
5. Wait for SDK components to download

### Verify Installation

1. Open **Android Studio** > **More Actions** > **SDK Manager**
2. Verify at least one Android SDK Platform is installed (e.g., Android 14)

---

## 3. Installing Git

Git is likely already installed via Xcode Command Line Tools.

### Check if Git is Installed

```bash
git --version
```

If not installed, you'll be prompted to install Xcode Command Line Tools. Accept the prompt.

### Alternative: Install via Homebrew

```bash
brew install git
```

### Configure Git Identity

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

---

## 4. Installing Claude Code

Claude Code is Anthropic's official CLI for AI-powered development.

### Step 1: Install Node.js

```bash
brew install node
```

Verify installation:

```bash
node --version  # Should be 18+
npm --version
```

### Step 2: Install Claude Code

```bash
npm install -g @anthropic-ai/claude-code
```

### Step 3: Authenticate

```bash
claude
```

Follow the prompts to authenticate with your Anthropic account.

### Verify Installation

```bash
claude --version
```

### Useful Commands

| Command | Description |
|---------|-------------|
| `claude` | Start interactive session |
| `claude "question"` | Ask a one-off question |
| `/help` | Get help within a session |

---

## 5. Installing GitHub CLI

```bash
brew install gh
```

### Authenticate

```bash
gh auth login
```

Select:
- **GitHub.com**
- **HTTPS**
- **Login with a web browser**

Follow the prompts to complete authentication.

### Verify

```bash
gh auth status
```

---

## 6. Installing Python 3

macOS may have Python pre-installed, but you can install the latest version:

```bash
brew install python
```

### Verify

```bash
python3 --version
pip3 --version
```

---

## 7. Setting Up an Emulator

### Step 1: Open Device Manager

In Android Studio:
- Click the **Device Manager** icon in the right sidebar
- Or go to **Tools** > **Device Manager**

### Step 2: Create Virtual Device

1. Click **Create Device**
2. Select **Phone** > **Pixel 7** (or similar)
3. Click **Next**
4. Select a system image (recommended: **API 34** or higher)
   - Download if needed
5. Click **Next** > **Finish**

### Step 3: Start Emulator

Click the **Play** button next to your device in the Device Manager.

### Step 4: Run the App

1. Select your emulator in the device dropdown
2. Click the **Run** button (green play) or press `Ctrl + R`

---

## 8. Installing Mobile MCP

Mobile MCP enables device automation through Claude Code.

### Prerequisites

- Node.js v22+ installed
- Android Emulator running or device connected

### Install

```bash
claude mcp add mobile-mcp -- npx -y @mobilenext/mobile-mcp@latest
```

### Verify

Restart Claude Code. With an emulator running, Mobile MCP tools become available.

### Example Usage

```bash
claude "Take a screenshot of my emulator"
claude "Open the Settings app"
```

### Troubleshooting

If MCP doesn't connect:
- Verify emulator is running: `adb devices`
- Check Node.js version: `node --version` (needs v22+)
- Restart Claude Code

---

## Quick Reference

| Action | Shortcut |
|--------|----------|
| Run app | `Ctrl + R` |
| Debug app | `Ctrl + D` |
| Stop app | `Cmd + F2` |
| Open Settings | `Cmd + ,` |
| Build Project | `Cmd + F9` |
| Search Everywhere | `Double Shift` |

---

## Next Steps

1. **Open the project** in Android Studio
2. **Start Claude Code** in the integrated terminal
3. **Run `/blueprint`** to begin ideating your app

---

## Additional Resources

- [Android Studio Documentation](https://developer.android.com/studio/intro)
- [Kotlin Language Guide](https://kotlinlang.org/docs/home.html)
- [Jetpack Compose Tutorial](https://developer.android.com/jetpack/compose/tutorial)
- [Claude Code Documentation](https://docs.anthropic.com/claude-code)

---

*Last updated: January 2026*
