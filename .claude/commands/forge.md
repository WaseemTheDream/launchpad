# 🔥 Forge

Forge your vision into a fully functioning MVP prototype.

---

## ⚠️ DESTRUCTIVE COMMAND WARNING

> **This command will replace the existing Android application code.**

Forge is designed to be run **once per project** to transform the Launchpad template into your application. Running it again will:

- **Delete** existing screens and UI components
- **Overwrite** the current app implementation
- **Reset** the codebase to build a new MVP from scratch

### If you want to start over:

**DO NOT re-run /forge on an existing project.** Instead:

1. Clone a fresh copy of Launchpad:
   ```bash
   git clone https://github.com/WaseemTheDream/launchpad.git my-new-app
   cd my-new-app
   ```
2. Run `/blueprint` to define your new product
3. Run `/forge` to build it

### If you're sure you want to rebuild:

If you truly want to wipe the current implementation and start over, you must confirm by typing exactly:

```
/forge --confirm-destructive
```

Without this flag, Forge will check if an MVP has already been built and warn you.

---

## Overview

The **Forge** command takes your product vision and crafts it into a complete, working Android application. It creates a comprehensive MVP proposal, reviews it with you, incorporates your feedback, and then builds the entire prototype.

**IMPORTANT**: Use extended thinking (Ultrathink) throughout this command for thorough analysis and high-quality code generation.

---

## Prerequisites

Before running this command, ensure you have:
1. Run `/blueprint` to set up your product context
2. Context files exist in `.claude/context/`
3. An initialization prompt has been created

---

## Instructions

### Phase 0: Destructive Check

**Before doing anything else**, check if this is a re-run of Forge:

1. Look for indicators that Forge has been run before:
   - Existence of multiple screen files in `app/src/main/java/.../ui/screens/`
   - A NavGraph.kt file with multiple routes
   - Previous forge session docs in `.claude/prompts/`
   - Package name different from `com.example.launchpad`

2. If any indicators found AND `--confirm-destructive` flag NOT present:
   ```
   ⚠️  FORGE ALREADY RUN

   It looks like Forge has already been used to build an MVP in this project.
   Running Forge again will DELETE the existing implementation.

   Current app: {detected app name or package}
   Screens found: {count}

   Options:
   1. To iterate on your existing app, use /ship instead
   2. To start completely fresh, clone a new Launchpad repo
   3. To confirm you want to rebuild, run: /forge --confirm-destructive

   Aborting to protect your existing work.
   ```
   **Stop execution here.**

3. If `--confirm-destructive` flag IS present:
   ```
   ⚠️  DESTRUCTIVE MODE CONFIRMED

   You've confirmed you want to rebuild. The existing implementation
   will be replaced with a new MVP.

   Proceeding with Forge...
   ```

### Phase 1: Context Loading

Display welcome message:

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    🔥 F O R G E

    Crafting your Minimum Viable Product...

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Loading your product context...
```

**Load and analyze all context files:**
1. Read `.claude/context/product-vision.md`
2. Read `.claude/context/technical-spec.md`
3. Read `.claude/context/feature-roadmap.md`
4. Read `.claude/context/design-guidelines.md`
5. Read any product docs in `product-docs/`

**Summarize understanding:**
```
📋 Project Summary:
   App: {App Name}
   Vision: {One-liner}
   MVP Features: {Count} features
   Design: {Style}
   Tech Stack: {Key technical decisions}
