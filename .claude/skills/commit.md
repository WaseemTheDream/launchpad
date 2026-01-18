# Commit

Automate committing changes to the current branch with intelligent commit message generation, auto-incrementing commit numbers, and automatic push to remote.

## Instructions

When the user wants to commit changes, follow these steps:

### Step 1: Check Git Status

1. Run `git status` to see all modified, added, and deleted files
2. Run `git diff --stat` to get a summary of changes
3. If there are no changes to commit, inform the user and stop

### Step 2: Determine the Next Commit Number

1. Run `git log --oneline -100` to get recent commits
2. Look for commits matching the pattern `[NNNN]` where N is a digit
3. Find the highest commit number
4. Add 1 to get the next commit number
5. Format as 4 digits with leading zeros (e.g., `[0001]`, `[0042]`, `[0123]`)

If no numbered commits exist, start with `[0001]`.

### Step 3: Analyze Changes for Commit Message

Analyze the changes to generate an intelligent commit message:

1. **Identify the type of change:**
   - `feat:` - New feature or functionality
   - `fix:` - Bug fix
   - `docs:` - Documentation changes
   - `style:` - Formatting, styling (no code change)
   - `refactor:` - Code restructuring without behavior change
   - `test:` - Adding or updating tests
   - `chore:` - Maintenance, dependencies, config
   - `build:` - Build system or dependencies
   - `perf:` - Performance improvements

2. **Summarize the changes:**
   - Read the diff to understand what was changed
   - Identify the primary purpose/goal
   - Note key files or components affected

3. **Generate a concise message:**
   - First line: `[NNNN] type: brief description` (max 72 chars)
   - Blank line
   - Body: More detailed explanation if needed
   - List significant changes as bullet points

### Step 4: Stage Changes

1. Run `git add .` to stage all changes
2. Or if user specified specific files, stage only those

### Step 5: Create the Commit

Create the commit with the generated message format:

```
[NNNN] type: Brief description of changes

- Detail about change 1
- Detail about change 2
- Detail about change 3

Co-Authored-By: Claude <noreply@anthropic.com>
```

### Step 6: Push to Remote (Default: Yes)

**By default, push to remote after committing.** This ensures changes are backed up and available.

1. Check if branch has an upstream:
   - If yes: Run `git push`
   - If no upstream: Run `git push -u origin {branch}`
2. Report push status to user

**To skip pushing**, user can say "commit without push" or "commit --no-push".

### Step 7: Confirm Completion

Display:
1. Commit number used
2. Commit hash
3. Summary of what was committed
4. Push status (pushed to {remote}/{branch})

## Commit Message Examples

```
[0001] feat: Add user authentication system

- Create LoginActivity with email/password fields
- Add AuthManager for handling login state
- Implement secure token storage
- Add logout functionality to MainActivity

Co-Authored-By: Claude <noreply@anthropic.com>
```

```
[0015] fix: Resolve crash on empty user input

- Add null check in FormValidator
- Display error message for empty fields
- Update unit tests for edge cases

Co-Authored-By: Claude <noreply@anthropic.com>
```

```
[0042] docs: Update environment setup guide

- Add Python installation instructions
- Include winget commands for Windows
- Reorganize section order

Co-Authored-By: Claude <noreply@anthropic.com>
```

## Quick Commit (No Prompt)

If the user says "quick commit" or "commit now", skip asking questions and:
1. Auto-detect the change type
2. Generate the message automatically
3. Commit immediately
4. Push to remote (default behavior)

## Options

| Option | Description |
|--------|-------------|
| (default) | Commit and push to remote |
| `--no-push` | Commit without pushing |
| `--amend` | Amend the previous commit (use sparingly) |

## Safety Checks

Before committing, verify:
- Not committing sensitive files (.env, credentials, API keys)
- Not on a protected branch that requires PR (unless user confirms)
- No merge conflicts exist

Before pushing, verify:
- Remote is configured and accessible
- Not force-pushing to protected branches (main/master)
