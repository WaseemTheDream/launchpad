# Flight Plan

> "No plan survives first contact with the enemy — so plan thoroughly." — Interactive mission planning.

Create a detailed implementation plan through interactive brainstorming before execution.

## Usage

```
/flight-plan <idea or goal>              # Start interactive planning session
/flight-plan --file <path>               # Plan from a requirements file
/flight-plan --prompt <prompt-number>    # Plan from an existing prompt file
/flight-plan list                        # List all pending flight plans
/flight-plan execute <plan-id>           # Execute a specific plan
```

**Voice-friendly examples:**
- "Flight plan add user authentication"
- "Flight plan redesign the home screen"
- "Flight plan file requirements.md"
- "Flight plan execute auth"

## What Flight Plan Does

Flight Plan is your strategic planning companion. Unlike `/queue` which just captures tasks, Flight Plan:

1. **Explores** your idea through guided questions
2. **Clarifies** requirements, edge cases, and constraints
3. **Designs** the implementation approach collaboratively
4. **Documents** a comprehensive plan with all details
5. **Saves** the plan for future execution

Think of it as: **Queue** captures → **Flight Plan** plans → **Ship/Burn** executes.

## Flight Plan vs Queue

| Aspect | /queue | /flight-plan |
|--------|--------|--------------|
| Input | Quick task description | Idea, file, or prompt |
| Interaction | Minimal (1 question max) | Deep brainstorming |
| Output | One-liner in queue.md | Full plan document |
| Detail level | "Add dark mode" | UI placement, colors, persistence, animations, etc. |
| Best for | Quick captures | Complex features |

## Instructions

When the user invokes `/flight-plan`, follow this interactive workflow:

### Phase 0: Initialize Flight Plan Session

1. Generate timestamp and plan ID
2. Create flight plan document at `.claude/flight-plans/{plan-id}.md`
3. Write initial template:

```markdown
# Flight Plan: {title}

## Mission Overview
- **Plan ID**: fp_{NNNN}_{timestamp}
- **Created**: {timestamp}
- **Status**: PLANNING | READY | EXECUTING | COMPLETED
- **Priority**: {to be determined}

## Original Request
{user's initial request or file contents}

## Brainstorming Session
{Conversation log will be added here}

## Requirements
### Must Have
{Critical requirements}

### Should Have
{Important but not critical}

### Nice to Have
{Optional enhancements}

### Out of Scope
{Explicitly excluded}

## Technical Design
### Architecture Decisions
{Key technical choices}

### Files to Create/Modify
{Detailed file list with descriptions}

### Dependencies
{External deps, internal deps, order of operations}

## Implementation Steps
{Numbered, detailed steps}

## Testing Plan
{How to verify the implementation}

## Risks & Mitigations
{Potential issues and how to handle them}

## Estimated Scope
- **Files**: {count}
- **Complexity**: Low | Medium | High
- **Suggested approach**: Single /ship | Multiple /ships | /pilot session
```

### Phase 1: Understand the Input

**If `--file <path>` provided:**
1. Read the specified file
2. Parse requirements, user stories, or specifications
3. Summarize: "I've read your requirements file. It describes {summary}. Let's plan this out."

**If `--prompt <number>` provided:**
1. Read `.claude/prompts/{NNNN}-*.md`
2. Extract the context and requirements
3. Summarize: "I've reviewed prompt {number}. Let's plan the implementation."

**If idea/goal provided:**
1. Acknowledge the goal
2. Share initial understanding
3. Begin brainstorming

### Phase 2: Interactive Brainstorming

**This is the core of flight-plan — deep, structured conversation!**

Guide the user through these discovery areas:

#### 2.1 Clarify the Vision
- "What problem does this solve for the user?"
- "Can you describe the ideal end state?"
- "Who is the primary user of this feature?"

#### 2.2 Define Requirements
- "What are the absolute must-haves?"
- "What would be nice but not essential?"
- "What should we explicitly NOT include?"

