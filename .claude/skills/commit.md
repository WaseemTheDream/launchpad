# Commit

Automate committing changes to the current branch with intelligent commit message generation and auto-incrementing commit numbers.

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

### Step 6: Push to Remote (Optional)

Ask the user if they want to push to remote:
- If yes, run `git push`
- If the branch has no upstream, run `git push -u origin {branch}`

### Step 7: Confirm Completion

Display:
1. Commit number used
2. Commit hash
3. Summary of what was committed
4. Push status (if pushed)

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
4. Ask about pushing

## Safety Checks

Before committing, verify:
- Not committing sensitive files (.env, credentials, API keys)
- Not on a protected branch that requires PR (unless user confirms)
- No merge conflicts exist
