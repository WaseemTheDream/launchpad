# Queue

> Save it for later — when you're busy but don't want to forget.

Queue a task for later execution without interrupting current work.

## Usage

```
/queue <what you want done later>
```

**Voice-friendly examples:**
- "Queue add dark mode"
- "Queue fix the settings crash"
- "Queue refactor the database"
- "Queue add unit tests for auth"

## What Queue Does

Queue captures your request and saves it to the task queue without executing immediately. Use this when:

- Claude is already working on something
- You think of something but want to stay focused
- You want to batch up multiple tasks
- You're brainstorming and capturing ideas

Think of it as: **Ship** executes now → **Queue** executes later.

## Instructions

When the user invokes `/queue <request>`, follow this workflow:

### Step 1: Parse the Request

1. Extract the task description from the arguments
2. If completely unclear, ask ONE quick clarifying question
3. Otherwise, proceed immediately — queuing should be fast

### Step 2: Add to Queue File

1. Open or create `.claude/queue.md`
2. Add the task with timestamp:
   ```markdown
   - [ ] {task description} (queued: {YYYY-MM-DD HH:MM})
   ```
3. Save the file

### Step 3: Confirm to User

Provide brief confirmation:
```
Queued: "{task description}"
Queue has {N} pending tasks.
```

## Queue File Format

The queue is stored at `.claude/queue.md`:

```markdown
# Task Queue

Pending tasks to be executed. Run `/burn` to process all, or `/ship` individual items.

## Pending

- [ ] Add dark mode toggle (queued: 2026-01-17 14:30)
- [ ] Fix navigation crash on back press (queued: 2026-01-17 14:35)
- [ ] Update app icon to new design (queued: 2026-01-17 15:00)

## Completed

- [x] Add login screen (completed: 2026-01-17 13:00)
- [x] Implement user settings (completed: 2026-01-17 12:30)
```

## Related Commands

| Command | Purpose |
|---------|---------|
| `/queue <task>` | Add task to queue |
| `/queue list` | Show all pending tasks |
| `/queue clear` | Remove all pending tasks |
| `/queue pop` | Remove and show the oldest task (for manual execution) |
| `/burn` | Execute all queued tasks sequentially |

### /queue list

Shows all pending tasks:

```
Pending tasks (3):
1. Add dark mode toggle (queued: 2026-01-17 14:30)
2. Fix navigation crash (queued: 2026-01-17 14:35)
3. Update app icon (queued: 2026-01-17 15:00)
```

### /queue clear

Removes all pending tasks (with confirmation):

```
Clear 3 pending tasks? This cannot be undone.
```

### /queue pop

Removes and displays the oldest task for manual execution:

```
Popped: "Add dark mode toggle"
Remaining: 2 tasks

You can now run: /ship add dark mode toggle
```

## Voice Invocation

Queue is designed for quick voice capture:

| Say This | What Happens |
|----------|--------------|
| "Queue add tests" | Adds "add tests" to queue |
| "Queue the login fix" | Adds "the login fix" to queue |
| "Queue list" | Shows pending tasks |
| "Queue clear" | Clears the queue |

## Integration with Ship

Queue and Ship work together:

1. **During a task**: "Queue add error handling" — saves for later
2. **After completing**: "Ship add error handling" — or `/burn` to do all

## Example Session

```
User: /ship implement user authentication
Claude: Working on authentication...

User: Queue add password reset
Claude: Queued: "add password reset" (1 pending)

User: Queue add email verification
Claude: Queued: "add email verification" (2 pending)

Claude: ...finished authentication. Committed [0015].

User: /burn
Claude: Initiating burn sequence — 2 tasks queued...
- Shipping: add password reset...
- Shipping: add email verification...
All tasks complete. Committed [0016], [0017].
```

---

*Queue is part of the Launchpad workflow: Blueprint → Forge → Ship/Queue*
