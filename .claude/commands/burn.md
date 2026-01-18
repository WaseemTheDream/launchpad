# Burn

> Initiate burn sequence — power through the entire queue.

Execute all queued tasks sequentially, like a rocket burn clearing the launchpad.

## Usage

```
/burn
/burn --dry-run
```

**Voice-friendly:**
- "Burn" — Execute all queued tasks
- "Burn dry run" — Preview without executing

## What Burn Does

Burn processes every task in `.claude/queue.md`, executing them one by one using the Ship workflow (implement → document → commit). Think of it as a sustained rocket burn that propels you through your backlog.

**Key feature**: Creates a progress log that enables resumption if Claude is interrupted mid-burn.

## Instructions

When the user invokes `/burn`, follow this workflow:

### Phase 0: Initialize Burn Progress Log

**CRITICAL**: Create a progress log IMMEDIATELY before processing any tasks.

1. Generate timestamp: `YYYYMMDD_HHMMSS` format
2. Create progress log at `.claude/prompts/logs/burn_{timestamp}.md`
3. Write initial context:

```markdown
# Burn Progress Log

## Session Info
- **Session ID**: burn_{timestamp}
- **Started**: {timestamp}
- **Status**: IN_PROGRESS
- **Total Tasks**: {count}

## Context Snapshot
- **Git Branch**: {branch}
- **Git Status**: {clean/dirty}
- **Working Directory**: {path}

## Task Queue
{List all tasks from queue.md}

## Progress
- [ ] Task 1: {description}
- [ ] Task 2: {description}
- [ ] Task 3: {description}

## Execution Log
[{timestamp}] Burn sequence initiated with {count} tasks
```

### Step 1: Read the Queue

1. Open `.claude/queue.md`
2. Parse all pending tasks (lines with `- [ ]`)
3. If queue is empty, inform user and stop
4. Log: `[{timestamp}] Found {count} pending tasks`
5. Display the tasks to be processed

### Step 2: Confirm Execution

Show the user what will be executed:

```
Burn sequence ready — 3 tasks queued:
1. Add dark mode toggle
2. Fix navigation crash
3. Update app icon

Initiate burn? (This will execute each task and commit)
```

If `--dry-run` flag is present, show this preview and stop.

### Step 3: Process Each Task

For each pending task:

1. Log: `[{timestamp}] === BURNING: {task description} ===`
2. Update progress checkbox to in-progress: `- [~] Task N: {description}`
3. Execute the task using Ship workflow:
   - Create task-specific progress log (ship_NNNN_timestamp.md)
   - Implement changes
   - Create prompt documentation
   - Commit with numbered message
4. Mark task as completed in progress log: `- [x] Task N: {description} → [NNNN]`
5. Mark task as completed in queue file:
   - Change `- [ ]` to `- [x]`
   - Add completion timestamp
   - Move to "Completed" section
6. Log: `[{timestamp}] Completed: {task} → Commit [NNNN] @ {hash}`
7. **Save progress log after each task** (enables mid-burn resumption)

### Step 4: Handle Failures

If a task fails:

1. Log: `[{timestamp}] FAILED: {task} - {error}`
2. Update progress: `- [!] Task N: {description} - FAILED`
3. Ask user: "Burn interrupted: {task}. Skip and continue, or abort?"
4. If skip: mark as `- [!]` (failed) in queue and continue
5. If abort:
   - Update status to `INTERRUPTED`
   - Log remaining tasks
   - Preserve remaining tasks in queue

### Step 5: Finalize Progress Log

After all tasks processed:

1. Update status: `**Status**: COMPLETED`
2. Add summary:

```markdown
## Summary
- **Completed**: {timestamp}
- **Status**: COMPLETED
- **Tasks Processed**: {count}
- **Tasks Succeeded**: {count}
- **Tasks Failed**: {count}
- **Commits**: [0015], [0016], [0017]
- **Duration**: {time elapsed}
```

### Step 6: Report Summary

```
Burn complete!
- Processed: 3 tasks
- Succeeded: 3
- Failed: 0
- Commits: [0015], [0016], [0017]
```

## Progress Log Format

The burn progress log at `.claude/prompts/logs/burn_{timestamp}.md`:

```markdown
# Burn Progress Log

## Session Info
- **Session ID**: burn_20260117_160000
- **Started**: 2026-01-17 16:00:00
- **Status**: IN_PROGRESS | COMPLETED | INTERRUPTED
- **Total Tasks**: 3

## Context Snapshot
- **Git Branch**: master
- **Git Status**: clean
- **Working Directory**: C:\Users\Waz\AndroidStudioProjects\launchpad

## Task Queue
1. Add dark mode toggle
2. Fix navigation crash
3. Update app icon

## Progress
- [x] Task 1: Add dark mode toggle → [0015]
- [x] Task 2: Fix navigation crash → [0016]
- [ ] Task 3: Update app icon

## Execution Log
[16:00:00] Burn sequence initiated with 3 tasks
[16:00:01] === BURNING: Add dark mode toggle ===
[16:02:30] Completed: Add dark mode toggle → Commit [0015] @ abc123
[16:02:31] === BURNING: Fix navigation crash ===
[16:04:15] Completed: Fix navigation crash → Commit [0016] @ def456
[16:04:16] === BURNING: Update app icon ===
[16:05:00] INTERRUPTED - Session ended unexpectedly

## Resume Instructions
To continue this burn, run: /reboot burn_20260117_160000
Remaining tasks:
- Task 3: Update app icon
```

## Resumption Support

If Claude is interrupted mid-burn, the progress log contains:
- Which tasks were completed (with commit hashes)
- Which task was in progress
- Which tasks remain

Use `/reboot {session_id}` to continue the burn from where it stopped.

## Options

| Option | Description |
|--------|-------------|
| `/burn` | Execute all pending tasks |
| `/burn --dry-run` | Preview tasks without executing |
| `/burn --first` | Execute only the first task |
| `/burn --skip-failed` | Auto-skip failed tasks |

## Queue File Updates

Before burn:
```markdown
## Pending
- [ ] Add dark mode toggle (queued: 2026-01-17 14:30)
- [ ] Fix navigation crash (queued: 2026-01-17 14:35)
```

After burn:
```markdown
## Pending
(empty)

## Completed
- [x] Add dark mode toggle (completed: 2026-01-17 16:00) → [0015]
- [x] Fix navigation crash (completed: 2026-01-17 16:05) → [0016]
```

## Voice Invocation

| Say This | What Happens |
|----------|--------------|
| "Burn" | Execute all queued tasks |
| "Burn dry run" | Preview without executing |
| "Burn first" | Execute only the oldest task |

## Safety

- Burn asks for confirmation before executing
- Each task is committed separately (easy to revert individually)
- Failed tasks don't stop the entire burn (by default)
- Dry-run mode lets you preview without risk
- **Progress logs enable resumption if interrupted**

---

*Burn powers through the queue: Queue captures → Burn executes*
*If interrupted, use /reboot to continue*
