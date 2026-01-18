# Execute Prompt File

Execute a prompt file and log the progress and results with full AI context for resumption.

## Arguments

- `$ARGUMENTS` - Path to the prompt file (relative to .claude/prompts/ or absolute path)

## Instructions

You are executing a prompt file provided by the user. Follow these steps carefully:

### Step 1: Initialize Progress Log

**CRITICAL**: Create a progress log IMMEDIATELY before doing any work. This enables resumption if Claude is disconnected.

1. Generate a timestamp for this execution: `YYYYMMDD_HHMMSS` format
2. Create a progress log at `.claude/prompts/logs/{prompt_filename}_{timestamp}.md`
3. Write the initial context:

```markdown
# Prompt Execution Progress Log

## Session Info
- **Session ID**: exec_{prompt_name}_{timestamp}
- **Started**: {timestamp}
- **Status**: IN_PROGRESS
- **Prompt File**: {filename}

## Context Snapshot
- **Git Branch**: {branch}
- **Git Status**: {clean/dirty}
- **Working Directory**: {path}

## Prompt Content
{full prompt content}

## Parsed Directives
- Auto-commit: {yes/no}
- No-commit: {yes/no}
- Branch: {branch name or N/A}

## Plan
{What Claude plans to do based on prompt analysis}

## Progress
- [ ] Parse and understand prompt
- [ ] Execute requirements
- [ ] Validate changes
- [ ] Commit (if applicable)

## Execution Log
[{timestamp}] Starting execution of: {prompt_file}
```

### Step 2: Read and Parse the Prompt

1. Read the prompt file from the provided path
   - If path doesn't start with `/` or `C:\`, prepend `.claude/prompts/`
2. Log: `[{timestamp}] Read prompt file: {path}`
3. Parse any special directives in the prompt (see Prompt Format section below)
4. Log: `[{timestamp}] Parsed directives: {list}`
5. Analyze the prompt and write a plan to the progress log
6. Check: `- [x] Parse and understand prompt`

### Step 3: Execute the Prompt

1. Log: `[{timestamp}] === EXECUTION STARTED ===`
2. Work through the prompt requirements step by step
3. **Log each significant action BEFORE and AFTER**:
   ```
   [{timestamp}] Reading: {filepath}
   [{timestamp}] Modified: {filepath}
   - Change: {description}
   [{timestamp}] Created: {filepath}
   [{timestamp}] Decision: {what was decided and why}
   ```
4. If you encounter errors:
   - Log: `[{timestamp}] ERROR: {description}`
   - Attempt to recover if possible
   - Log recovery attempts
5. **Update progress log after every major step** (enables resumption)
6. Check: `- [x] Execute requirements`

### Step 4: Validate Changes

1. Log: `[{timestamp}] Validating changes`
2. Review what was done against the prompt requirements
3. Log: `[{timestamp}] Validation: {pass/fail} - {notes}`
4. Check: `- [x] Validate changes`

### Step 5: Determine if Commit is Appropriate

After completing the work, evaluate whether to commit:

**DO commit if:**
- The prompt explicitly requests it
- Significant, complete changes were made
- All changes are working/validated (tests pass if applicable)
- The prompt file contains `[auto-commit]` directive

**DO NOT commit if:**
- The prompt contains `[no-commit]` directive
- Changes are incomplete or experimental
- There were unresolved errors
- Only exploratory/research work was done

Log decision: `[{timestamp}] Commit decision: {yes/no} - {reason}`

### Step 6: Commit (if appropriate)

1. Log: `[{timestamp}] === PREPARING COMMIT ===`
2. Determine next commit number from `git log --oneline -50`
3. Stage all relevant changes: `git add .`
4. Create a descriptive commit message
5. Commit the changes
6. Log: `[{timestamp}] Committed: [NNNN] @ {hash}`
7. Check: `- [x] Commit (if applicable)`

### Step 7: Finalize Progress Log

1. Update status: `**Status**: COMPLETED` (or `FAILED` / `PARTIAL`)
2. Add summary section:

```markdown
## Summary
- **Completed**: {timestamp}
- **Status**: COMPLETED | PARTIAL | FAILED
- **Duration**: {elapsed time}
- **Files Modified**: {count}
- **Files Created**: {count}
- **Committed**: {yes/no}
- **Commit**: [NNNN] @ {hash} (or N/A)

## Notes
{Any warnings, issues, or follow-up suggestions}
```

3. If interrupted or failed, add:

```markdown
## Resume Instructions
To continue this work, run: /reboot exec_{prompt_name}_{timestamp}
Remaining tasks:
- {list of incomplete items}
```

## Prompt File Format

Prompt files can include optional directives at the top:

```
[auto-commit]     # Automatically commit if successful
[no-commit]       # Do not commit changes, even if successful
[branch: name]    # Create/switch to a specific branch before executing
```

The rest of the file is the prompt/task to execute.

## Progress Log Format

The progress log at `.claude/prompts/logs/{prompt}_{timestamp}.md`:

```markdown
# Prompt Execution Progress Log

## Session Info
- **Session ID**: exec_add-dark-mode_20260117_143052
- **Started**: 2026-01-17 14:30:52
- **Status**: IN_PROGRESS | COMPLETED | FAILED | PARTIAL
- **Prompt File**: 0015-add-dark-mode.md

## Context Snapshot
- **Git Branch**: master
- **Git Status**: clean
- **Working Directory**: C:\Users\Waz\AndroidStudioProjects\launchpad

## Prompt Content
# Add Dark Mode
[auto-commit]
Add a dark mode toggle to the settings screen...

## Parsed Directives
- Auto-commit: yes
- No-commit: no
- Branch: N/A

## Plan
1. Add darkMode preference to SettingsViewModel
2. Create DarkModeToggle composable
3. Wire up theme switching in MainActivity
4. Test the implementation

## Progress
- [x] Parse and understand prompt
- [x] Execute requirements
- [ ] Validate changes
- [ ] Commit (if applicable)

## Execution Log
[14:30:52] Starting execution of: 0015-add-dark-mode.md
[14:30:53] Read prompt file: .claude/prompts/0015-add-dark-mode.md
[14:30:53] Parsed directives: [auto-commit]
[14:30:55] === EXECUTION STARTED ===
[14:31:00] Reading: app/src/main/java/.../SettingsViewModel.kt
[14:31:15] Modified: SettingsViewModel.kt
- Change: Added darkModeEnabled StateFlow and toggle function
[14:31:30] Modified: SettingsScreen.kt
- Change: Added DarkModeToggle composable with Switch
[14:32:00] INTERRUPTED - Session ended unexpectedly

## Resume Instructions
To continue this work, run: /reboot exec_add-dark-mode_20260117_143052
Remaining tasks:
- Validate changes
- Commit with [auto-commit] directive
```

## Resumption Support

If Claude is interrupted, the progress log contains everything needed to resume:
- Original prompt content
- What was planned
- What was completed
- What remains to be done

Use `/reboot {session_id}` to continue interrupted work.

## Example Usage

```
/execute-prompt add-feature.md
/execute-prompt .claude/prompts/refactor-auth.md
/execute-prompt C:\Users\Waz\prompts\custom-task.md
```

## Now Execute

Read the prompt file at: $ARGUMENTS

Execute it following the steps above, creating the progress log and handling commits as specified.

---

*If interrupted, use /reboot to continue*