#### 2.3 Explore User Experience
- "How should users discover this feature?"
- "What's the happy path flow?"
- "What happens when things go wrong?"

#### 2.4 Technical Considerations
- "Any preferences on implementation approach?"
- "Should this work offline?"
- "Any performance requirements?"
- "Integration with existing features?"

#### 2.5 Edge Cases & Constraints
- "What edge cases should we handle?"
- "Any platform-specific concerns?"
- "Security or privacy considerations?"

#### 2.6 Testing & Validation
- "How will we know it's working correctly?"
- "What should we test manually vs. automatically?"

**Log every Q&A in the Brainstorming Session section!**

### Phase 3: Synthesize the Plan

After gathering enough information:

1. Summarize key decisions
2. Draft the technical design
3. Create implementation steps
4. Identify risks
5. Estimate scope

Present to user: "Based on our discussion, here's the plan. Does this capture everything?"

### Phase 4: Finalize Flight Plan

1. Update status to: `**Status**: READY`
2. Add execution instructions:

```markdown
## Execution Options

**Option 1: Single Ship** (if small)
```
/ship execute flight-plan fp_0001
```

**Option 2: Pilot Session** (if complex)
```
/pilot execute flight-plan fp_0001
```

**Option 3: Step by Step**
Execute each implementation step as a separate /ship command.
```

3. Confirm to user:
   - Plan ID
   - Location of plan file
   - Recommended execution approach

## Flight Plan Document Structure

```markdown
# Flight Plan: User Authentication System

## Mission Overview
- **Plan ID**: fp_0023_20260117_150000
- **Created**: 2026-01-17 15:00:00
- **Status**: READY
- **Priority**: High

## Original Request
"Add user authentication with email/password login"

## Brainstorming Session
[15:00:30] User request: Add user authentication with email/password login
[15:00:45] Claude: What authentication features do you need?
[15:01:00] User: Basic login/logout, remember me, password reset
[15:01:15] Claude: Should we include social login options?
[15:01:30] User: Not for MVP, maybe later
[15:02:00] Claude: Where should login appear - splash screen, dedicated screen, or modal?
[15:02:15] User: Dedicated login screen, navigate there if not logged in
[15:03:00] Claude: What should happen after successful login?
[15:03:15] User: Go to home screen, show welcome message
...

## Requirements

### Must Have
- Email/password login
- Logout functionality
- "Remember me" checkbox
- Password reset via email
- Session persistence
- Login screen with form validation

### Should Have
- Password strength indicator
- Email format validation
- Error messages for common issues
- Loading states during auth

### Nice to Have
- Biometric login (fingerprint/face)
- Social login (Google, GitHub)
- Two-factor authentication

### Out of Scope
- User registration (using existing accounts)
- Profile management
- Password change (only reset)

## Technical Design

### Architecture Decisions
- Use Firebase Authentication for backend
- Store auth state in DataStore
- Create AuthViewModel for state management
- Use Navigation component for auth flow

### Files to Create/Modify

**Create:**
- `data/auth/AuthRepository.kt` - Firebase auth wrapper
- `data/auth/AuthState.kt` - Sealed class for auth states
- `ui/auth/LoginScreen.kt` - Login UI
- `ui/auth/LoginViewModel.kt` - Login state management
- `ui/auth/ForgotPasswordScreen.kt` - Password reset UI

**Modify:**
- `MainActivity.kt` - Add auth state check
- `NavGraph.kt` - Add auth routes
- `build.gradle.kts` - Add Firebase dependencies

### Dependencies
- Firebase Auth SDK
- Firebase BOM for version management
- Kotlin Coroutines for async

## Implementation Steps

1. **Add Firebase dependencies**
   - Update build.gradle.kts with Firebase BOM
   - Add Firebase Auth dependency
   - Sync project

2. **Create data layer**
   - Create AuthState sealed class
   - Create AuthRepository with Firebase calls
   - Add error handling

3. **Create Login UI**
   - Build LoginScreen composable
   - Add email/password fields with validation
   - Add "Remember me" checkbox
   - Add "Forgot password" link
   - Style according to app theme

4. **Create ViewModel**
   - Implement LoginViewModel
   - Add login, logout, resetPassword functions
   - Handle loading and error states

5. **Integrate with navigation**
   - Add login route to NavGraph
   - Add auth check to MainActivity
   - Redirect unauthenticated users

6. **Add password reset flow**
   - Create ForgotPasswordScreen
   - Send reset email via Firebase
   - Show confirmation message

7. **Test the flow**
   - Test successful login
   - Test invalid credentials
   - Test password reset
   - Test session persistence

## Testing Plan

**Manual Testing:**
- [ ] Login with valid credentials
- [ ] Login with invalid email format
- [ ] Login with wrong password
- [ ] Remember me persists session
- [ ] Logout clears session
- [ ] Password reset sends email
- [ ] Back navigation from login

**Edge Cases:**
- [ ] Network error during login
- [ ] App killed during auth
- [ ] Expired session handling

## Risks & Mitigations

| Risk | Mitigation |
|------|------------|
| Firebase quota limits | Use caching, implement rate limiting |
| Slow network | Show loading states, timeout handling |
| Token expiration | Implement refresh token flow |
| Email deliverability | Use verified sender, clear instructions |

## Estimated Scope
- **Files**: 7 new, 3 modified
- **Complexity**: Medium
- **Suggested approach**: /pilot session (interactive testing important)

## Execution Options

**Recommended: Pilot Session**
```
/pilot execute flight-plan fp_0023
```
This allows testing the auth flow interactively.

**Alternative: Step by Step**
Execute each implementation step as a separate /ship.
```

