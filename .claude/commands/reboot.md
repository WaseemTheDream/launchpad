# Reboot

> Systems back online — continue from where you left off.

Resume interrupted work by reading progress logs and continuing execution.

## Usage

```
/reboot
/reboot <session_id>
```

**Voice-friendly:**
- "Reboot" — Find and resume the most recent interrupted session
- "Reboot ship_0015_20260117" — Resume a specific session

## What Reboot Does

Reboot recovers interrupted AI sessions by:

1. **Finding** the progress log from an interrupted session
2. **Reading** what was completed and what remains
3. **Resuming** execution from where it stopped
4. **Completing** the original task

Think of it as a system reboot that restores your work state.

## Instructions

When the user invokes `/reboot`, follow this workflow:

### Step 1: Find Session to Resume

**If no session_id provided:**
1. List all progress logs in `.claude/prompts/logs/`
2. Find logs with `Status: IN_PROGRESS` or `Status: INTERRUPTED`
3. Sort by timestamp (most recent first)
4. If multiple found, show list and ask user to choose:
   ```
   Found 3 interrupted sessions:
   1. ship_0015_20260117_143052 - "Add dark mode toggle" (IN_PROGRESS)
   2. burn_20260117_160000 - 3 tasks, 1 remaining (INTERRUPTED)
   3. exec_refactor_20260117_120000 - "Refactor auth" (INTERRUPTED)

   Which session to resume? (Enter number or session ID)
   ```
5. If only one found, confirm: "Resume {session_id}? ({description})"
6. If none found: "No interrupted sessions found."

**If session_id provided:**
1. Look for progress log matching the session_id pattern
2. Verify the log exists
3. If not found, search for partial matches and suggest

### Step 2: Read Progress Log

1. Parse the progress log file
2. Extract:
   - **Session type**: ship, burn, or exec
   - **Original request/prompt**: What was being done
   - **Context snapshot**: Branch, status, directory
   - **Progress checkboxes**: What's done vs remaining
   - **Execution log**: Detailed history of actions
   - **Resume instructions**: Specific remaining tasks

3. Display summary to user:
   ```
   Resuming: ship_0015_20260117_143052
   Original request: Add dark mode toggle to settings

   Completed:
   ✓ Phase 1: Understand request
   ✓ Phase 2: Implement changes (SettingsViewModel.kt, SettingsScreen.kt)

   Remaining:
   ○ Phase 3: Document work
   ○ Phase 4: Commit changes

   Continue? (y/n)
   ```

### Step 3: Verify Current State

Before resuming, verify the codebase state:

1. Check current git branch matches the logged branch
2. Check for uncommitted changes that might conflict
3. Verify modified files still exist and haven't been manually changed
4. If conflicts detected:
   ```
   Warning: Current state differs from session snapshot
   - Branch: master (expected: feature-dark-mode)
   - Uncommitted changes detected

   Options:
   1. Switch to correct branch and stash changes
   2. Continue anyway (may cause issues)
   3. Abort
   ```

### Step 4: Resume Execution

Based on session type, hand off to the appropriate command:

**For ship sessions:**
1. Update progress log: `[{timestamp}] === RESUMING SESSION ===`
2. Continue from the incomplete phase
3. Follow ship.md instructions from that phase onward

**For burn sessions:**
1. Update progress log: `[{timestamp}] === RESUMING BURN ===`
2. Identify which task was interrupted
3. Continue from that task
4. Process remaining tasks

**For exec sessions:**
1. Update progress log: `[{timestamp}] === RESUMING EXECUTION ===`
2. Continue from incomplete step
3. Follow execute-prompt.md instructions

### Step 5: Update Progress Log

1. Add resumption entry to execution log:
   ```
   [{timestamp}] === SESSION RESUMED ===
   [{timestamp}] Previous session ended: {last_timestamp}
   [{timestamp}] Resuming from: {phase/task}
   ```

2. Continue logging as normal until completion

### Step 6: Complete and Finalize

1. When all tasks complete, update status to `COMPLETED`
2. Add final summary including:
   - Original start time
   - Resumption time
   - Completion time
   - Total elapsed time (accounting for gap)

## Finding Interrupted Sessions

Reboot searches for progress logs in `.claude/prompts/logs/` matching:

| Pattern | Session Type |
|---------|--------------|
| `ship_NNNN_*.md` | Ship sessions |
| `burn_*.md` | Burn sessions |
| `exec_*_*.md` or `*_YYYYMMDD_*.md` | Execute-prompt sessions |

Sessions are considered resumable if:
- Status is `IN_PROGRESS` or `INTERRUPTED`
- Status is NOT `COMPLETED` or `FAILED`

## Voice Invocation

| Say This | What Happens |
|----------|--------------|
| "Reboot" | Find and resume most recent interrupted session |
| "Reboot ship fifteen" | Resume ship session 0015 |
| "Reboot the burn" | Resume interrupted burn session |

## Example Scenarios

### Resuming an interrupted Ship

```
User: /reboot

Claude: Found 1 interrupted session:
- ship_0015_20260117_143052 - "Add dark mode toggle" (IN_PROGRESS)

Completed:
✓ Understand request
✓ Implement changes

Remaining:
○ Document work
○ Commit changes

Resume? (y/n)

User: y

Claude: [Resuming] Creating prompt documentation...
[Resuming] Committing changes...
Shipped! Dark mode toggle complete. Commit [0015] @ abc123
```

### Resuming an interrupted Burn

```
User: /reboot burn_20260117_160000

Claude: Resuming burn session with 1 remaining task:
- Task 3: Update app icon

Previous tasks completed:
✓ Task 1: Add dark mode → [0015]
✓ Task 2: Fix navigation → [0016]

Continue burn? (y/n)

User: y

Claude: [Resuming] Burning: Update app icon...
Burn complete! All 3 tasks finished.
Commits: [0015], [0016], [0017]
```

## Options

| Option | Description |
|--------|-------------|
| `/reboot` | Resume most recent interrupted session |
| `/reboot <session_id>` | Resume specific session |
| `/reboot --list` | List all interrupted sessions |
| `/reboot --clean` | Mark stale sessions as abandoned |

## Safety

- Reboot verifies codebase state before resuming
- Warns about branch mismatches or conflicts
- Won't overwrite manual changes without confirmation
- Preserves full history in progress log

---

*Reboot restores your work: Interrupted → Resumed → Completed*
