# Session: Add /queue and /burn Commands

[no-commit]

## Date
January 2026

## Summary
Created voice-friendly task queue system with `/queue` to save tasks for later and `/burn` to execute them all.

## Naming Rationale

### /queue
- Clear intent - everyone knows what a queue is
- Voice-friendly: "Queue add dark mode"
- Captures the "save for later" semantic

### /burn (originally "flush")
- Renamed from "flush" for cooler space theme
- Fits launchpad theme - rocket burn
- One syllable, voice-friendly: "Burn"
- Conveys power and clearing through tasks

## What These Commands Do

### /queue
- Saves a task to `.claude/queue.md` without executing
- Use when AI is busy on something else
- Voice: "Queue fix the settings crash"
- Subcommands: `list`, `clear`, `pop`

### /burn
- Executes all queued tasks sequentially
- Uses Ship workflow for each (implement → document → commit)
- Voice: "Burn" (one word)
- Options: `--dry-run`, `--first`, `--skip-failed`

## Files Created
- `.claude/commands/queue.md` - Task queue command
- `.claude/commands/burn.md` - Queue execution command

## Files Modified
- `CLAUDE.md` - Added both commands
- `README.md` - Added both commands

## Voice Command Summary

| Say | Effect |
|-----|--------|
| "Ship add login" | Execute immediately |
| "Queue add login" | Save for later |
| "Burn" | Execute all queued |

## Design Decisions
1. Queue stores tasks in markdown for transparency
2. Burn processes tasks using Ship workflow
3. Each burn task gets its own commit (easy revert)
4. Failed tasks don't stop entire burn by default
