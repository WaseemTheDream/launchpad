# Android Development Environment Setup Guide

This guide walks you through setting up a complete Android development environment on Windows 11, including Android Studio, Git, Claude Code, and running your first app on an emulator.

---

## Overview

| Section | What You'll Install | Purpose |
|---------|---------------------|---------|
| [1. Android Studio](#1-installing-android-studio) | Android Studio + SDK | IDE for Android development |
| [2. Git](#2-installing-git) | Git for Windows | Version control |
| [3. Claude Code](#3-installing-claude-code-for-windows) | Node.js + Claude Code CLI | AI-powered coding assistant |
| [4. Claude Code Extension](#4-installing-the-claude-code-extension-for-android-studio) | Android Studio plugin | IDE integration for Claude |
| [5. GitHub CLI](#5-installing-github-cli) | `gh` command-line tool | GitHub integration |
| [6. Python](#6-installing-python-3) | Python 3 | Scripting and automation |
| [7. Emulator Setup](#7-setting-up-an-emulator-and-running-the-launchpad-app) | Android Virtual Device | Run and test your app |
| [8. Mobile MCP](#8-installing-mobile-mcp-for-claude-code) | Mobile MCP server | Device automation via Claude |

**Estimated total setup time**: Follow sections 1-8 in order. Section 8 (running the app) confirms everything works.

---

## Prerequisites

- Windows 11 (64-bit)
- At least 16 GB RAM (recommended)
- At least 50 GB free disk space
- Internet connection
- Administrator access to your computer

---

## 1. Installing Android Studio

Android Studio is the official IDE for Android development.

### Step 1: Download Android Studio

1. Open your web browser and navigate to: https://developer.android.com/studio
2. Click the **Download Android Studio** button
3. Accept the terms and conditions
4. The download will start automatically (approximately 1 GB)

### Step 2: Run the Installer

1. Locate the downloaded file (e.g., `android-studio-<version>-windows.exe`)
2. Double-click to run the installer
3. If prompted by User Account Control, click **Yes**

### Step 3: Complete the Installation Wizard

1. Click **Next** on the welcome screen
2. **Choose Components**: Ensure the following are checked:
   - Android Studio
   - Android Virtual Device
3. Click **Next**
4. **Choose Install Location**: Accept the default or choose a custom path
   - Default: `C:\Program Files\Android\Android Studio`
5. Click **Next**
6. **Choose Start Menu Folder**: Accept the default
7. Click **Install**
8. Wait for the installation to complete
9. Click **Next**, then **Finish**

### Step 4: Initial Setup Wizard

When Android Studio launches for the first time:

1. Select **Do not import settings** (for a fresh install)
2. Click **OK**
3. Choose your UI theme (Darcula for dark mode, or Light)
4. Click **Next**
5. **Install Type**: Select **Standard** (recommended for beginners)
6. Click **Next**
7. Review the SDK Components to be downloaded:
   - Android SDK
   - Android SDK Platform
   - Android Virtual Device
   - Performance (Intel HAXM or Windows Hypervisor)
8. Click **Next**
9. Accept all license agreements by selecting each component and clicking **Accept**
10. Click **Finish**
11. Wait for the SDK components to download (this may take several minutes)

### Step 5: Verify Installation

1. Once complete, you should see the Android Studio Welcome screen
2. Click **More Actions** > **SDK Manager**
3. Verify that at least one Android SDK Platform is installed (e.g., Android 14.0 "UpsideDownCake")

---

## 2. Installing Git

Git is essential for version control and working with GitHub repositories.

### Step 1: Download Git for Windows

1. Navigate to: https://git-scm.com/download/win
2. The download should start automatically for the 64-bit version
3. If not, click **64-bit Git for Windows Setup**

### Step 2: Run the Installer

1. Locate and double-click the downloaded file (e.g., `Git-<version>-64-bit.exe`)
2. Click **Yes** if prompted by User Account Control

### Step 3: Configure Installation Options

Follow the installer prompts with these recommended settings:

1. **Select Components**: Keep defaults, ensure these are checked:
   - Windows Explorer integration
   - Git Bash Here
   - Git GUI Here
   - Associate .git* configuration files with the default text editor

2. **Default Editor**: Choose your preferred editor
   - Recommended: **Use Visual Studio Code as Git's default editor** (if installed)
   - Alternative: **Use Notepad++ as Git's default editor**
   - Fallback: **Use Vim** (default)

3. **Initial Branch Name**: Select **Override the default branch name for new repositories**
   - Enter: `main`

4. **PATH Environment**: Select **Git from the command line and also from 3rd-party software** (recommended)

5. **SSH Executable**: Select **Use bundled OpenSSH**

6. **HTTPS Transport Backend**: Select **Use the native Windows Secure Channel library**

7. **Line Ending Conversions**: Select **Checkout Windows-style, commit Unix-style line endings**

8. **Terminal Emulator**: Select **Use Windows' default console window**

9. **Default Behavior of `git pull`**: Select **Default (fast-forward or merge)**

10. **Credential Helper**: Select **Git Credential Manager**

11. **Extra Options**: Keep defaults:
    - Enable file system caching
    - Enable symbolic links

12. Click **Install**
13. Click **Finish** when complete

### Step 4: Verify Installation

1. Open **Command Prompt** or **PowerShell**
2. Run:
   ```
   git --version
   ```
3. You should see output like: `git version 2.x.x.windows.x`

### Step 5: Configure Git Identity

Set up your Git identity (required for commits):

```
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

---

## 3. Installing Claude Code for Windows

Claude Code is Anthropic's official CLI tool that brings Claude's AI capabilities directly to your terminal.

### Step 1: Install Node.js (Prerequisite)

Claude Code requires Node.js version 18 or higher.

#### Option A: Using Windows Package Manager (Recommended)

1. Open **PowerShell** or **Command Prompt**
2. Run:
   ```
   winget install OpenJS.NodeJS.LTS
   ```
3. Close and reopen your terminal to refresh the PATH

#### Option B: Using the Installer

1. Navigate to: https://nodejs.org/
2. Download the **LTS** (Long Term Support) version
3. Run the installer and follow the prompts:
   - Accept the license agreement
   - Keep the default installation path
   - Ensure **Add to PATH** is selected
   - Click **Install**

#### Verify Node.js Installation

Open a new terminal and run:

```
node --version
npm --version
```

You should see version numbers for both (Node.js 18+ and npm 9+).

#### Troubleshooting: Node.js Not in PATH

If you get `'node' is not recognized` or `command not found` errors, Node.js was installed but not added to your system PATH. This commonly happens with certain installation methods.

**Manual Fix:**

1. Press `Win + R`, type `sysdm.cpl`, press Enter
2. Click the **Advanced** tab
3. Click **Environment Variables**
4. Under **System variables**, find **Path** and click **Edit**
5. Click **New** and add: `C:\Program Files\nodejs`
6. Click **OK** on all dialogs
7. Close and reopen your terminal

**Automated Fix (Run in PowerShell as Administrator):**

```powershell
[Environment]::SetEnvironmentVariable("Path", [Environment]::GetEnvironmentVariable("Path", "Machine") + ";C:\Program Files\nodejs", "Machine")
```

> **Note for Claude Code:** To fix PATH issues automatically, run this command:
> ```
> powershell.exe -Command "[Environment]::SetEnvironmentVariable('Path', [Environment]::GetEnvironmentVariable('Path', 'Machine') + ';C:\Program Files\nodejs', 'Machine')"
> ```
> After running, instruct the user to restart their terminal/Claude Code session for changes to take effect.

### Step 2: Install Claude Code

#### Option A: Using Windows Package Manager (Recommended)

Open **PowerShell** or **Command Prompt** and run:

```
winget install Anthropic.Claude
```

Wait for the installation to complete, then close and reopen your terminal.

#### Option B: Using npm

Open **PowerShell** or **Command Prompt** and run:

```
npm install -g @anthropic-ai/claude-code
```

Wait for the installation to complete.

### Step 3: Authenticate Claude Code

1. Open a new terminal window
2. Navigate to any project directory
3. Run:
   ```
   claude
   ```
4. Follow the prompts to authenticate with your Anthropic account
5. A browser window will open for authentication
6. After authenticating, return to your terminal

### Step 4: Verify Installation

Run the following command to verify Claude Code is working:

```
claude --version
```

### Helpful Claude Code Commands

| Command | Description |
|---------|-------------|
| `claude` | Start an interactive session |
| `claude "your question"` | Ask a one-off question |
| `claude --help` | View all available commands |
| `/help` | Get help within a session |
| `/clear` | Clear conversation history |

---

## 4. Installing the Claude Code Extension for Android Studio

The Claude Code extension integrates Claude's AI capabilities directly into Android Studio.

### Step 1: Open Android Studio Settings

1. Launch **Android Studio**
2. Open Settings using one of these methods:
   - Press `Ctrl + Alt + S`
   - Or go to **File** > **Settings** (on Windows)

### Step 2: Navigate to Plugins

1. In the Settings window, select **Plugins** from the left sidebar
2. Click on the **Marketplace** tab at the top

### Step 3: Install the Extension

1. In the search bar, type: **Claude Code**
2. Locate the **Claude Code** plugin in the search results
3. Click the **Install** button next to it
4. Wait for the installation to complete
5. Click **Restart IDE** when prompted

### Step 4: Configure the Extension

After Android Studio restarts:

1. Open **Settings** (`Ctrl + Alt + S`)
2. Navigate to **Tools** > **Claude Code** (or search for "Claude")
3. Configure any settings as needed:
   - Ensure the Claude Code CLI path is correctly detected
   - Adjust any preferences for inline suggestions or chat behavior
4. Click **Apply** and **OK**

### Step 5: Verify the Extension

1. You should see a Claude Code icon in the sidebar or tool window bar
2. Click on it to open the Claude Code panel
3. Try sending a message to verify it's connected

---

## 5. Installing GitHub CLI

GitHub CLI (`gh`) allows you to interact with GitHub directly from your terminal — creating repositories, managing pull requests, and more.

> **Tip:** If you already have Claude Code installed and running, you can simply ask Claude to install GitHub CLI for you! Just open a terminal in your project directory, run `claude`, and say:
>
> *"Install GitHub CLI and authenticate with my GitHub account"*
>
> Claude will handle the installation and guide you through authentication.

### Option A: Install Using Windows Package Manager (Recommended)

1. Open **PowerShell** or **Command Prompt**
2. Run:
   ```
   winget install GitHub.cli
   ```
3. Wait for the installation to complete
4. Close and reopen your terminal to refresh the PATH

### Option B: Install Using the Installer

1. Navigate to: https://cli.github.com/
2. Click **Download for Windows**
3. Run the downloaded `.msi` installer
4. Follow the installation prompts
5. Close and reopen your terminal

### Step 2: Verify Installation

Open a new terminal and run:

```
gh --version
```

You should see output like: `gh version 2.x.x (2024-xx-xx)`

### Step 3: Authenticate with GitHub

1. Run:
   ```
   gh auth login
   ```
2. When prompted, select:
   - **GitHub.com** (for regular GitHub accounts)
   - **HTTPS** as the preferred protocol
   - **Login with a web browser**
3. Copy the one-time code displayed in your terminal
4. Press **Enter** to open your browser
5. Paste the code on the GitHub authentication page
6. Click **Authorize github** to complete authentication

### Step 4: Verify Authentication

Run:

```
gh auth status
```

You should see a message confirming you're logged in to github.com.

### Helpful GitHub CLI Commands

| Command | Description |
|---------|-------------|
| `gh repo create` | Create a new repository |
| `gh repo clone <repo>` | Clone a repository |
| `gh pr create` | Create a pull request |
| `gh pr list` | List open pull requests |
| `gh issue create` | Create an issue |
| `gh issue list` | List open issues |
| `gh auth status` | Check authentication status |

---

## 6. Installing Python 3

Python is a versatile programming language useful for scripting, automation, and many development tools.

> **Note for Claude Code:** These steps are designed to be executable. Use the winget command in Option A to install Python automatically.

### Option A: Using Windows Package Manager (Recommended)

1. Open **PowerShell** or **Command Prompt**
2. Run:
   ```
   winget install Python.Python.3.12
   ```
3. Wait for the installation to complete
4. Close and reopen your terminal to refresh the PATH

### Option B: Using the Installer

1. Navigate to: https://www.python.org/downloads/
2. Click **Download Python 3.x.x** (latest stable version)
3. Run the downloaded installer
4. **Important**: Check the box **Add Python to PATH** at the bottom of the installer
5. Click **Install Now**
6. Wait for the installation to complete
7. Click **Close**

### Verify Installation

Open a new terminal and run:

```
python --version
```

You should see output like: `Python 3.12.x`

Also verify pip (Python's package manager):

```
pip --version
```

You should see output like: `pip 24.x.x from ...`

### Configure Python (Optional)

Upgrade pip to the latest version:

```
python -m pip install --upgrade pip
```

### Helpful Python Commands

| Command | Description |
|---------|-------------|
| `python` | Start Python interactive shell |
| `python script.py` | Run a Python script |
| `pip install <package>` | Install a Python package |
| `pip list` | List installed packages |
| `pip freeze > requirements.txt` | Export dependencies to file |
| `pip install -r requirements.txt` | Install dependencies from file |

---

## 7. Setting Up an Emulator and Running the Launchpad App

Now let's create an Android emulator and run the Launchpad app on it.

### Step 1: Open the Launchpad Project

1. Launch **Android Studio**
2. Click **Open** on the Welcome screen
3. Navigate to the project directory:
   ```
   C:\Users\Waz\AndroidStudioProjects\launchpad
   ```
4. Click **OK**
5. Wait for Android Studio to sync the Gradle project (this may take a few minutes on first open)

### Step 2: Open Device Manager

1. In Android Studio, locate the **Device Manager** using one of these methods:
   - Click the **Device Manager** icon in the right sidebar (phone icon)
   - Or go to **Tools** > **Device Manager**

### Step 3: Create a Virtual Device

1. In the Device Manager panel, click **Create Device** (or the **+** button)
2. **Select Hardware**:
   - Choose **Phone** from the Category list
   - Select a device (recommended: **Pixel 7** or **Pixel 8**)
   - Click **Next**

3. **Select System Image**:
   - Click the **Recommended** tab
   - Choose an Android version (recommended: **API 34** "UpsideDownCake" or higher)
   - If the image shows a download icon, click it to download the system image
   - Wait for the download to complete
   - Select the downloaded image and click **Next**

4. **Configure AVD (Android Virtual Device)**:
   - **AVD Name**: Keep the default or enter a custom name (e.g., "Pixel 7 API 34")
   - **Startup Orientation**: Portrait (default)
   - Click **Show Advanced Settings** for more options:
     - **RAM**: 2048 MB (increase if you have more system RAM)
     - **VM Heap**: 512 MB
     - **Internal Storage**: 2048 MB
   - Click **Finish**

### Step 4: Start the Emulator

1. In the Device Manager, find your newly created device
2. Click the **Play** button (triangle icon) next to the device name
3. Wait for the emulator to boot (this may take 1-2 minutes on first launch)
4. The emulator window will appear showing the Android home screen

### Step 5: Build and Run the Launchpad App

1. In Android Studio, ensure your emulator is selected in the device dropdown (toolbar near the top)
   - It should show something like "Pixel 7 API 34"
2. Click the **Run** button (green play triangle) or press `Shift + F10`
3. Wait for the build process to complete:
   - You can monitor progress in the **Build** tab at the bottom
4. Once built, the app will automatically install and launch on the emulator
5. You should see the Launchpad app with "Hello Android!" displayed

### Troubleshooting Emulator Issues

**Emulator won't start:**
- Ensure virtualization is enabled in your BIOS (Intel VT-x or AMD-V)
- Check that Windows Hypervisor Platform is enabled:
  1. Open **Control Panel** > **Programs** > **Turn Windows features on or off**
  2. Enable **Windows Hypervisor Platform**
  3. Restart your computer

**Emulator is slow:**
- Allocate more RAM to the AVD (at least 2 GB)
- Enable hardware acceleration in AVD settings
- Close other memory-intensive applications

**App won't install:**
- Clean the project: **Build** > **Clean Project**
- Rebuild: **Build** > **Rebuild Project**
- Invalidate caches: **File** > **Invalidate Caches** > **Invalidate and Restart**

---

## 8. Installing Mobile MCP for Claude Code

> **⚠️ Experimental on Windows 11**: Mobile MCP has not yet been proven to work reliably on Windows 11. You may encounter connection issues or other problems. This section is included for users who want to try it, but expect that it may not function as expected. The steps below are still recommended to attempt, as future updates may resolve compatibility issues.

Mobile MCP is a Model Context Protocol server that enables mobile automation through Claude Code. It provides tools for device management, app operations, screen interaction, and input handling for both Android and iOS devices.

### Prerequisites

- **Node.js v22+** installed (see Section 3, Step 1)
- **Claude Code** installed and authenticated (see Section 3)
- **Android Emulator** running or physical device connected (see Section 7)

### Step 1: Install Mobile MCP

Open **PowerShell** or **Command Prompt** and run:

```
claude mcp add mobile-mcp -- npx -y @mobilenext/mobile-mcp@latest
```

This adds the Mobile MCP server to your Claude Code configuration.

### Step 2: Verify Installation

1. Close any existing Claude Code sessions
2. Start a new Claude Code session:
   ```
   claude
   ```
3. The Mobile MCP tools will be automatically available when you have a device/emulator running

### Step 3: Using Mobile MCP

With an emulator running, you can ask Claude Code to:

- Take screenshots of the device
- Tap on screen elements
- Type text into input fields
- Scroll and swipe
- Launch and interact with apps
- Inspect UI elements

### Example Usage

Start your emulator (see Section 7), then in Claude Code:

```
claude "Take a screenshot of my emulator"
claude "Open the Settings app and navigate to Wi-Fi"
```

### Troubleshooting

**MCP server not connecting:**
- Ensure an emulator is running or device is connected
- Verify with: `adb devices`
- Restart Claude Code to reload MCP servers

**Node.js not found / not in PATH:**
- If `node --version` fails with "not recognized" or "command not found", Node.js is not in your system PATH
- See [Troubleshooting: Node.js Not in PATH](#troubleshooting-nodejs-not-in-path) in Section 3
- After fixing PATH, restart Claude Code completely for changes to take effect

> **Note for Claude Code:** If mobile-mcp fails to connect, first check if Node.js is accessible:
> ```
> node --version
> ```
> If this fails, Node.js is likely installed but not in PATH. Check if it exists:
> ```
> ls "/c/Program Files/nodejs/node.exe" 2>/dev/null && echo "Node installed but not in PATH"
> ```
> If found, fix the PATH with:
> ```
> powershell.exe -Command "[Environment]::SetEnvironmentVariable('Path', [Environment]::GetEnvironmentVariable('Path', 'Machine') + ';C:\Program Files\nodejs', 'Machine')"
> ```
> Then instruct user to restart their terminal/Claude Code session.

**Node.js version error:**
- Mobile MCP requires Node.js v22+
- Check version: `node --version`
- Update if needed: `winget upgrade OpenJS.NodeJS.LTS`

---

## Quick Reference: Keyboard Shortcuts

| Action | Shortcut |
|--------|----------|
| Run app | `Shift + F10` |
| Debug app | `Shift + F9` |
| Stop app | `Ctrl + F2` |
| Open Settings | `Ctrl + Alt + S` |
| Sync Gradle | `Ctrl + Shift + O` |
| Build Project | `Ctrl + F9` |
| Search Everywhere | `Double Shift` |
| Find in Files | `Ctrl + Shift + F` |

---

## Next Steps

Now that your environment is set up, you can:

1. **Explore the codebase**: Open `MainActivity.kt` to see the main app code
2. **Modify the UI**: Edit the `Greeting` composable to change the displayed text
3. **Learn Jetpack Compose**: Visit https://developer.android.com/jetpack/compose
4. **Use Claude Code**: Ask Claude for help with Android development questions

---

## Additional Resources

- [Android Studio Documentation](https://developer.android.com/studio/intro)
- [Kotlin Language Guide](https://kotlinlang.org/docs/home.html)
- [Jetpack Compose Tutorial](https://developer.android.com/jetpack/compose/tutorial)
- [Git Documentation](https://git-scm.com/doc)
- [Claude Code Documentation](https://docs.anthropic.com/claude-code)

---

*Last updated: January 2026*
