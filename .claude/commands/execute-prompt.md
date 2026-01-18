# Execute Prompt File

Execute a prompt file and log the progress and results.

## Arguments

- `$ARGUMENTS` - Path to the prompt file (relative to .claude/prompts/ or absolute path)

## Instructions

You are executing a prompt file provided by the user. Follow these steps carefully:

### Step 1: Initialize Logging

1. Generate a timestamp for this execution: `YYYYMMDD_HHMMSS` format
2. Create a log file at `.claude/prompts/logs/{prompt_filename}_{timestamp}.log`
3. Write the initial log entry with:
   - Execution start time
   - Prompt file being executed
   - Current git branch and status

### Step 2: Read and Parse the Prompt

1. Read the prompt file from the provided path
   - If path doesn't start with `/` or `C:\`, prepend `.claude/prompts/`
2. Log the prompt content to the log file
3. Parse any special directives in the prompt (see Prompt Format section below)

### Step 3: Execute the Prompt

1. Log: "=== EXECUTION STARTED ==="
2. Work through the prompt requirements step by step
3. Log each significant action taken:
   - Files read
   - Files created or modified
   - Commands executed
   - Decisions made
4. If you encounter errors, log them and attempt to recover if possible

### Step 4: Determine if Commit is Appropriate

After completing the work, evaluate whether to commit and push:

**DO commit and push if:**
- The prompt explicitly requests it
- Significant, complete changes were made
- All changes are working/validated (tests pass if applicable)
- The prompt file contains `[auto-commit]` directive

**DO NOT commit and push if:**
- The prompt contains `[no-commit]` directive
- Changes are incomplete or experimental
- There were unresolved errors
- Only exploratory/research work was done

### Step 5: Commit and Push (if appropriate)

1. Log: "=== PREPARING COMMIT ==="
2. Stage all relevant changes: `git add .`
3. Create a descriptive commit message summarizing what was done
4. Commit the changes
5. Push to remote: `git push`
6. Log the commit hash and push status

### Step 6: Finalize Log

1. Log: "=== EXECUTION COMPLETED ==="
2. Write a summary section including:
   - Total files modified/created
   - Whether changes were committed and pushed
   - Any warnings or notes
   - Execution end time
3. Save and close the log file

## Prompt File Format

Prompt files can include optional directives at the top:

```
[auto-commit]     # Automatically commit and push if successful
[no-commit]       # Do not commit changes, even if successful
[branch: name]    # Create/switch to a specific branch before executing
```

The rest of the file is the prompt/task to execute.

## Log File Format

```
================================================================================
PROMPT EXECUTION LOG
================================================================================
Prompt File: {filename}
Started: {timestamp}
Git Branch: {branch}
Git Status: {clean/dirty}

================================================================================
PROMPT CONTENT
================================================================================
{full prompt content}

================================================================================
EXECUTION LOG
================================================================================
[timestamp] Action taken...
[timestamp] Action taken...

================================================================================
SUMMARY
================================================================================
Files Modified: {count}
Files Created: {count}
Committed: {yes/no}
Commit Hash: {hash or N/A}
Pushed: {yes/no}
Completed: {timestamp}
Status: {SUCCESS/PARTIAL/FAILED}
Notes: {any relevant notes}
================================================================================
```

## Example Usage

```
/execute-prompt add-feature.md
/execute-prompt .claude/prompts/refactor-auth.md
/execute-prompt C:\Users\Waz\prompts\custom-task.md
```

## Now Execute

Read the prompt file at: $ARGUMENTS

Execute it following the steps above, creating appropriate logs and handling commits as specified.
