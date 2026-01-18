# Ship

> "Ship it!" — The universal developer call to action.

Execute a change request, document it, and commit — all in one voice-friendly command.

## Usage

```
/ship <what you want done>
```

**Voice-friendly examples:**
- "Ship add a dark mode toggle"
- "Ship fix the login button crash"
- "Ship update the app icon"
- "Ship refactor the database layer"

## What Ship Does

Ship is your end-to-end change execution skill. When invoked, it:

1. **Understands** your request
2. **Implements** the changes
3. **Documents** the work (creates a prompt file)
4. **Commits** with auto-numbered commit message

Think of it as: **Blueprint** plans → **Forge** builds → **Ship** delivers.

## Instructions

When the user invokes `/ship <request>`, follow this workflow:

### Phase 1: Understand the Request

1. Parse the user's request from the arguments
2. If the request is unclear or ambiguous, ask ONE clarifying question
3. If clear, proceed immediately — Ship is meant to be fast

### Phase 2: Implement the Changes

1. Analyze the codebase to understand what needs to change
2. Make the necessary modifications:
   - Edit existing files
   - Create new files if needed
   - Update related files (imports, exports, etc.)
3. Keep changes focused — don't over-engineer or add unrequested features
4. If you encounter blockers, inform the user and ask how to proceed

### Phase 3: Document the Work

Create a prompt file to document what was done:

1. Determine the next prompt number:
   - List files in `.claude/prompts/` matching `NNNN-*.md`
   - Find the highest number and add 1
   - Format as 4 digits (e.g., `0012`)

2. Create the prompt file at `.claude/prompts/{NNNN}-{slug}.md`:
   - Generate a slug from the request (lowercase, hyphens, max 50 chars)
   - Use `[no-commit]` directive (since we're committing separately)

3. Document:
   - Original request
   - What was changed
   - Files modified/created
   - Any decisions made

### Phase 4: Commit the Changes

1. Determine the next commit number:
   - Run `git log --oneline -50`
   - Find highest `[NNNN]` pattern
   - Add 1 for next number

2. Determine commit type:
   - `feat:` — New feature
   - `fix:` — Bug fix
   - `docs:` — Documentation
   - `style:` — Formatting/styling
   - `refactor:` — Code restructuring
   - `chore:` — Maintenance
   - `perf:` — Performance

3. Stage and commit:
   ```
   git add .
   git commit -m "[NNNN] type: Brief description

   - Detail 1
   - Detail 2

   Co-Authored-By: Claude <noreply@anthropic.com>"
   ```

4. Report the commit hash to the user

### Phase 5: Confirm Completion

Provide a brief summary:
- What was changed
- Prompt file created
- Commit number and hash
- Any follow-up suggestions

## Example Flow

**User says:** `/ship add a floating action button to the home screen`

**Ship executes:**
1. Reads MainActivity.kt and related UI files
2. Adds FloatingActionButton to the home screen composable
3. Creates `.claude/prompts/0012-add-floating-action-button.md`
4. Commits as `[0012] feat: Add floating action button to home screen`
5. Reports: "Shipped! Added FAB to HomeScreen. Commit [0012] @ abc123"

## Voice Invocation Tips

Ship is designed for natural voice commands:

| Say This | Ship Understands |
|----------|------------------|
| "Ship add dark mode" | Add dark mode feature |
| "Ship fix the crash" | Investigate and fix crash |
| "Ship update the colors" | Modify color scheme |
| "Ship refactor auth" | Restructure authentication code |
| "Ship add tests for login" | Create login tests |

## Options

- `/ship --no-commit <request>` — Make changes but don't commit
- `/ship --no-doc <request>` — Skip creating prompt documentation
- `/ship --dry-run <request>` — Show what would be done without doing it

## Safety

- Ship will not make changes to files outside the project
- Ship will warn before modifying critical files (AndroidManifest.xml, build.gradle.kts)
- Ship creates documentation, so changes are always traceable
- Ship commits atomically, so changes can be easily reverted

## Quick Reference

```
/ship <request>           # Full flow: implement → document → commit
/ship --no-commit <req>   # Implement and document only
/ship --dry-run <req>     # Preview what would happen
```

---

*Ship is part of the Launchpad workflow: Blueprint → Forge → Ship*
