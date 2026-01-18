# Ship

> "Ship it!" — The universal developer call to action.

Execute a change request, document it, and commit — all in one voice-friendly command.

## Usage

```
/ship <what you want done>
```

**Voice-friendly examples:**
- "Ship add a dark mode toggle"
- "Ship fix the login button crash"
- "Ship update the app icon"
- "Ship refactor the database layer"

## What Ship Does

Ship is your end-to-end change execution skill. When invoked, it:

1. **Understands** your request
2. **Creates a progress log** (for resumption if interrupted)
3. **Implements** the changes
4. **Documents** the work (creates a prompt file)
5. **Commits** with auto-numbered commit message

Think of it as: **Blueprint** plans → **Forge** builds → **Ship** delivers.

## Instructions

When the user invokes `/ship <request>`, follow this workflow:

### Phase 0: Initialize Progress Log

**CRITICAL**: Create a progress log IMMEDIATELY before doing any work. This enables resumption if Claude is disconnected.

1. Generate timestamp: `YYYYMMDD_HHMMSS` format
2. Determine the next prompt number (for the session ID)
3. Create progress log at `.claude/prompts/logs/ship_{NNNN}_{timestamp}.md`
4. Write initial context:

```markdown
# Ship Progress Log

## Session Info
- **Session ID**: ship_{NNNN}_{timestamp}
- **Started**: {timestamp}
- **Status**: IN_PROGRESS
- **Request**: {original request}

## Context Snapshot
- **Git Branch**: {branch}
- **Git Status**: {clean/dirty}
- **Working Directory**: {path}

## Plan
{What Claude plans to do - written before starting}

## Progress
- [ ] Phase 1: Understand request
- [ ] Phase 2: Implement changes
- [ ] Phase 3: Document work
- [ ] Phase 4: Commit changes

## Execution Log
[{timestamp}] Starting ship: {request}
```

5. **Update this log after EVERY significant action** so it can be resumed

### Phase 1: Understand the Request

1. Parse the user's request from the arguments
2. Log: `[{timestamp}] Parsed request: {interpretation}`
3. If the request is unclear or ambiguous, ask ONE clarifying question
4. Update progress log with understanding
5. Check checkbox: `- [x] Phase 1: Understand request`

### Phase 2: Implement the Changes

1. Log: `[{timestamp}] Beginning implementation`
2. Analyze the codebase to understand what needs to change
3. **Log each file you plan to modify BEFORE modifying it**
4. Make the necessary modifications:
   - Edit existing files
   - Create new files if needed
   - Update related files (imports, exports, etc.)
5. **After each file change, log it**:
   ```
   [{timestamp}] Modified: {filepath}
   - Change: {what changed}
   ```
6. Keep changes focused — don't over-engineer or add unrequested features
7. If you encounter blockers, log them and inform the user
8. Check checkbox: `- [x] Phase 2: Implement changes`

### Phase 3: Document the Work

Create a prompt file to document what was done:

1. Determine the next prompt number:
   - List files in `.claude/prompts/` matching `NNNN-*.md`
   - Find the highest number and add 1
   - Format as 4 digits (e.g., `0012`)

