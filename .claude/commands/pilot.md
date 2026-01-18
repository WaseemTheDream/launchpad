# Pilot

> "You have the controls." — Interactive, hands-on development with a co-pilot.

Execute changes interactively with user verification at every step. Unlike `/ship` which runs autonomously, `/pilot` keeps you in the cockpit.

## Usage

```
/pilot <what you want done>
```

**Voice-friendly examples:**
- "Pilot add a settings screen"
- "Pilot redesign the home page"
- "Pilot fix the navigation bug"
- "Pilot implement user authentication"

## What Pilot Does

Pilot is your interactive development companion. When invoked, it:

1. **Discusses** the request with you to clarify requirements
2. **Creates a progress log** (for tracking and resumption)
3. **Plans** the implementation and gets your approval
4. **Implements** changes incrementally, checking in with you
5. **Builds & Installs** the app so you can test
6. **Waits** for your verification and feedback
7. **Iterates** based on your input
8. **Leaves changes pending** for `/land` to commit

Think of it as: **Ship** on autopilot vs **Pilot** with you at the controls.

## Pilot vs Ship

| Aspect | /ship | /pilot |
|--------|-------|--------|
| User presence | Optional | Required |
| Questions | Minimal (1 if unclear) | Encouraged throughout |
| Build/Install | Optional | Always |
| User testing | Not included | Required |
| Commits | Automatic | Deferred to /land |
| Best for | Quick, clear tasks | Complex, exploratory work |

## Instructions

When the user invokes `/pilot <request>`, follow this interactive workflow:

### Phase 0: Initialize Progress Log

**CRITICAL**: Create a progress log IMMEDIATELY.

1. Generate timestamp: `YYYYMMDD_HHMMSS` format
2. Determine the next prompt number (for session ID)
3. Create progress log at `.claude/prompts/logs/pilot_{NNNN}_{timestamp}.md`
4. Write initial context:

```markdown
# Pilot Progress Log

## Session Info
- **Session ID**: pilot_{NNNN}_{timestamp}
- **Started**: {timestamp}
- **Status**: IN_PROGRESS
- **Request**: {original request}

## Context Snapshot
- **Git Branch**: {branch}
- **Git Status**: {clean/dirty}
- **Working Directory**: {path}

## Conversation Log
[{timestamp}] User request: {request}

## Implementation Plan
{To be filled after discussion}

## Progress
- [ ] Phase 1: Discuss and clarify
- [ ] Phase 2: Plan and get approval
- [ ] Phase 3: Implement changes
- [ ] Phase 4: Build and install
- [ ] Phase 5: User verification
- [ ] Phase 6: Ready for landing

## Changes Made
{List of files modified/created}

## Execution Log
[{timestamp}] Starting pilot session: {request}
```

### Phase 1: Discuss and Clarify

**This is the key difference from /ship — engage in conversation!**

1. Log: `[{timestamp}] Beginning discussion phase`
2. Acknowledge the request and share your initial understanding
3. Ask clarifying questions:
   - What specific behavior do you want?
   - Any design preferences?
   - Should this integrate with existing features?
   - What's the priority: speed, quality, or flexibility?
4. **Wait for user responses** — don't proceed until clear
5. Log each Q&A exchange in the Conversation Log
6. Summarize your understanding and confirm with user
7. Check checkbox: `- [x] Phase 1: Discuss and clarify`

**Example conversation:**
```
Claude: "You want to add a settings screen. A few questions:
1. What settings should be included? (theme, notifications, account?)
2. Should it be accessible from the bottom nav or a menu?
3. Any specific design style in mind?"

User: "Theme toggle and about section. Bottom nav. Keep it simple."

Claude: "Got it! I'll create a Settings screen with:
- Dark/light theme toggle
- About section with app info
- Add it to the bottom navigation bar
Sound good?"
```

### Phase 2: Plan and Get Approval

1. Log: `[{timestamp}] Creating implementation plan`
2. Present a clear implementation plan:
   - Files to create/modify
   - Key components to build
   - Integration points
   - Potential challenges
3. **Ask: "Does this plan look good? Any changes?"**
4. Wait for explicit approval before proceeding
5. Log the approved plan
6. Check checkbox: `- [x] Phase 2: Plan and get approval`

### Phase 3: Implement Changes

1. Log: `[{timestamp}] Beginning implementation`
2. Work through the plan incrementally
3. **After each significant change, inform the user:**
   - "Created SettingsScreen.kt with theme toggle"
   - "Updated navigation to include Settings tab"
4. Log each file modification:
   ```
   [{timestamp}] Modified: {filepath}
   - Change: {what changed}
   ```
5. If you encounter decisions or issues, **ask the user**
6. Update the "Changes Made" section in the log
7. Check checkbox: `- [x] Phase 3: Implement changes`

### Phase 4: Build and Install

1. Log: `[{timestamp}] Building and installing app`
2. Build the app:
   ```bash
   export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr"
   ./gradlew.bat assembleDebug
   ```
3. If build fails:
   - Log the error
   - Inform the user
   - Fix the issue
   - Retry
4. Install to device/emulator:
   ```bash
   ./gradlew.bat installDebug
   ```
5. Launch the app:
   ```bash
   adb shell am start -n com.example.launchpad/.MainActivity
   ```
