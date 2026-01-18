[no-commit]

# Add Fork & Rename Workflow to README

## Original Request
Update README to suggest forking and renaming the repository if the user already knows their app name.

## Changes Made

### README.md - Quick Start Section
Added two options for getting started:

1. **Option 1: Clone** - For exploring/learning Launchpad
2. **Option 2: Fork & Rename** - For users who know their app name
   - Fork the repository on GitHub
   - Name the new repo after their app (e.g., `fittrack`, `recipe-vault`)
   - Clone the fork
   - Explains why: cleaner git history, package name matches repo

### README.md - Option A Section
- Added "Step 0" recommending fork before starting if user knows app name
- Updated heading to indicate fork is recommended

## Rationale
When users know their app name upfront, starting with a properly named repository:
- Creates cleaner git history (no "rename project" commits)
- Repository name can inform package renaming during /forge
- Feels like "their" project from the start
- Easier to remember and share

## Files Modified
- README.md
