# Land

> "Cleared to land." — Touch down and secure your changes.

Commit pending changes from a `/pilot` session with proper documentation and logging.

## Usage

```
/land                    # Land the most recent pilot session
/land <session_id>       # Land a specific pilot session
```

**Voice-friendly examples:**
- "Land" (commits most recent pilot changes)
- "Land it"
- "Land pilot 0018"

## What Land Does

Land is the commit phase for `/pilot` sessions. When invoked, it:

1. **Finds** the pending pilot session
2. **Verifies** changes are ready to commit
3. **Creates** prompt documentation
4. **Commits** with auto-numbered message
5. **Updates** the pilot progress log
6. **Pushes** to remote (by default)

Think of it as: **Pilot** flies → **Land** touches down safely.

## Instructions

When the user invokes `/land`, follow this workflow:

### Phase 0: Find Pilot Session

1. If session_id provided, use that
2. Otherwise, find the most recent pilot log with status `PENDING_LAND`:
   - Search `.claude/prompts/logs/pilot_*.md`
   - Check for `**Status**: PENDING_LAND`
3. If no pending session found:
   - Inform user: "No pending pilot session found. Run /pilot first."
   - Exit

4. Read the pilot progress log to understand:
   - Original request
   - Changes made
   - Suggested commit message

### Phase 1: Verify Ready to Land

1. Check git status to confirm there are changes to commit
2. Verify the changes match what's documented in the pilot log
3. If discrepancies found, warn the user:
   - "Found additional uncommitted changes not in the pilot log. Include them?"
4. Get user confirmation to proceed

### Phase 2: Create Prompt Documentation

1. Determine the next prompt number:
   - List files in `.claude/prompts/` matching `NNNN-*.md`
   - Find highest number and add 1
   - Format as 4 digits (e.g., `0019`)

2. Create prompt file at `.claude/prompts/{NNNN}-{slug}.md`:

```markdown
[no-commit]

# {Title from request}

## Original Request
{From pilot log}

## Conversation Summary
{Key Q&A from pilot session}

## Implementation
{What was done}

## Files Changed
{List from pilot log}

## Testing Notes
{User feedback from pilot session}

## Session Reference
- **Pilot Session**: {session_id}
- **Landed**: {timestamp}
```

3. Log: `Created prompt file: {path}`

### Phase 3: Commit Changes

1. Determine the next commit number:
   - Run `git log --oneline -50`
   - Find highest `[NNNN]` pattern
   - Add 1 for next number

2. Use the suggested commit message from pilot log, or generate:
   - Determine type: `feat:`, `fix:`, `refactor:`, etc.
   - Write brief description
   - Include bullet points for details

3. Stage and commit:
   ```bash
   git add .
   git commit -m "[NNNN] type: Brief description

   - Detail 1
   - Detail 2

   Co-Authored-By: Claude <noreply@anthropic.com>"
   ```

4. Push to remote:
   ```bash
   git push
   ```

5. Log: `Committed: [NNNN] @ {hash}`

### Phase 4: Update Pilot Log

1. Update the pilot progress log:
   - Change status: `**Status**: LANDED`
   - Add landing info:

```markdown
## Landing Complete
- **Landed**: {timestamp}
- **Commit**: [NNNN] @ {hash}
- **Prompt File**: {path}
- **Pushed**: Yes
```

2. Add to execution log:
   ```
   [{timestamp}] Landed successfully: [NNNN] @ {hash}
   ```

### Phase 5: Confirm to User

Provide summary:
```
Landed! ✓

Pilot session: {session_id}
Commit: [NNNN] @ {hash}
Prompt: {prompt_file}
Files: {count} modified, {count} created

Changes are now committed and pushed.
```

## Options

- `/land` — Land most recent pending pilot session
- `/land <session_id>` — Land specific session
- `/land --no-push` — Commit but don't push
- `/land --amend` — Amend to previous commit (use carefully)

## Edge Cases

### No Pending Session
```
No pending pilot session found.

Recent pilot sessions:
- pilot_0018_20260117_150000: LANDED
- pilot_0017_20260117_140000: ABORTED

Run /pilot to start a new interactive session.
```

### Uncommitted Changes Outside Pilot
```
Found changes not tracked in pilot session:
- Modified: README.md
- New file: test.txt

Options:
1. Include all changes in this commit
2. Commit only pilot changes (stash others)
3. Abort and review manually

What would you like to do?
```

### Merge Conflicts
```
Cannot land: merge conflicts detected.

Please resolve conflicts manually, then run:
/land --continue
```

## Safety

- Land verifies changes before committing
- Prompts for confirmation if unexpected changes found
- Creates documentation for traceability
- Updates pilot log for audit trail
- Can be aborted before final commit

## Workflow Example

```
User: /pilot add dark mode
... interactive development ...
User: Looks good!
Claude: Changes ready! Run /land to commit.

User: /land
Claude:
Landed! ✓

Pilot session: pilot_0018_20260117_150000
Commit: [0019] feat: Add dark mode toggle @ abc123
Prompt: .claude/prompts/0019-add-dark-mode.md
Files: 3 modified, 1 created

Changes are now committed and pushed.
```

## Related Commands

| Command | Purpose |
|---------|---------|
| `/pilot` | Interactive development (creates pending changes) |
| `/land` | Commit pending pilot changes |
| `/ship` | Autonomous implementation + commit |
| `/reboot` | Resume interrupted sessions |

---

*Land is part of the Launchpad workflow: Pilot → Land*
*For autonomous execution, use /ship instead*
