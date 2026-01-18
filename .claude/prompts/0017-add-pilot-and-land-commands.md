[no-commit]

# Add /pilot and /land Commands

## Original Request
Create a pilot command that is the same as ship but works interactively with the user present, able to answer questions and provide input. After building and installing, wait for user verification. Also create /land command to commit the pending changes from pilot sessions.

## Implementation

### /pilot Command
Created `.claude/commands/pilot.md` with:
- Interactive development workflow (user present throughout)
- 6-phase process: Discuss → Plan → Implement → Build/Install → Verify → Ready for Landing
- Conversation logging for all Q&A exchanges
- Build and install app for user testing
- Wait for explicit user approval before marking ready
- Creates progress logs at `.claude/prompts/logs/pilot_{NNNN}_{timestamp}.md`
- Does NOT commit - leaves changes pending for /land

### /land Command
Created `.claude/commands/land.md` with:
- Finds pending pilot session (most recent or by ID)
- Verifies changes match pilot log
- Creates prompt documentation
- Commits with auto-numbered message
- Pushes to remote
- Updates pilot log with landing info

## Key Differences: Pilot vs Ship

| Aspect | /ship | /pilot |
|--------|-------|--------|
| User presence | Optional | Required |
| Questions | Minimal | Encouraged |
| Build/Install | Optional | Always |
| User testing | Not included | Required |
| Commits | Automatic | Deferred to /land |

## Files Created
- `.claude/commands/pilot.md` - Interactive development command
- `.claude/commands/land.md` - Commit pending pilot changes

## Voice Commands
- "Pilot add a feature" - Start interactive session
- "Land" or "Land it" - Commit pending changes
- "Abort" - Cancel pilot session

## Workflow
```
/pilot <request>  →  Interactive development  →  User tests  →  /land  →  Committed
```
