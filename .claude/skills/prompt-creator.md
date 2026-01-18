# Prompt Creator

Create a new prompt file with an auto-incrementing 4-digit prefix in `.claude/prompts/`.

## Instructions

When the user wants to create a new prompt file, follow these steps:

### Step 1: Determine the Next Prefix Number

1. List all `.md` files in `.claude/prompts/` directory (excluding README.md and example-prompt.md)
2. Find all files matching the pattern `NNNN-*.md` (where N is a digit)
3. Extract the highest prefix number found
4. Add 1 to get the next prefix number
5. Format as 4 digits with leading zeros (e.g., `0001`, `0002`, `0023`)

If no numbered files exist, start with `0001`.

### Step 2: Get Prompt Details

Ask the user:
1. What is the name/title of this prompt?
2. What should this prompt accomplish? (task description)
3. Any specific requirements?
4. Should changes be auto-committed? (`[auto-commit]`, `[no-commit]`, or neither)

### Step 3: Generate Filename

1. Take the prompt name/title
2. Convert to lowercase
3. Replace spaces with hyphens
4. Remove special characters (keep only letters, numbers, hyphens)
5. Truncate to max 50 characters if needed
6. Prepend the 4-digit prefix
7. Append `.md` extension

Example: "Add User Authentication" → `0003-add-user-authentication.md`

### Step 4: Create the Prompt File

Create the file at `.claude/prompts/{prefix}-{name}.md` with this structure:

```markdown
# {Title}

{directive if specified, e.g., [auto-commit] or [no-commit]}

## Task

{Main task description}

## Requirements

{List of requirements as bullet points}

## Expected Outcome

{What should be accomplished when this prompt is executed}
```

### Step 5: Confirm Creation

Display:
1. The full path of the created file
2. The prefix number used
3. Remind user they can execute it with `/execute-prompt {filename}`

## Example Output

```
Created: .claude/prompts/0001-add-login-feature.md
Prefix: 0001

To execute this prompt, run:
/execute-prompt 0001-add-login-feature.md
```
