# 🚀 Launchpad

> Your AI-powered launchpad for rapidly building Android applications with Claude Code.

---

## What is Launchpad?

Launchpad is a custom Android project template specifically designed to work seamlessly with **Claude Code** — Anthropic's AI-powered development assistant. It provides the foundation and tooling to help you rapidly ideate, iterate, and build Android applications of all kinds.

Whether you're prototyping a new idea, building an MVP, or creating a full-featured app, Launchpad gives you:

- **AI-First Workflow** — Built from the ground up to leverage Claude Code's capabilities
- **Rapid Iteration** — Go from idea to running app in minutes, not hours
- **Smart Automation** — Intelligent commands for building, testing, and deploying
- **Structured Development** — Prompt-based system for organized, reproducible development

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| **AI-Assisted Development** | Claude Code understands your project structure and helps you build faster |
| **Automated Build Pipeline** | `/build`, `/install`, `/run` commands with smart error handling |
| **Intelligent Commits** | Auto-numbered commits with smart message generation |
| **Prompt System** | Create, execute, and log development prompts |
| **Product Doc Integration** | AI reads your product docs to understand your vision |
| **Context-Aware Assistance** | Claude maintains context about your specific app |

---

## 🏁 Getting Started

### Environment Setup

<table>
<tr>
<td width="33%" align="center">

**Windows 11**

✅ Available

[Setup Guide](./ENVIRONMENT_SETUP_WIN11.md)

</td>
<td width="33%" align="center">

**macOS**

🚧 Coming Soon

</td>
<td width="33%" align="center">

**Linux**

🚧 Coming Soon

</td>
</tr>
</table>

### Quick Start

```bash
# 1. Clone the repository
git clone https://github.com/WaseemTheDream/launchpad.git
cd launchpad

# 2. Set up your environment (see guides above)

# 3. Open in Android Studio
# File → Open → Select the launchpad folder

# 4. Start Claude Code in the integrated terminal
claude
```

---

## 🎯 Adapting Launchpad to Your Idea

Launchpad is designed to be **transformed into YOUR Android application**. Here's how to make it yours:

### Step 1: Document Your Product Vision

Place your product documentation in the `product-docs/` folder. The more context you provide, the better Claude can assist you.

**Suggested documents:**

```
product-docs/
├── vision.md       # What is your app? What problem does it solve?
├── features.md     # Core features and functionality
├── audience.md     # Who are your users?
├── design.md       # Design preferences, colors, style
└── references/     # Screenshots, wireframes, inspiration
```

Don't worry about being perfect — even rough notes help Claude understand your vision.

### Step 2: Initialize Your Project with Liftoff

Once your product docs are ready, launch Claude Code and run:

```
/liftoff
```

**The Liftoff command will:**

1. 📖 **Analyze** your product documentation
2. ❓ **Ask** clarifying questions about your vision
3. 🧠 **Generate** context files for AI-assisted development
4. 📝 **Create** an initialization prompt capturing your requirements
5. ⚙️ **Configure** the project for your specific app
6. ✅ **Commit** everything to get you started

### Step 3: Build with AI Assistance

With your project initialized, use Claude Code naturally:

```
"Add a login screen with email and password"

"Create a bottom navigation with Home, Search, and Profile tabs"

"Implement dark mode support"

"Add offline caching for the user's data"

"Fix the crash that happens when the network is unavailable"
```

Claude understands your app's context and generates code that fits your architecture.

---

## 📁 Project Structure

```
launchpad/
├── app/                          # Android application module
│   └── src/main/
│       ├── java/.../             # Kotlin source files
│       └── res/                  # Resources (layouts, strings, etc.)
│
├── product-docs/                 # YOUR product documentation
│   └── (your docs here)
│
├── .claude/
│   ├── commands/                 # Claude Code commands
│   │   ├── build.md              # /build - Compile the app
│   │   ├── install.md            # /install - Deploy to device
│   │   ├── run.md                # /run - Build, install, launch
│   │   ├── liftoff.md            # /liftoff - Initialize your project
│   │   └── execute-prompt.md     # /execute-prompt - Run prompt files
│   │
│   ├── skills/                   # Claude Code skills
│   │   ├── app.md                # App lifecycle management
│   │   ├── commit.md             # Intelligent commits
│   │   └── prompt-creator.md     # Create numbered prompts
│   │
│   ├── prompts/                  # Development prompts
│   │   └── logs/                 # Execution logs
│   │
│   └── context/                  # AI context files (generated)
│
├── ENVIRONMENT_SETUP_WIN11.md    # Windows setup guide
└── README.md                     # You are here
```

---

## 🛠️ Available Commands

| Command | Description |
|---------|-------------|
| `/liftoff` | 🚀 Initialize project with your product vision |
| `/forge` | 🔥 Generate and build your complete MVP |
| `/build` | 🔨 Build the Android app |
| `/build clean` | 🧹 Clean build from scratch |
| `/install` | 📲 Build and install to device/emulator |
| `/run` | ▶️ Build, install, and launch the app |
| `/execute-prompt <file>` | 📜 Execute a prompt file with logging |

---

## 💡 Tips for Working with Claude

1. **Be Specific** — "Add a button" vs "Add a floating action button in the bottom-right that opens a new task dialog"

2. **Provide Context** — Reference your product docs: "Based on the features in product-docs/features.md, implement the search functionality"

3. **Iterate Quickly** — Don't aim for perfection. Build, test, refine.

4. **Use Prompts** — For complex features, create a prompt file to capture requirements

5. **Commit Often** — Use the commit skill to save progress with meaningful messages

---

## 🤝 Contributing

Contributions are welcome! If you have ideas for improving Launchpad:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<div align="center">

**Built with 🤖 and ❤️ for the Android developer community**

[Report Bug](https://github.com/WaseemTheDream/launchpad/issues) · [Request Feature](https://github.com/WaseemTheDream/launchpad/issues)

</div>