6. Log: `[{timestamp}] App installed and launched`
7. Check checkbox: `- [x] Phase 4: Build and install`

### Phase 5: User Verification

**CRITICAL: This is where the user tests the changes!**

1. Inform the user: "The app is now running with your changes. Please test:"
   - List specific things to verify
   - Mention where to find new features
2. **Ask: "How does it look? Any issues or changes needed?"**
3. **WAIT for user feedback** — do not proceed automatically
4. Log user feedback in Conversation Log

**If user requests changes:**
- Log: `[{timestamp}] User requested changes: {feedback}`
- Return to Phase 3 to implement changes
- Rebuild and reinstall
- Return to Phase 5 for re-verification

**If user approves:**
- Log: `[{timestamp}] User approved changes`
- Check checkbox: `- [x] Phase 5: User verification`

### Phase 6: Ready for Landing

1. Log: `[{timestamp}] Changes verified, ready for landing`
2. Update progress log status to: `**Status**: PENDING_LAND`
3. Summarize what was done:
   ```markdown
   ## Landing Summary
   - **Files Modified**: {count}
   - **Files Created**: {count}
   - **Ready for commit**: Yes
   - **Suggested commit message**: [NNNN] type: description
   ```
4. Check checkbox: `- [x] Phase 6: Ready for landing`
5. Inform user: "Changes are ready! Run `/land` to commit them."

## Progress Log Format

```markdown
# Pilot Progress Log

## Session Info
- **Session ID**: pilot_0018_20260117_150000
- **Started**: 2026-01-17 15:00:00
- **Status**: IN_PROGRESS | PENDING_LAND | LANDED | ABORTED
- **Request**: Add settings screen with theme toggle

## Context Snapshot
- **Git Branch**: master
- **Git Status**: clean
- **Working Directory**: C:\Users\Waz\AndroidStudioProjects\launchpad

## Conversation Log
[15:00:00] User request: Add settings screen with theme toggle
[15:00:30] Claude asked: What settings to include?
[15:01:00] User answered: Theme toggle and about section
[15:01:30] Claude confirmed: Settings with theme + about, in bottom nav
[15:02:00] User approved plan
[15:05:00] User feedback: Looks good but make the toggle bigger
[15:06:00] Claude: Updated toggle size
[15:07:00] User approved: Ready to land

## Implementation Plan
1. Create SettingsScreen.kt with theme toggle and about section
2. Add Settings to bottom navigation
3. Wire up theme state to MainActivity

## Progress
- [x] Phase 1: Discuss and clarify
- [x] Phase 2: Plan and get approval
- [x] Phase 3: Implement changes
- [x] Phase 4: Build and install
- [x] Phase 5: User verification
- [x] Phase 6: Ready for landing

## Changes Made
- Created: app/src/main/java/.../SettingsScreen.kt
- Modified: app/src/main/java/.../BottomNavBar.kt
- Modified: app/src/main/java/.../NavGraph.kt

## Execution Log
[15:00:00] Starting pilot session: Add settings screen
[15:02:00] Plan approved, beginning implementation
[15:03:00] Created: SettingsScreen.kt
[15:03:30] Modified: BottomNavBar.kt - Added Settings tab
[15:04:00] Modified: NavGraph.kt - Added settings route
[15:04:30] Building app...
[15:05:00] Installed and launched
[15:06:00] User requested larger toggle - implementing
[15:06:30] Rebuilt and reinstalled
[15:07:00] User approved changes
[15:07:30] Status updated to PENDING_LAND

## Landing Summary
- **Files Modified**: 2
- **Files Created**: 1
- **Ready for commit**: Yes
- **Suggested commit message**: [0019] feat: Add settings screen with theme toggle
```

## Workflow with /land

1. `/pilot add settings screen` — Interactive development
2. User tests and approves changes
3. `/land` — Commits the changes (see /land command)

## Aborting a Pilot Session

If you need to abandon changes:
- Tell Claude: "Abort this pilot session"
- Claude will:
  - Log: `[{timestamp}] Session aborted by user`
  - Update status to: `**Status**: ABORTED`
  - Optionally revert changes: `git checkout .`

## Resuming a Pilot Session

If interrupted, use `/reboot pilot_{session_id}` to continue.

## Voice Commands During Pilot

| Say This | What Happens |
|----------|--------------|
| "Looks good" | Proceed to next phase |
| "Make it bigger" | Implement the change |
| "Show me the code" | Display relevant code |
| "Start over" | Reset current phase |
| "Abort" | Cancel the session |
| "Land it" | Shortcut to run /land |

## Best Practices

1. **Be present** — Pilot expects your input throughout
2. **Test thoroughly** — Use the installed app before approving
3. **Give specific feedback** — "Make the button blue" > "I don't like it"
4. **Use /ship for quick tasks** — Pilot is for exploratory work
5. **Land promptly** — Don't leave changes pending too long

## Safety

- Changes are not committed until you run `/land`
- You can abort at any time to discard changes
- Progress logs track everything for accountability
- Build failures are caught and reported before proceeding

---

*Pilot is part of the Launchpad workflow: Blueprint → Forge → Pilot → Land*
*For autonomous execution, use /ship instead*