## Managing Flight Plans

### List All Plans
```
/flight-plan list
```

Output:
```
Flight Plans:

READY:
  fp_0023 - User Authentication System (created: 2026-01-17)
  fp_0022 - Dark Mode Implementation (created: 2026-01-16)

PLANNING:
  fp_0024 - Settings Redesign (created: 2026-01-17)

COMPLETED:
  fp_0021 - Navigation Refactor (completed: 2026-01-15)
```

### Execute a Plan
```
/flight-plan execute fp_0023
```

This reads the plan and:
1. Confirms execution approach with user
2. Kicks off `/ship` or `/pilot` as appropriate
3. Updates plan status to EXECUTING
4. Tracks progress against the plan

### Archive a Plan
```
/flight-plan archive fp_0021
```

Moves completed or abandoned plans to `.claude/flight-plans/archive/`.

## Voice Commands

| Say This | What Happens |
|----------|--------------|
| "Flight plan auth" | Start planning authentication feature |
| "Flight plan list" | Show all flight plans |
| "Flight plan execute auth" | Execute the auth plan |
| "Flight plan file spec.md" | Plan from spec file |

## Integration with Other Commands

Flight Plan works seamlessly with the Launchpad workflow:

```
/blueprint          # High-level product vision
    ↓
/flight-plan        # Detailed implementation planning
    ↓
/pilot or /ship     # Execute the plan
    ↓
/land               # Commit (if using pilot)
```

## Best Practices

1. **Use for complex features** — Simple tasks don't need flight plans
2. **Be thorough in brainstorming** — The more detail now, the smoother execution later
3. **Review before executing** — Re-read the plan before starting implementation
4. **Update as you learn** — If requirements change, update the plan
5. **Keep plans focused** — One feature per plan, break up large initiatives

## File Locations

- **Active plans**: `.claude/flight-plans/fp_{NNNN}_{timestamp}.md`
- **Archived plans**: `.claude/flight-plans/archive/`
- **Plan index**: `.claude/flight-plans/index.md` (auto-generated)

## Safety

- Flight plans are documents, not code — safe to create and modify
- Execution requires explicit command — plans don't auto-execute
- Version controlled — all plans are tracked in git
- Can be abandoned without consequences

---

*Flight Plan is part of the Launchpad workflow: Blueprint → Flight Plan → Ship/Pilot*
*For quick task capture, use /queue instead*
