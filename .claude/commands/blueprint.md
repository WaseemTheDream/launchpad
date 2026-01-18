# 📐 Blueprint

Design the foundation for your Android project.

## Overview

Blueprint is your ideation and planning phase — where ideas take shape. It analyzes your product documentation, asks clarifying questions, and creates the architectural foundation for your app.

---

## Instructions

### Phase 1: Welcome Sequence

Display an inspiring welcome:

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    📐 B L U E P R I N T

    Designing your Android project...

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Welcome to Blueprint! I'm going to help you design the foundation for YOUR app.

Here's what we'll do:
  1. 📖 Review your product documentation
  2. ❓ Ask questions to fill in the gaps
  3. 🧠 Create AI context files for your project
  4. 📝 Generate an initialization prompt
  5. ✅ Commit everything to get you started

Let's begin!
```

---

### Phase 2: Scan Product Documentation

1. **Read all files** in `product-docs/` directory
2. **Summarize** what you found:
   - List each document and its key points
   - Note what's well-documented
   - Identify gaps that need clarification

Display:
```
📖 Analyzing your product documentation...

Found X documents in product-docs/:
  ✓ vision.md - [brief summary]
  ✓ features.md - [brief summary]
  ...

Key information gathered:
  • App concept: [summary]
  • Target users: [summary]
  • Core features: [summary]

Gaps identified:
  • [Missing information 1]
  • [Missing information 2]
```

If `product-docs/` is empty or only contains the README, inform the user and offer to proceed with questions only.

---

### Phase 3: Interactive Questionnaire

Ask the user comprehensive questions using the AskUserQuestion tool. Gather ALL necessary information to fully understand their vision.

**IMPORTANT**: Use the AskUserQuestion tool for multiple-choice questions. For open-ended questions, ask directly in conversation.

#### Section A: Product Vision

1. **App Name**
   - "What would you like to name your app?"

2. **One-Liner**
   - "Describe your app in one sentence — what does it do?"

3. **Problem & Solution**
   - "What problem does your app solve, and how does it solve it?"

4. **Unique Value**
   - "What makes your app different from existing solutions?"

#### Section B: Target Audience

5. **Primary Users**
   - "Who are your primary users? Describe them."

6. **Device Types** (AskUserQuestion - multiSelect)
   - Options: Phone, Tablet, Wear OS (Watch), Android TV
   - "Which devices will your app support?"

7. **Accessibility**
   - "Any specific accessibility requirements? (screen readers, color blindness, etc.)"

#### Section C: Core Features

8. **MVP Features**
   - "What are the 3-5 MUST-HAVE features for your first version?"

9. **Future Features**
   - "What features would you like to add later?"

10. **Out of Scope**
    - "Is there anything explicitly OUT of scope for this app?"

#### Section D: Technical Requirements

11. **Authentication** (AskUserQuestion)
    - Options: None, Email/Password, Social Login (Google/Facebook), Phone Number, Multiple Methods
    - "What authentication does your app need?"

12. **Data Storage** (AskUserQuestion)
    - Options: Local only, Cloud only, Both local and cloud, No data storage needed
    - "How should user data be stored?"

13. **Offline Support** (AskUserQuestion)
    - Options: Not needed, Basic (cache data), Full offline mode
    - "Does your app need to work offline?"

14. **Backend/APIs**
    - "Does your app need a backend? Any third-party APIs or services?"

15. **Notifications** (AskUserQuestion)
    - Options: None, Local notifications only, Push notifications, Both
    - "What notification capabilities do you need?"

#### Section E: Design & UX

16. **Design Style** (AskUserQuestion)
    - Options: Material Design 3 (Google's latest), Material You (dynamic colors), Custom/Branded, Minimal/Clean
    - "What design style do you prefer?"

17. **Color Scheme**
    - "Do you have brand colors? If so, what are they? (or describe the mood/feeling)"

18. **Navigation Pattern** (AskUserQuestion)
    - Options: Bottom Navigation, Navigation Drawer (hamburger), Tab-based, Simple (back/forward), Other
    - "How should users navigate your app?"

19. **Inspiration**
    - "Are there any apps whose design you admire or want to emulate?"

#### Section F: Business Context

20. **Monetization** (AskUserQuestion)
    - Options: Free (no monetization), Paid app, Freemium (free + premium), Ads, Subscriptions, In-app purchases, Not decided yet
    - "How do you plan to monetize?"

21. **Timeline**
    - "What's your target timeline or deadline?"

22. **Compliance**
    - "Any compliance requirements? (GDPR, COPPA for kids, HIPAA for health, etc.)"

#### Section G: Additional Context

23. **Existing Assets**
    - "Do you have any existing assets? (logos, designs, code, backend)"

24. **Team & Skills**
    - "Is this a solo project or team? What's your Android/development experience?"

25. **Anything Else**
    - "Is there anything else I should know about your app or vision?"

---

### Phase 4: Generate Context Files

Create the `.claude/context/` directory and generate comprehensive context files:

#### 4.1 Create Directory
```bash
mkdir -p .claude/context
```

#### 4.2 Create product-vision.md
```markdown
# Product Vision: {App Name}