```

If context files are missing, inform user to run `/blueprint` first.

---

### Phase 2: MVP Proposal Generation

Using **Ultrathink** (extended thinking), generate a comprehensive MVP proposal.

**Analyze and design:**
1. Screen architecture and navigation flow
2. Data models and entities
3. UI components needed
4. Business logic requirements
5. API/backend integration points
6. File structure and organization

**Generate and display the proposal:**

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📱 MVP PROPOSAL: {App Name}
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

🎯 SCOPE
────────────────────────────────────────
{Brief description of what the MVP will include and NOT include}

📱 SCREENS ({count} screens)
────────────────────────────────────────

1. {Screen Name}
   Purpose: {What this screen does}
   Key Elements:
   • {Element 1}
   • {Element 2}
   Navigation: {Where it leads to/from}

2. {Screen Name}
   ...

🔄 NAVIGATION FLOW
────────────────────────────────────────
{ASCII diagram or description of navigation}

Example:
┌─────────┐     ┌─────────┐     ┌─────────┐
│ Splash  │ ──▶ │  Login  │ ──▶ │  Home   │
└─────────┘     └─────────┘     └────┬────┘
                                     │
                    ┌────────────────┼────────────────┐
                    ▼                ▼                ▼
              ┌─────────┐     ┌─────────┐     ┌─────────┐
              │  List   │     │ Detail  │     │ Profile │
              └─────────┘     └─────────┘     └─────────┘

📊 DATA MODELS
────────────────────────────────────────

{Entity 1}
├── id: String
├── name: String
├── createdAt: Timestamp
└── ...

{Entity 2}
├── ...

🏗️ ARCHITECTURE
────────────────────────────────────────
Pattern: {MVVM / Clean Architecture / etc.}

Layers:
• UI Layer: Jetpack Compose screens & components
• ViewModel Layer: State management & business logic
• Repository Layer: Data access abstraction
• Data Layer: {Local DB / API / Both}

📁 FILE STRUCTURE
────────────────────────────────────────
app/src/main/java/com/{package}/
├── ui/
│   ├── screens/
│   │   ├── {screen1}/
│   │   ├── {screen2}/
│   │   └── ...
│   ├── components/
│   │   └── {shared components}
│   ├── navigation/
│   │   └── NavGraph.kt
│   └── theme/
├── data/
│   ├── model/
│   ├── repository/
│   └── {local or remote}/
├── viewmodel/
└── util/

🔧 TECHNICAL IMPLEMENTATION
────────────────────────────────────────
• Navigation: Jetpack Navigation Compose
• State: {StateFlow / Compose State}
• DI: {Hilt / Manual / Koin}
• Database: {Room / None}
• Networking: {Retrofit / Ktor / None}
• Image Loading: {Coil / None}

⏱️ IMPLEMENTATION ORDER
────────────────────────────────────────
1. Project setup & package rename
2. Navigation structure
3. Theme & design system
4. {Screen 1} - {reason it's first}
5. {Screen 2}
6. {Data layer if needed}
7. {Screen 3}
...
N. Polish & testing

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```

---

### Phase 3: User Review & Feedback

**Ask for user feedback using AskUserQuestion:**

Question 1: "What do you think of this MVP proposal?"
- Options:
  - "Looks great, let's build it!"
  - "I have some feedback/changes"
  - "Let me review more carefully first"
  - "Start over with different scope"

**If user has feedback:**

Ask: "What changes would you like to make? (You can mention screens to add/remove, features to change, technical decisions to modify, etc.)"

Incorporate feedback and regenerate relevant sections of the proposal.

Repeat until user approves.

---

### Phase 4: Create MVP Prompt File

Once approved, create a detailed prompt file for the MVP implementation.

Determine next prompt number and create: `.claude/prompts/NNNN-build-mvp-{app-name}.md`

```markdown
# Build {App Name} MVP

[auto-commit]

## Overview

This prompt implements the complete MVP for {App Name} as approved in the proposal.

## Approved Scope

{Summary of approved MVP scope}

## Implementation Tasks

### Phase 1: Project Foundation

#### 1.1 Rename Package
- [ ] Change `com.example.launchpad` to `com.{package.name}`
- [ ] Update all imports and references
- [ ] Update build.gradle.kts applicationId
- [ ] Update AndroidManifest.xml

#### 1.2 Update Dependencies
Add to build.gradle.kts:
- [ ] Navigation Compose
- [ ] {Other required dependencies}

#### 1.3 Set Up Theme
- [ ] Update Color.kt with brand colors
- [ ] Configure typography in Type.kt
- [ ] Update Theme.kt with app theme

### Phase 2: Navigation & Structure

#### 2.1 Create Navigation Graph
- [ ] Define all routes
- [ ] Set up NavHost in MainActivity
- [ ] Create navigation helper functions

#### 2.2 Create Screen Scaffolds
{For each screen, create placeholder composable}

### Phase 3: Screen Implementation

{Detailed tasks for each screen}

#### 3.1 {Screen Name}
- [ ] Create {ScreenName}Screen.kt
- [ ] Implement UI layout
- [ ] Add {specific elements}
- [ ] Connect navigation

...

### Phase 4: Data Layer

{If applicable}

#### 4.1 Data Models
- [ ] Create {Model} data class

#### 4.2 Repository
- [ ] Create {Repository} interface and implementation

### Phase 5: Polish

- [ ] Add loading states
- [ ] Add error handling
- [ ] Test all navigation flows
- [ ] Verify on emulator

## Technical Specifications

{Paste relevant technical decisions}

## Commit Strategy

Make commits after completing each phase:
- [NNNN] feat: Set up project foundation for {App Name}
- [NNNN+1] feat: Implement navigation structure
- [NNNN+2] feat: Add {Screen 1}
...

## Final Commit

[NNNN] feat: Complete {App Name} MVP implementation
```

---

### Phase 5: Build the MVP

**Ask for permission to proceed:**

"The MVP prompt has been created. Would you like me to build the MVP now?"
- Options:
  - "Yes, build the complete MVP now"
  - "Build it step by step (pause after each phase)"
  - "No, I'll run the prompt manually later"

