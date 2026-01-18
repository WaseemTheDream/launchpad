# Session: Update Commit Skill to Push by Default

[no-commit]

## Date
January 2026

## Summary
Updated the commit skill to push changes to remote by default after committing.

## Original Request
Update the commit skill so that it pushes changes to remote by default.

## Changes Made

### Updated `.claude/skills/commit.md`

**Before:**
- Step 6 was "Push to Remote (Optional)" - asked user if they want to push
- Quick commit would ask about pushing

**After:**
- Step 6 is now "Push to Remote (Default: Yes)"
- Pushes automatically after every commit
- Handles upstream setup automatically (`git push -u origin {branch}`)
- User can opt-out with "commit --no-push" or "commit without push"
- Added safety checks for pushing (remote accessible, no force-push to protected branches)

### Key Changes
1. Changed default behavior from "ask" to "push automatically"
2. Added `--no-push` option for when user wants to skip
3. Added push safety checks
4. Updated completion message to include push status
5. Quick commit now pushes by default

## Files Modified
- `.claude/skills/commit.md` - Updated push behavior

## Rationale
Automatic pushing ensures:
- Changes are backed up immediately
- Work is available across devices
- Collaboration is seamless
- No risk of losing work if local machine has issues