## Overview
{One-liner description}

## Problem Statement
{The problem this app solves}

## Solution
{How the app solves it}

## Unique Value Proposition
{What makes it different}

## Target Audience
{Description of users}
- Primary devices: {devices}
- Accessibility: {requirements}
```

#### 4.3 Create technical-spec.md
```markdown
# Technical Specification: {App Name}

## Authentication
{Authentication approach}

## Data Architecture
- Storage: {local/cloud/both}
- Offline: {offline approach}

## Backend & APIs
{Backend requirements}

## Notifications
{Notification requirements}

## Third-Party Services
{List of services}
```

#### 4.4 Create feature-roadmap.md
```markdown
# Feature Roadmap: {App Name}

## MVP (Phase 1)
- [ ] {Feature 1}
- [ ] {Feature 2}
- [ ] {Feature 3}

## Phase 2
- [ ] {Feature 4}
- [ ] {Feature 5}

## Future Backlog
- [ ] {Feature 6}

## Out of Scope
- {Excluded item 1}
```

#### 4.5 Create design-guidelines.md
```markdown
# Design Guidelines: {App Name}

## Style
{Design style}

## Colors
- Primary: {color}
- Secondary: {color}
- Accent: {color}

## Navigation
{Navigation pattern}

## Inspiration
{Reference apps}
```

---

### Phase 5: Create Initialization Prompt

Determine the next prompt number by checking existing prompts in `.claude/prompts/`.

Create a new prompt file: `.claude/prompts/NNNN-initialize-{app-name-slug}.md`

```markdown
# Initialize {App Name}

[auto-commit]

## Overview
This prompt initializes the Launchpad project as {App Name}.

## App Summary
{One-liner}

## Task

Transform this Launchpad template into {App Name} by:

### 1. Rename Package
- Change package from `com.example.launchpad` to `com.{appropriate.package.name}`
- Update all file locations and imports
- Update AndroidManifest.xml
- Update build.gradle.kts

### 2. Update App Identity
- Update app_name in strings.xml to "{App Name}"
- Update application label in AndroidManifest

### 3. Set Up Navigation
- Implement {navigation pattern} navigation
- Create placeholder screens for main sections:
  {List of main screens}

### 4. Configure Theme
- Update colors.xml with brand colors: {colors}
- Configure Material theme in Theme.kt
- Set up {design style} styling

### 5. Create Data Layer Foundation
- Set up {storage approach} data architecture
- Create placeholder repositories
- {If auth needed} Prepare authentication structure

### 6. MVP Feature Stubs
Create placeholder implementations for MVP features:
{List of MVP features}

## Technical Notes
- Authentication: {auth approach}
- Offline: {offline approach}
- Backend: {backend notes}

## Commit
Commit: `[NNNN] feat: Initialize {App Name} with core structure`
```

---

### Phase 6: Update strings.xml

Update the app name in `app/src/main/res/values/strings.xml`:
```xml
<string name="app_name">{App Name}</string>
```

---

### Phase 7: Create Blueprint Session Documentation

Create a documentation prompt file that records the entire blueprint session.

Determine the next prompt number and create: `.claude/prompts/NNNN-blueprint-session-{app-name-slug}.md`

```markdown
# Blueprint Session: {App Name}

> Session documentation for the /blueprint command execution

## Session Info
- **Date**: {current date/time}
- **App Name**: {App Name}

## Product Documentation Analyzed

