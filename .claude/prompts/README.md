# Claude Code Prompts

This directory contains prompt files that can be executed using the `/execute-prompt` command.

## Creating Prompts

Ask Claude to use the **Prompt Creator** skill to create new prompts with auto-incrementing prefixes:

```
"Create a new prompt for adding user authentication"
"Use the prompt creator skill to make a new prompt"
```

This will create files like:
- `0001-add-user-authentication.md`
- `0002-refactor-database.md`
- `0003-fix-login-bug.md`

## Executing Prompts

```bash
# In Claude Code, run:
/execute-prompt <prompt-file>

# Examples:
/execute-prompt 0001-add-user-authentication.md
/execute-prompt example-prompt.md
```

## Creating Prompt Files

Create a markdown file in this directory with your task instructions.

### Optional Directives

Add these at the top of your prompt file:

| Directive | Description |
|-----------|-------------|
| `[auto-commit]` | Automatically commit and push changes if successful |
| `[no-commit]` | Do not commit changes, even if successful |
| `[branch: name]` | Create or switch to a specific branch before executing |

### Example Prompt File

```markdown
# Add Login Button

[auto-commit]

## Task

Add a login button to the main screen that navigates to a login activity.

## Requirements

1. Create a new LoginActivity
2. Add a "Login" button to MainActivity
3. Wire up navigation between the two
4. Use Material Design 3 styling

## Acceptance Criteria

- Button is visible on main screen
- Clicking button opens LoginActivity
- Back button returns to MainActivity
```

## Logs

Execution logs are stored in `./logs/` with the format:
```
{prompt-filename}_{YYYYMMDD_HHMMSS}.log
```

Each log contains:
- Full prompt content
- Step-by-step execution details
- Files modified/created
- Commit information (if applicable)
- Final status and summary
