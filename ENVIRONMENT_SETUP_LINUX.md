# Android Development Environment Setup Guide (Linux)

This guide walks you through setting up a complete Android development environment on Linux (Ubuntu/Debian-based distributions).

---

## Overview

| Section | What You'll Install | Purpose |
|---------|---------------------|---------|
| [1. Prerequisites](#1-prerequisites) | Essential packages | Build tools and dependencies |
| [2. Android Studio](#2-installing-android-studio) | Android Studio + SDK | IDE for Android development |
| [3. Git](#3-installing-git) | Git | Version control |
| [4. Claude Code](#4-installing-claude-code) | Node.js + Claude Code CLI | AI-powered coding assistant |
| [5. GitHub CLI](#5-installing-github-cli) | `gh` command-line tool | GitHub integration |
| [6. Python](#6-installing-python-3) | Python 3 | Scripting and automation |
| [7. Emulator Setup](#7-setting-up-an-emulator) | Android Virtual Device | Run and test your app |
| [8. Mobile MCP](#8-installing-mobile-mcp) | Mobile MCP server | Device automation via Claude |

---

## System Requirements

- Ubuntu 20.04+ / Debian 11+ (or equivalent)
- At least 16 GB RAM (recommended)
- At least 50 GB free disk space
- 64-bit OS

---

## 1. Prerequisites

Update your system and install essential build tools:

```bash
sudo apt update && sudo apt upgrade -y
sudo apt install -y build-essential curl wget unzip
```

### Enable KVM for Emulator Acceleration

```bash
sudo apt install -y qemu-kvm libvirt-daemon-system
sudo adduser $USER kvm
```

Log out and back in for the group change to take effect.

Verify KVM is working:

```bash
kvm-ok
```

---

## 2. Installing Android Studio

### Step 1: Download

1. Navigate to: https://developer.android.com/studio
2. Click **Download Android Studio**
3. Select the **Linux** option
4. Accept the terms and download the `.tar.gz` file

### Step 2: Install

```bash
# Extract to /opt
sudo tar -xzf android-studio-*.tar.gz -C /opt

# Launch Android Studio
/opt/android-studio/bin/studio.sh
```

### Step 3: Create Desktop Entry (Optional)

On first launch, Android Studio will offer to create a desktop entry. Accept this for easy launching.

Alternatively, create one manually:

```bash
cat > ~/.local/share/applications/android-studio.desktop << EOF
[Desktop Entry]
Name=Android Studio
Exec=/opt/android-studio/bin/studio.sh
Icon=/opt/android-studio/bin/studio.png
Type=Application
Categories=Development;IDE;
EOF
```

### Step 4: Initial Setup

1. Select **Do not import settings**
2. Choose your UI theme
3. Select **Standard** installation type
4. Accept all licenses
5. Wait for SDK components to download

### Verify Installation

Open **Android Studio** > **More Actions** > **SDK Manager** and verify at least one Android SDK Platform is installed.

---

## 3. Installing Git

```bash
sudo apt install -y git
```

### Configure Git Identity

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### Verify

```bash
git --version
```

---

## 4. Installing Claude Code

### Step 1: Install Node.js

Using NodeSource repository (recommended for latest LTS):

```bash
curl -fsSL https://deb.nodesource.com/setup_lts.x | sudo -E bash -
sudo apt install -y nodejs
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
# Add GitHub CLI repository
curl -fsSL https://cli.github.com/packages/githubcli-archive-keyring.gpg | sudo dd of=/usr/share/keyrings/githubcli-archive-keyring.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | sudo tee /etc/apt/sources.list.d/github-cli.list > /dev/null
sudo apt update
sudo apt install -y gh
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

Python 3 is usually pre-installed on modern Linux distributions:

```bash
python3 --version
```

If not installed:

```bash
sudo apt install -y python3 python3-pip
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
2. Click the **Run** button (green play) or press `Shift + F10`

### Troubleshooting Emulator Issues

**Emulator won't start:**
- Ensure KVM is properly configured (see Prerequisites)
- Check permissions: `ls -la /dev/kvm` (should show your user has access)

**Slow emulator:**
- Enable hardware acceleration in AVD settings
- Allocate more RAM to the AVD

---

## 8. Installing Mobile MCP

Mobile MCP enables device automation through Claude Code.

### Prerequisites

- Node.js v22+ installed
- Android Emulator running or device connected

### Upgrade Node.js (if needed)

```bash
# Check current version
node --version

# If below v22, upgrade:
curl -fsSL https://deb.nodesource.com/setup_22.x | sudo -E bash -
sudo apt install -y nodejs
```

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
| Run app | `Shift + F10` |
| Debug app | `Shift + F9` |
| Stop app | `Ctrl + F2` |
| Open Settings | `Ctrl + Alt + S` |
| Build Project | `Ctrl + F9` |
| Search Everywhere | `Double Shift` |

---

## Fedora/RHEL Users

Replace `apt` commands with `dnf`:

```bash
# Prerequisites
sudo dnf install -y @development-tools curl wget unzip

# Git
sudo dnf install -y git

# Python
sudo dnf install -y python3 python3-pip

# KVM
sudo dnf install -y qemu-kvm libvirt
sudo usermod -aG kvm $USER
```

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
