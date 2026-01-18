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

## Instructions

When the user invokes `/burn`, follow this workflow:

### Step 1: Read the Queue

1. Open `.claude/queue.md`
2. Parse all pending tasks (lines with `- [ ]`)
3. If queue is empty, inform user and stop
4. Display the tasks to be processed

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

1. Announce: "Burning: {task description}"
2. Execute the task using Ship workflow:
   - Implement changes
   - Create prompt documentation
   - Commit with numbered message
3. Mark task as completed in queue file:
   - Change `- [ ]` to `- [x]`
   - Add completion timestamp
   - Move to "Completed" section
4. Report: "Completed: {task} → Commit [NNNN]"

### Step 4: Handle Failures

If a task fails:

1. Log the error
2. Ask user: "Burn interrupted: {task}. Skip and continue, or abort?"
3. If skip: mark as `- [!]` (failed) and continue
4. If abort: preserve remaining tasks in queue

### Step 5: Summary

After all tasks processed:

```
Burn complete!
- Processed: 3 tasks
- Succeeded: 3
- Failed: 0
- Commits: [0015], [0016], [0017]
```

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

---

*Burn powers through the queue: Queue captures → Burn executes*
