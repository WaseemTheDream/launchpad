# README and Blueprint Command

[auto-commit]

## Task

Create the project README.md and a powerful `/blueprint` command that helps users transform their product ideas into actionable Android projects.

## Requirements

### 1. Create README.md in Project Root

Create an inspiring, well-structured README that positions this project as an AI-powered Android development launchpad.

**Structure:**

```markdown
# 🚀 Launchpad

> Your AI-powered launchpad for rapidly building Android applications with Claude Code.

## What is Launchpad?

[Explain that this is a custom Android project template specifically designed to work seamlessly with Claude Code, enabling rapid ideation and iteration on Android apps of all kinds]

## ✨ Features

- AI-assisted development workflow
- Automated build, install, and run commands
- Intelligent commit management with auto-numbering
- Prompt-based development system
- Product documentation integration
- Context-aware AI assistance

## 🏁 Getting Started

### Environment Setup

**Windows 11**
See [ENVIRONMENT_SETUP_WIN11.md](./ENVIRONMENT_SETUP_WIN11.md) for complete setup instructions.

**macOS** *(Coming Soon)*
Setup guide for macOS is in development.

**Linux** *(Coming Soon)*
Setup guide for Linux distributions is in development.

### Quick Start

1. Clone this repository
2. Set up your development environment (see above)
3. Open the project in Android Studio
4. Launch Claude Code in the terminal

## 🎯 Adapting Launchpad to Your Idea

Launchpad is designed to be transformed into YOUR Android application. Here's how:

### Step 1: Document Your Product Vision

Create your product documentation in the `product-docs/` folder:

- `product-docs/vision.md` - Your app's purpose and goals
- `product-docs/features.md` - Core features and functionality
- `product-docs/audience.md` - Target users and use cases
- `product-docs/design.md` - Design preferences and branding
- Add any additional docs, wireframes, or references

### Step 2: Initialize Your Project with Blueprint

Once your product docs are in place, use Claude Code to run:

```
/blueprint
```

The Blueprint command will:
- Analyze your product documentation
- Ask clarifying questions about your vision
- Generate context files for AI-assisted development
- Create an initialization prompt capturing your product requirements
- Set up the project structure for your specific app

### Step 3: Build with AI Assistance

With your project initialized, use Claude Code to:
- Implement features: "Add user authentication"
- Create UI: "Design the home screen"
- Fix issues: "Debug the login flow"
- Iterate rapidly: "Refactor the data layer"

## 📁 Project Structure

[Include directory tree showing key folders]

## 🛠️ Available Commands

| Command | Description |
|---------|-------------|
| `/build` | Build the Android app |
| `/install` | Build and install to device/emulator |
| `/run` | Build, install, and launch the app |
| `/blueprint` | Initialize project with your product vision |
| `/execute-prompt <file>` | Execute a prompt file |

## 🤝 Contributing

[Standard contribution guidelines]

## 📄 License

[License information]
```

### 2. Create product-docs/ Directory

Create the folder structure:
```
product-docs/
├── README.md          # Instructions for documenting your product
├── .gitkeep
```

The README.md inside product-docs should explain:
- What to document
- Suggested file structure
- Examples of good product documentation
- Tips for effective AI collaboration

### 3. Create /blueprint Command

Create `.claude/commands/blueprint.md` - an inspiring command that transforms product docs into an initialized project.

**The Blueprint command should:**

1. **Welcome & Introduction**
   - Display an inspiring welcome message
   - Explain what's about to happen

2. **Scan Product Docs**
   - Read all files in `product-docs/`
   - Analyze and summarize what was found
   - Identify gaps in documentation

3. **Interactive Questionnaire**
   Ask comprehensive questions about:

   **Product Vision**
   - What is the name of your app?
   - Describe your app in one sentence
   - What problem does it solve?
   - What's your unique value proposition?

   **Target Audience**
   - Who are your primary users?
   - What devices will they use? (phone, tablet, watch, TV)
   - Any accessibility requirements?

   **Core Features**
   - What are the 3-5 must-have features for MVP?
   - What features are nice-to-have for later?
   - Any features explicitly out of scope?

   **Technical Requirements**
   - Does the app need user authentication?
   - What data needs to be stored? (local, cloud, both)
   - Offline functionality needed?
   - Any third-party APIs or services?
   - Backend requirements?

   **Design & UX**
   - Design style preference (Material You, custom, minimal)?
   - Color scheme or branding guidelines?
   - Any reference apps for inspiration?

   **Business Context**
   - Monetization strategy (free, paid, freemium, ads)?
   - Target launch timeline?
   - Any compliance requirements (GDPR, COPPA, etc.)?

4. **Generate Context Files**
   Create `.claude/context/` directory with:
   - `product-vision.md` - Consolidated product vision
   - `technical-spec.md` - Technical requirements
   - `feature-roadmap.md` - Prioritized features
   - `design-guidelines.md` - Design preferences

5. **Create Initialization Prompt**
   Generate a prompt file `0004-initialize-{app-name}.md` that:
   - Captures all gathered information
   - Provides instructions for the initial app setup
   - Includes renaming package from com.example.launchpad
   - Sets up initial screens and navigation

6. **Update Project Files**
   - Update app name in strings.xml
   - Optionally update package name references

7. **Commit Everything**
   Create commit: `[NNNN] feat: Initialize {app-name} project with Blueprint`

8. **Next Steps**
   Display clear next steps for the user

## Expected Outcome

- Professional README.md that welcomes users and explains the project
- product-docs/ folder ready for user documentation
- Powerful /blueprint command that transforms ideas into initialized projects
- Seamless onboarding experience for new users

## Commit

Commit: `[0003] feat: Add README and Blueprint command for project initialization`
