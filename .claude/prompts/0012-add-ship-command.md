# Session: Add /ship Command

[no-commit]

## Date
January 2026

## Summary
Created the `/ship` command - a voice-friendly command for executing changes, documenting them, and committing in one streamlined workflow.

## Naming Rationale
The name "Ship" was chosen because:
- **One syllable** - Perfect for voice invocation ("Ship add dark mode")
- **Dev culture** - "Ship it!" is universal developer vernacular
- **Fits the theme** - Blueprint plans → Forge builds → Ship delivers
- **Action-oriented** - Implies completion and delivery

Alternative names considered: spark, thrust, ignite, go, make

## What /ship Does
1. **Understands** the user's natural language request
2. **Implements** the changes in the codebase
3. **Documents** by creating a numbered prompt file
4. **Commits** with auto-numbered commit message

## Voice Invocation Examples
- "Ship add a login screen"
- "Ship fix the navigation crash"
- "Ship update the color scheme"
- "Ship refactor the API layer"

## Files Created
- `.claude/commands/ship.md` - The ship command definition

## Files Modified
- `CLAUDE.md` - Added /ship to commands table, project structure, and workflow
- `README.md` - Added /ship to commands tables and project structure

## Design Decisions
1. Made it a command (not skill) for discoverability via `/help`
2. Included options: `--no-commit`, `--no-doc`, `--dry-run`
3. Designed for voice-first interaction pattern
4. Integrates with existing prompt-creator and commit numbering systems
