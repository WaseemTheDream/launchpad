# Session: Add Progress Logging and /reboot Command

[no-commit]

## Date
January 2026

## Summary
Enhanced all execution commands with progress logging for session resumption, and created the `/reboot` command to continue interrupted work.

## Original Request
Update all commands that execute AI against prompts to create MD progress logs with AI context, enabling resumption if Claude is disconnected. Also create a continue/resume command with a cool name.

## Changes Made

### 1. Updated /ship Command
- Added Phase 0: Initialize Progress Log (CRITICAL - done before any work)
- Progress log created at `.claude/prompts/logs/ship_{NNNN}_{timestamp}.md`
- Logs include: session info, context snapshot, plan, progress checkboxes, execution log
- Each action logged with timestamp for full audit trail
- Resume instructions automatically added if interrupted

### 2. Updated /burn Command
- Added Phase 0: Initialize Burn Progress Log
- Progress log at `.claude/prompts/logs/burn_{timestamp}.md`
- Tracks each task's completion status with commit hashes
- Enables mid-burn resumption (pick up from incomplete task)
- Logs saved after each task for granular recovery

### 3. Updated /execute-prompt Command
- Added Step 1: Initialize Progress Log (before any work)
- Progress log at `.claude/prompts/logs/{prompt}_{timestamp}.md`
- Includes full prompt content for context
- Parsed directives documented
- Plan written before execution

### 4. Created /reboot Command
- Voice-friendly: "Reboot" or "Reboot {session_id}"
- Finds interrupted sessions (status: IN_PROGRESS or INTERRUPTED)
- Shows what's completed vs remaining
- Verifies codebase state before resuming
- Hands off to appropriate command (ship/burn/exec)
- Full history preserved in progress log

## Progress Log Format

All logs follow consistent structure:
```markdown
# {Type} Progress Log

## Session Info
- **Session ID**: {type}_{id}_{timestamp}
- **Started**: {timestamp}
- **Status**: IN_PROGRESS | COMPLETED | INTERRUPTED
- **Request/Prompt**: {description}

## Context Snapshot
- **Git Branch**: {branch}
- **Git Status**: {clean/dirty}
- **Working Directory**: {path}

## Progress
- [x] Completed task
- [ ] Pending task

## Execution Log
[timestamp] Action taken...

## Resume Instructions (if interrupted)
To continue: /reboot {session_id}
```

## Files Created
- `.claude/commands/reboot.md` - Resume command

## Files Modified
- `.claude/commands/ship.md` - Added progress logging
- `.claude/commands/burn.md` - Added progress logging
- `.claude/commands/execute-prompt.md` - Added progress logging
- `CLAUDE.md` - Added /reboot command
- `README.md` - Added /reboot command

## Voice Command Summary

| Command | Voice | Purpose |
|---------|-------|---------|
| `/ship` | "Ship add login" | Execute with logging |
| `/burn` | "Burn" | Execute queue with logging |
| `/reboot` | "Reboot" | Resume interrupted work |

## Design Decisions
1. Named resume command `/reboot` - fits space theme, implies system restart
2. Progress logs use markdown for human readability
3. Logs saved immediately and after each action (not just at end)
4. Session IDs include timestamp for uniqueness
5. Resume instructions auto-generated in logs
