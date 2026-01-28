[no-commit]

# Add Push to Remote by Default

## Original Request
Update the ship command and any other relevant commands that create commits to ensure that they push to remote by default.

## Implementation

Updated commands to push to remote by default after committing:

### 1. ship.md
- Added step 6 "Pushes to remote (by default)" to What Ship Does
- Updated Phase 4 to "Commit and Push the Changes"
- Added `git push` step after commit
- Added `--no-push` option to skip pushing
- Updated progress log format to include "Pushed: Yes"
- Updated example flow to show push step
- Updated Quick Reference to show push is default

### 2. land.md
- Already had push to remote by default (no changes needed)
- Has `--no-push` option already documented

### 3. burn.md
- Updated description to mention push in workflow
- Added push step to Ship workflow execution
- Added `--no-push` option

### 4. commit.md (skill)
- Already had push to remote by default (was updated in prompt 0015)
- Has `--no-push` option already documented

## Files Changed
- `.claude/commands/ship.md` - Added push to remote as default behavior
- `.claude/commands/burn.md` - Added push mention and --no-push option

## Decisions Made
- land.md already pushes by default - no changes needed
- commit.md skill already pushes by default - no changes needed
- All commands now have `--no-push` option for opting out

## Session Reference
- **Ship Session**: ship_0029_20260128_151754