{List of files found in product-docs/ and brief summaries of each}

## Interactive Questionnaire

### Product Vision
**Q: What is the name of your app?**
A: {user's answer}

**Q: Describe your app in one sentence**
A: {user's answer}

**Q: What problem does it solve?**
A: {user's answer}

**Q: What makes it unique?**
A: {user's answer}

### Target Audience
**Q: Who are your primary users?**
A: {user's answer}

**Q: Device types?**
A: {user's selection}

**Q: Accessibility requirements?**
A: {user's answer}

### Core Features
**Q: MVP features (3-5 must-haves)?**
A: {user's answer}

**Q: Future features?**
A: {user's answer}

**Q: Out of scope?**
A: {user's answer}

### Technical Requirements
**Q: Authentication?**
A: {user's selection}

**Q: Data storage?**
A: {user's selection}

**Q: Offline support?**
A: {user's selection}

**Q: Backend/APIs?**
A: {user's answer}

**Q: Notifications?**
A: {user's selection}

### Design & UX
**Q: Design style?**
A: {user's selection}

**Q: Color scheme?**
A: {user's answer}

**Q: Navigation pattern?**
A: {user's selection}

**Q: Inspiration apps?**
A: {user's answer}

### Business Context
**Q: Monetization?**
A: {user's selection}

**Q: Timeline?**
A: {user's answer}

**Q: Compliance requirements?**
A: {user's answer}

### Additional Context
**Q: Existing assets?**
A: {user's answer}

**Q: Team & skills?**
A: {user's answer}

**Q: Anything else?**
A: {user's answer}

## Generated Context Files

The following files were created in `.claude/context/`:

### product-vision.md
{Summary of what was written}

### technical-spec.md
{Summary of what was written}

### feature-roadmap.md
{Summary of what was written}

### design-guidelines.md
{Summary of what was written}

## Next Steps Chosen

User selected: {their choice from the next steps options}

## Session Complete
Blueprint session completed successfully. Project initialized for {App Name}.
```

---

### Phase 8: Commit Everything

Use the commit skill to commit all changes:

1. Stage all new and modified files
2. Determine the next commit number
3. Create commit message:
```
[NNNN] feat: Initialize {App Name} project with Blueprint

- Add product vision and technical spec context files
- Create feature roadmap and design guidelines
- Generate initialization prompt
- Update app name to {App Name}
- Set up project structure for {brief description}

Co-Authored-By: Claude <noreply@anthropic.com>
```
4. Push to remote

---

### Phase 8: Display Next Steps

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    🎉 BLUEPRINT COMPLETE!

    {App Name} is ready for development.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📁 Created Files:
   • .claude/context/product-vision.md
   • .claude/context/technical-spec.md
   • .claude/context/feature-roadmap.md
   • .claude/context/design-guidelines.md
   • .claude/prompts/NNNN-initialize-{app-name}.md

✅ Committed: [NNNN] feat: Initialize {App Name} project with Blueprint

🚀 What's Next?
```

**Ask the user using AskUserQuestion:**

"Your project is initialized! What would you like to do next?"

Options:
- "🔥 Run /forge to build my complete MVP" (Recommended)
- "📝 Execute the initialization prompt first"
- "🔍 Review the context files"
- "✋ I'll continue on my own"

**If user chooses /forge:**
Inform them and transition:
```
Excellent choice! Let's forge your MVP...
```
Then invoke or instruct to run `/forge`.

**If user chooses other options, display appropriate guidance:**

```
📋 Available Next Steps:

   Option A: Build your complete MVP (Recommended)
   ─────────────────────────────────────────────
   /forge

   This will create a detailed MVP proposal, review it with you,
   and then build out your entire working prototype.

   Option B: Execute initialization prompt
   ─────────────────────────────────────────────
   /execute-prompt NNNN-initialize-{app-name}.md

   This sets up the basic project structure and package names.

   Option C: Manual development
   ─────────────────────────────────────────────
   Start building feature by feature:
   • "Implement the login screen"
   • "Create the home screen layout"
   • "Set up the navigation"

Happy building! 🛠️
```

---

## Error Handling

- If product-docs is empty, proceed with questions only
- If user skips questions, use sensible defaults or mark as "TBD"
- If commit fails, save files and inform user to commit manually