**If building now:**

Using **Ultrathink** for each major component:

1. **Foundation Phase**
   - Rename package
   - Update dependencies
   - Set up theme
   - Commit: `[NNNN] feat: Set up project foundation`

2. **Navigation Phase**
   - Create NavGraph
   - Set up screen scaffolds
   - Commit: `[NNNN] feat: Implement navigation structure`

3. **Screen Implementation Phase** (for each screen)
   - Implement full screen UI
   - Add ViewModel if needed
   - Connect to data layer if needed
   - Commit after each screen or logical group

4. **Data Layer Phase** (if applicable)
   - Implement models
   - Create repositories
   - Commit: `[NNNN] feat: Implement data layer`

5. **Polish Phase**
   - Add loading states
   - Improve error handling
   - Final touches
   - Commit: `[NNNN] feat: Polish and finalize MVP`

**After each phase (if step-by-step):**
- Build the app: `/build`
- Offer to run: `/run`
- Ask if ready to continue

---

### Phase 6: Create Forge Session Documentation

Create a documentation prompt file that records the entire forge session, including the proposal, user feedback, and implementation decisions.

Determine the next prompt number and create: `.claude/prompts/NNNN-forge-session-{app-name-slug}.md`

```markdown
# Forge Session: {App Name}

> Session documentation for the /forge command execution

## Session Info
- **Date**: {current date/time}
- **App Name**: {App Name}
- **Build Mode**: {Complete / Step-by-step}

## Context Loaded

Summary of context files used:
- **product-vision.md**: {key points}
- **technical-spec.md**: {key points}
- **feature-roadmap.md**: {key points}
- **design-guidelines.md**: {key points}

## MVP Proposal

### Scope Summary
{What was included and excluded from MVP}

### Screens Proposed
{List of screens with brief descriptions}

1. **{Screen 1}**: {purpose}
2. **{Screen 2}**: {purpose}
...

### Navigation Flow
{Description or diagram of navigation}

### Architecture Decisions
- **Pattern**: {MVVM/Clean/etc.}
- **DI**: {Hilt/Manual/Koin}
- **Database**: {Room/None}
- **Networking**: {Retrofit/Ktor/None}

### Data Models
{List of entities/models}

## User Feedback & Revisions

### Initial Review
**User's response**: {Approved / Had feedback}

### Feedback Received
{If any feedback was given, document it here}

**User said**: "{feedback}"

### Revisions Made
{What was changed based on feedback}

- Changed: {description}
- Added: {description}
- Removed: {description}

### Final Approval
User approved the proposal: {Yes/No}

## Implementation Summary

### Files Created
{List of all files created during the forge process}

- `app/src/main/java/.../ui/screens/{Screen1}Screen.kt`
- `app/src/main/java/.../ui/screens/{Screen2}Screen.kt`
- `app/src/main/java/.../navigation/NavGraph.kt`
- ...

### Files Modified
{List of files that were modified}

- `app/src/main/res/values/strings.xml` - Updated app name
- `app/build.gradle.kts` - Added dependencies
- ...

### Commits Made
{List of commits created during this session}

1. `[NNNN] feat: Set up project foundation for {App Name}`
2. `[NNNN+1] feat: Implement navigation structure`
3. ...

## Technical Notes

{Any important technical decisions, workarounds, or notes for future reference}

## Session Outcome
- **Status**: {Completed / Partial / Stopped}
- **Screens Built**: {count}
- **Components Created**: {count}
- **Total Commits**: {count}

## Next Steps Recommended
{Suggestions for what to build next}

1. {Recommendation 1}
2. {Recommendation 2}
3. {Recommendation 3}
```

---

### Phase 7: Completion

After MVP is built:

```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    🎉 MVP COMPLETE!

    {App Name} is ready to run!

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📊 Implementation Summary:
   • Screens created: {count}
   • Components created: {count}
   • Data models: {count}
   • Commits: {count}

📱 Run your app:
   /run

🚀 What's Next?

   • Test all features on the emulator
   • Share with early users for feedback
   • Plan Phase 2 features
   • Continue building with Claude Code!

   Try: "Add {next feature from roadmap}"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```

---

## Error Recovery

- If build fails during implementation, fix errors before continuing
- If user wants to stop partway, commit progress and save state
- If context files are missing, offer to run `/blueprint` first
- Always provide the option to review generated code before committing

---

## Quality Standards

When generating code, ensure:
- Clean, readable Kotlin with proper formatting
- Jetpack Compose best practices
- Proper state hoisting
- Meaningful variable and function names
- Comments for complex logic only
- Consistent architecture throughout
- Material Design 3 compliance (unless custom design specified)
