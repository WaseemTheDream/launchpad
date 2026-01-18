[no-commit]

# Add /flight-plan Command

## Original Request
Create a flight-plan command that is an advanced version of queue - very interactive, takes input files or extended prompts, and through interactive brainstorming plans out all finer details of changes.

## Implementation

### /flight-plan Command
Created `.claude/commands/flight-plan.md` with:

**Input Options:**
- `<idea or goal>` - Start interactive planning from a description
- `--file <path>` - Plan from a requirements file
- `--prompt <number>` - Plan from existing prompt file
- `list` - Show all flight plans
- `execute <plan-id>` - Execute a specific plan

**Interactive Brainstorming Phases:**
1. Clarify the Vision - Problem, ideal state, target user
2. Define Requirements - Must have, should have, nice to have, out of scope
3. Explore User Experience - Discovery, happy path, error handling
4. Technical Considerations - Approach, offline, performance, integrations
5. Edge Cases & Constraints - Edge cases, platform concerns, security
6. Testing & Validation - Verification, manual vs automated testing

**Flight Plan Document Structure:**
- Mission Overview (ID, status, priority)
- Original Request
- Brainstorming Session (conversation log)
- Requirements (MoSCoW prioritization)
- Technical Design (architecture, files, dependencies)
- Implementation Steps (numbered, detailed)
- Testing Plan
- Risks & Mitigations
- Estimated Scope
- Execution Options

### Directory Structure
Created `.claude/flight-plans/` with:
- `archive/` subdirectory for completed/abandoned plans
- `.gitkeep` to preserve directory

## Flight Plan vs Queue

| Aspect | /queue | /flight-plan |
|--------|--------|--------------|
| Input | Quick task description | Idea, file, or prompt |
| Interaction | Minimal | Deep brainstorming |
| Output | One-liner in queue.md | Full plan document |
| Detail level | "Add dark mode" | UI, colors, persistence, animations |
| Best for | Quick captures | Complex features |

## Files Created
- `.claude/commands/flight-plan.md` - The command definition
- `.claude/flight-plans/.gitkeep` - Directory structure

## Workflow Integration
```
/blueprint          # High-level product vision
    ↓
/flight-plan        # Detailed implementation planning
    ↓
/pilot or /ship     # Execute the plan
    ↓
/land               # Commit (if using pilot)
```