2. Create the prompt file at `.claude/prompts/{NNNN}-{slug}.md`:
   - Generate a slug from the request (lowercase, hyphens, max 50 chars)
   - Use `[no-commit]` directive (since we're committing separately)

3. Document:
   - Original request
   - What was changed
   - Files modified/created
   - Any decisions made

4. Log: `[{timestamp}] Created prompt file: {path}`
5. Check checkbox: `- [x] Phase 3: Document work`

### Phase 4: Commit the Changes

1. Log: `[{timestamp}] Preparing commit`
2. Determine the next commit number:
   - Run `git log --oneline -50`
   - Find highest `[NNNN]` pattern
   - Add 1 for next number

3. Determine commit type:
   - `feat:` — New feature
   - `fix:` — Bug fix
   - `docs:` — Documentation
   - `style:` — Formatting/styling
   - `refactor:` — Code restructuring
   - `chore:` — Maintenance
   - `perf:` — Performance

4. Stage and commit:
   ```
   git add .
   git commit -m "[NNNN] type: Brief description

   - Detail 1
   - Detail 2

   Co-Authored-By: Claude <noreply@anthropic.com>"
   ```

5. Log: `[{timestamp}] Committed: [NNNN] @ {hash}`
6. Check checkbox: `- [x] Phase 4: Commit changes`

### Phase 5: Finalize Progress Log

1. Update status: `**Status**: COMPLETED`
2. Add completion timestamp
3. Write summary section:

```markdown
## Summary
- **Completed**: {timestamp}
- **Status**: COMPLETED
- **Commit**: [NNNN] @ {hash}
- **Files Modified**: {count}
- **Files Created**: {count}
- **Prompt File**: {path}
```

### Phase 6: Confirm Completion

Provide a brief summary to the user:
- What was changed
- Prompt file created
- Commit number and hash
- Any follow-up suggestions

## Progress Log Format

The progress log at `.claude/prompts/logs/ship_{NNNN}_{timestamp}.md` contains:

```markdown
# Ship Progress Log

## Session Info
- **Session ID**: ship_0015_20260117_143052
- **Started**: 2026-01-17 14:30:52
- **Status**: IN_PROGRESS | COMPLETED | INTERRUPTED
- **Request**: Add dark mode toggle to settings

## Context Snapshot
- **Git Branch**: master
- **Git Status**: clean
- **Working Directory**: C:\Users\Waz\AndroidStudioProjects\launchpad

## Plan
1. Add dark mode preference to SettingsViewModel
2. Create DarkModeToggle composable
3. Wire up to system theme
4. Update MainActivity to respect preference

## Progress
- [x] Phase 1: Understand request
- [x] Phase 2: Implement changes
- [ ] Phase 3: Document work
- [ ] Phase 4: Commit changes

## Execution Log
[14:30:52] Starting ship: Add dark mode toggle to settings
[14:30:53] Parsed request: Add toggle in settings to switch between light/dark mode
[14:31:00] Reading: app/src/main/java/com/example/launchpad/ui/settings/SettingsScreen.kt
[14:31:15] Modified: SettingsViewModel.kt
- Change: Added darkModeEnabled StateFlow
[14:31:30] Modified: SettingsScreen.kt
- Change: Added DarkModeToggle composable
[14:32:00] INTERRUPTED - Session ended unexpectedly

## Resume Instructions
To continue this work, run: /reboot ship_0015_20260117_143052
Remaining tasks:
- Create prompt documentation file
- Commit changes with message about dark mode
```

## Resumption Support

If Claude is interrupted, the progress log contains everything needed to resume:
- Original request
- What was planned
- What was completed
- What remains to be done

Use `/reboot {session_id}` to continue interrupted work.

## Example Flow

**User says:** `/ship add a floating action button to the home screen`

**Ship executes:**
1. Creates progress log: `ship_0012_20260117_143000.md`
2. Logs: "Starting ship: add floating action button..."
3. Reads MainActivity.kt and related UI files
4. Logs each file modification
5. Adds FloatingActionButton to the home screen composable
6. Creates `.claude/prompts/0012-add-floating-action-button.md`
7. Commits as `[0012] feat: Add floating action button to home screen`
8. Updates progress log status to COMPLETED
9. Reports: "Shipped! Added FAB to HomeScreen. Commit [0012] @ abc123"

## Voice Invocation Tips

Ship is designed for natural voice commands:

| Say This | Ship Understands |
|----------|------------------|
| "Ship add dark mode" | Add dark mode feature |
| "Ship fix the crash" | Investigate and fix crash |
| "Ship update the colors" | Modify color scheme |
| "Ship refactor auth" | Restructure authentication code |
| "Ship add tests for login" | Create login tests |

## Options

- `/ship --no-commit <request>` — Make changes but don't commit
- `/ship --no-doc <request>` — Skip creating prompt documentation
- `/ship --dry-run <request>` — Show what would be done without doing it

## Safety

- Ship will not make changes to files outside the project
- Ship will warn before modifying critical files (AndroidManifest.xml, build.gradle.kts)
- Ship creates documentation, so changes are always traceable
- Ship commits atomically, so changes can be easily reverted
- **Progress logs enable resumption if interrupted**

## Quick Reference

```
/ship <request>           # Full flow: implement → document → commit
/ship --no-commit <req>   # Implement and document only
/ship --dry-run <req>     # Preview what would happen
```

---

*Ship is part of the Launchpad workflow: Blueprint → Forge → Ship*
*If interrupted, use /reboot to continue*
