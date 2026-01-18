package com.example.launchpad.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class Command(
    val name: String,
    val description: String,
    val icon: ImageVector,
    val usage: String,
    val examples: List<String>,
    val options: List<CommandOption> = emptyList(),
    val category: CommandCategory,
    val architecture: CommandArchitecture? = null,
    val sourceFileName: String? = null // e.g., "blueprint.md" - references .claude/commands/{file}
)

data class CommandOption(
    val flag: String,
    val description: String
)

/**
 * Detailed architecture information for a command.
 * This helps users understand HOW the command works internally,
 * enabling deeper ideation about how to leverage it.
 */
data class CommandArchitecture(
    val philosophy: String,
    val workflow: List<String>,
    val keyBehaviors: List<KeyBehavior>,
    val bestFor: List<String>,
    val notFor: List<String> = emptyList(),
    val relatedCommands: List<String> = emptyList(),
    val proTips: List<String> = emptyList()
)

/**
 * A key behavior or capability of a command
 */
data class KeyBehavior(
    val title: String,
    val description: String
)

enum class CommandCategory(val title: String) {
    DEVELOPMENT("Development"),
    BUILD("Build & Deploy"),
    WORKFLOW("Workflow")
}

object CommandRepository {
    val commands = listOf(
        // ═══════════════════════════════════════════════════════════════════════════
        // DEVELOPMENT COMMANDS
        // ═══════════════════════════════════════════════════════════════════════════

        Command(
            name = "/blueprint",
            description = "Interactive product ideation. Define your app concept through guided questions.",
            icon = Icons.Default.Architecture,
            usage = "/blueprint",
            examples = listOf(
                "/blueprint",
                "Define a fitness tracking app",
                "Create context for a recipe app"
            ),
            category = CommandCategory.DEVELOPMENT,
            sourceFileName = "blueprint.md",
            architecture = CommandArchitecture(
                philosophy = "Blueprint is the architect before the builder. It ensures Claude deeply understands your vision BEFORE any code is written. By creating structured context files, it prevents misunderstandings and wasted effort later.",
                workflow = listOf(
                    "Display welcome sequence and explain the process",
                    "Scan product-docs/ folder for any existing documentation",
                    "Run interactive 25-question questionnaire covering: Vision, Audience, Features, Tech, Design, Business",
                    "Generate 4 context files in .claude/context/: product-vision.md, technical-spec.md, feature-roadmap.md, design-guidelines.md",
                    "Create initialization prompt for your app",
                    "Create session documentation prompt recording all Q&A",
                    "Commit all files with numbered commit message"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Works With or Without Docs",
                        "If you have product-docs/, Blueprint analyzes them and asks targeted questions to fill gaps. If empty, it guides you through comprehensive questions to define everything from scratch."
                    ),
                    KeyBehavior(
                        "Context Files Are Persistent",
                        "The .claude/context/ files become Claude's 'memory' of your app. Every future session reads these files first, ensuring consistency across all development."
                    ),
                    KeyBehavior(
                        "Session Documentation",
                        "Every Q&A exchange is recorded in a prompt file. This creates an audit trail of all decisions made during ideation."
                    ),
                    KeyBehavior(
                        "Automatic Next Steps",
                        "After completion, Blueprint suggests running /forge to build your MVP, creating a smooth handoff between ideation and building."
                    )
                ),
                bestFor = listOf(
                    "Starting a brand new project",
                    "When you have a rough idea but need to flesh it out",
                    "When working with product documentation",
                    "Before running /forge for the first time"
                ),
                notFor = listOf(
                    "Adding features to an existing app (use /ship or /pilot instead)",
                    "Quick prototyping without planning"
                ),
                relatedCommands = listOf("/forge", "/flight-plan"),
                proTips = listOf(
                    "Put rough notes in product-docs/ before running — even bullet points help",
                    "Answer the monetization and timeline questions honestly — they affect architecture decisions",
                    "Review .claude/context/ files after — you can edit them manually to refine your vision"
                )
            )
        ),

        Command(
            name = "/forge",
            description = "Generate and build your complete MVP. Creates proposal, reviews with you, builds the app.",
            icon = Icons.Default.LocalFireDepartment,
            usage = "/forge",
            examples = listOf(
                "/forge",
                "/forge --confirm-destructive"
            ),
            options = listOf(
                CommandOption("--confirm-destructive", "Confirm rebuild of existing app")
            ),
            category = CommandCategory.DEVELOPMENT,
            sourceFileName = "forge.md",
            architecture = CommandArchitecture(
                philosophy = "Forge transforms vision into reality. It's a one-time 'big bang' that creates your complete MVP in a single session. The key insight: by generating a detailed proposal FIRST and reviewing it with you, Forge ensures alignment before writing any code.",
                workflow = listOf(
                    "Destructive check: Detect if Forge was already run (to prevent accidental rebuilds)",
                    "Load all context files from .claude/context/",
                    "Use Ultrathink to generate comprehensive MVP proposal: screens, navigation, data models, architecture",
                    "Present proposal and gather user feedback",
                    "Iterate on proposal until user approves",
                    "Create detailed MVP prompt file documenting the approved plan",
                    "Build the entire app: Foundation → Navigation → Screens → Data Layer → Polish",
                    "Commit after each major phase with numbered commits",
                    "Create forge session documentation",
                    "Display completion summary with next steps"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Proposal-First Approach",
                        "Forge never starts coding immediately. It generates a detailed proposal including screen mockups, navigation flow diagrams, and data models. You review and approve before any code is written."
                    ),
                    KeyBehavior(
                        "Ultrathink-Powered",
                        "Uses extended thinking for thorough analysis. This means Claude spends more time reasoning through architecture decisions, resulting in better-structured code."
                    ),
                    KeyBehavior(
                        "Destructive By Design",
                        "Forge is meant to be run ONCE per project. It replaces the existing app. The --confirm-destructive flag is a safety mechanism to prevent accidental rebuilds."
                    ),
                    KeyBehavior(
                        "Step-by-Step Option",
                        "You can choose 'Build it step by step' to pause after each phase, letting you test and verify before continuing."
                    ),
                    KeyBehavior(
                        "Comprehensive Documentation",
                        "Creates a forge session prompt that records the proposal, user feedback, all files created/modified, and commits made."
                    )
                ),
                bestFor = listOf(
                    "Creating your initial MVP after running /blueprint",
                    "Starting a fresh project with a clear vision",
                    "When you want a complete working app, not just scaffolding"
                ),
                notFor = listOf(
                    "Adding features to an existing app (use /ship or /pilot)",
                    "Making incremental changes",
                    "Projects where you want to build feature-by-feature"
                ),
                relatedCommands = listOf("/blueprint", "/ship", "/pilot"),
                proTips = listOf(
                    "Take time to review the proposal carefully — changes after build are harder",
                    "Choose 'step by step' mode for your first forge to understand the process",
                    "If the proposal misses something, tell Claude — the proposal phase is for iteration"
                )
            )
        ),

        Command(
            name = "/ship",
            description = "Autonomous changes — implement, document, and commit in one command.",
            icon = Icons.Default.RocketLaunch,
            usage = "/ship <request>",
            examples = listOf(
                "/ship add dark mode toggle",
                "/ship fix the login crash",
                "/ship --no-commit refactor auth"
            ),
            options = listOf(
                CommandOption("--no-commit", "Make changes without committing"),
                CommandOption("--no-doc", "Skip documentation"),
                CommandOption("--dry-run", "Preview without changes")
            ),
            category = CommandCategory.DEVELOPMENT,
            sourceFileName = "ship.md",
            architecture = CommandArchitecture(
                philosophy = "Ship is your autonomous delivery system. Say what you want, and it handles everything: understanding, implementing, documenting, and committing. Designed for voice commands and hands-free operation. The progress log system enables resumption if Claude is interrupted.",
                workflow = listOf(
                    "Create progress log immediately (.claude/prompts/logs/ship_NNNN_timestamp.md)",
                    "Parse and understand the request (ask 1 clarifying question if unclear)",
                    "Analyze codebase and plan changes",
                    "Implement changes, logging each file modification",
                    "Create prompt documentation file",
                    "Commit with auto-numbered message",
                    "Update progress log to COMPLETED",
                    "Report summary to user"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Progress Logging",
                        "Every Ship session creates a detailed progress log BEFORE starting work. This log tracks: original request, plan, progress checkboxes, execution log, and files modified. If Claude disconnects, /reboot can resume from exactly where it stopped."
                    ),
                    KeyBehavior(
                        "Voice-Friendly Design",
                        "Commands like 'Ship add dark mode' are designed to be spoken naturally. Ship parses natural language requests without requiring specific syntax."
                    ),
                    KeyBehavior(
                        "Automatic Documentation",
                        "Every change creates a numbered prompt file (e.g., 0012-add-dark-mode.md) documenting what was done. This creates an audit trail of all development."
                    ),
                    KeyBehavior(
                        "Focused Changes",
                        "Ship keeps changes focused — it implements exactly what you asked for without over-engineering or adding unrequested features."
                    )
                ),
                bestFor = listOf(
                    "Quick, well-defined tasks",
                    "Voice-controlled development",
                    "When you trust Claude to make decisions autonomously",
                    "Feature additions, bug fixes, refactoring"
                ),
                notFor = listOf(
                    "Complex features requiring discussion (use /pilot)",
                    "Features where you want to test before committing (use /pilot)",
                    "Strategic planning (use /flight-plan)"
                ),
                relatedCommands = listOf("/pilot", "/queue", "/burn", "/reboot"),
                proTips = listOf(
                    "Use --dry-run first if you're unsure what changes will be made",
                    "Be specific: 'add dark mode toggle to settings' > 'add dark mode'",
                    "Check .claude/prompts/logs/ to see detailed execution history"
                )
            )
        ),

        Command(
            name = "/pilot",
            description = "Interactive development with user present for testing before commit.",
            icon = Icons.Default.Flight,
            usage = "/pilot <request>",
            examples = listOf(
                "/pilot add settings screen",
                "/pilot redesign home page",
                "/pilot implement auth flow"
            ),
            options = listOf(
                CommandOption("Discuss", "Claude asks clarifying questions"),
                CommandOption("Build", "App is built and installed for testing"),
                CommandOption("Verify", "User tests and approves changes")
            ),
            category = CommandCategory.DEVELOPMENT,
            sourceFileName = "pilot.md",
            architecture = CommandArchitecture(
                philosophy = "Pilot keeps you in the cockpit. Unlike Ship which runs autonomously, Pilot engages in dialogue, builds the app for you to test, and waits for your approval. Changes are NOT committed until you explicitly run /land. This is pair programming with AI.",
                workflow = listOf(
                    "Create progress log (.claude/prompts/logs/pilot_NNNN_timestamp.md)",
                    "Discuss: Engage in conversation to clarify requirements",
                    "Plan: Present implementation plan and get approval",
                    "Implement: Make changes incrementally, informing user of each step",
                    "Build & Install: Compile app and deploy to device/emulator",
                    "Verify: User tests the changes and provides feedback",
                    "Iterate: If feedback requires changes, loop back to implement",
                    "Ready: When approved, status becomes PENDING_LAND",
                    "User runs /land to commit"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Conversation-First",
                        "Pilot starts with questions, not code. It asks about specific behaviors, design preferences, integration points, and priorities before writing anything."
                    ),
                    KeyBehavior(
                        "Build-Test Loop",
                        "After implementing, Pilot automatically builds and installs the app so you can test. This happens BEFORE committing, letting you iterate until satisfied."
                    ),
                    KeyBehavior(
                        "Deferred Commits",
                        "Changes remain uncommitted until you run /land. This gives you full control over when changes are 'finalized' and lets you abort without leaving partial commits."
                    ),
                    KeyBehavior(
                        "Conversation Log",
                        "Every Q&A exchange is logged in the progress file. This creates documentation of WHY decisions were made, not just what was built."
                    ),
                    KeyBehavior(
                        "Abort Safety",
                        "Say 'abort' at any time to discard all changes. Since nothing is committed, you can safely experiment."
                    )
                ),
                bestFor = listOf(
                    "Complex features requiring discussion",
                    "UI work where you need to see and approve the result",
                    "When you want to be involved in decision-making",
                    "Exploratory development where requirements evolve"
                ),
                notFor = listOf(
                    "Quick, well-defined tasks (use /ship)",
                    "Batch processing multiple tasks (use /queue + /burn)",
                    "When you're away from your computer"
                ),
                relatedCommands = listOf("/land", "/ship", "/reboot"),
                proTips = listOf(
                    "Give specific feedback: 'make the button bigger' > 'I don't like it'",
                    "Don't approve until you've actually tested on the device",
                    "Use for UI work — seeing is believing"
                )
            )
        ),

        Command(
            name = "/land",
            description = "Commit pending changes from a /pilot session.",
            icon = Icons.Default.FlightLand,
            usage = "/land [session_id]",
            examples = listOf(
                "/land",
                "/land pilot_0018_20260117",
                "/land --no-push"
            ),
            options = listOf(
                CommandOption("session_id", "Specific pilot session to land"),
                CommandOption("--no-push", "Commit without pushing")
            ),
            category = CommandCategory.DEVELOPMENT,
            sourceFileName = "land.md",
            architecture = CommandArchitecture(
                philosophy = "Land is the commit phase for Pilot sessions. It's intentionally separate — you decide WHEN to commit after testing and approval. This separation of 'building' and 'committing' gives you full control over your git history.",
                workflow = listOf(
                    "Find pending pilot session (most recent with PENDING_LAND status)",
                    "Verify changes are ready (git status matches pilot log)",
                    "Warn about additional uncommitted changes if found",
                    "Create prompt documentation file",
                    "Commit with auto-numbered message",
                    "Push to remote (unless --no-push)",
                    "Update pilot log status to LANDED",
                    "Report summary"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Automatic Session Detection",
                        "Without arguments, Land finds the most recent pilot session with PENDING_LAND status. You don't need to remember session IDs."
                    ),
                    KeyBehavior(
                        "Change Verification",
                        "Land checks that current uncommitted changes match what was tracked in the pilot session. If there are unexpected changes, it warns you."
                    ),
                    KeyBehavior(
                        "Documentation Creation",
                        "Creates a prompt file documenting the original request, conversation summary, what was implemented, and testing notes from the pilot session."
                    )
                ),
                bestFor = listOf(
                    "Committing after a successful /pilot session",
                    "When you've tested and approved changes"
                ),
                notFor = listOf(
                    "Use alone — requires a preceding /pilot session"
                ),
                relatedCommands = listOf("/pilot"),
                proTips = listOf(
                    "Always test on device before landing",
                    "Use --no-push if you want to review the commit before pushing",
                    "Check git log after to verify the commit looks correct"
                )
            )
        ),

        Command(
            name = "/flight-plan",
            description = "Strategic planning with deep brainstorming for complex features.",
            icon = Icons.Default.Map,
            usage = "/flight-plan <idea>",
            examples = listOf(
                "/flight-plan add user authentication",
                "/flight-plan --file requirements.md",
                "/flight-plan list"
            ),
            options = listOf(
                CommandOption("--file", "Plan from requirements file"),
                CommandOption("--prompt", "Plan from existing prompt"),
                CommandOption("list", "Show all flight plans"),
                CommandOption("execute", "Execute a specific plan")
            ),
            category = CommandCategory.DEVELOPMENT,
            sourceFileName = "flight-plan.md",
            architecture = CommandArchitecture(
                philosophy = "Flight Plan is for THINKING before DOING. Unlike /queue which captures quick tasks, Flight Plan engages in deep, structured conversation to fully understand requirements, edge cases, and design decisions. The result is a comprehensive plan document that can be executed later.",
                workflow = listOf(
                    "Create flight plan document (.claude/flight-plans/fp_NNNN_timestamp.md)",
                    "Understand input: parse idea, file, or prompt",
                    "Brainstorm: Guide user through discovery questions",
                    "  - Clarify the vision: What problem? Ideal end state? Primary user?",
                    "  - Define requirements: Must-have, should-have, nice-to-have, out of scope",
                    "  - Explore UX: Discovery, happy path, error handling",
                    "  - Technical considerations: Implementation approach, offline, performance",
                    "  - Edge cases & constraints: Platform-specific, security, privacy",
                    "  - Testing plan: How to verify success",
                    "Synthesize: Create technical design, implementation steps, risks",
                    "Finalize: Update status to READY with execution options"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Deep Brainstorming",
                        "Unlike quick task capture, Flight Plan explores your idea thoroughly. It asks about requirements, edge cases, user experience, technical approach, and testing — ensuring nothing is overlooked."
                    ),
                    KeyBehavior(
                        "MoSCoW Prioritization",
                        "Requirements are categorized as Must-have, Should-have, Nice-to-have, and Out of Scope. This forces clarity about what's essential vs. optional."
                    ),
                    KeyBehavior(
                        "Plan Documents Are Executable",
                        "Each plan includes execution options: single /ship, /pilot session, or step-by-step. You can run '/flight-plan execute fp_0023' to start implementation."
                    ),
                    KeyBehavior(
                        "Risk Identification",
                        "Plans include a Risks & Mitigations table. Thinking about what could go wrong before building leads to more robust implementations."
                    )
                ),
                bestFor = listOf(
                    "Complex features with many requirements",
                    "When you're unsure of the right approach",
                    "Features that affect multiple parts of the app",
                    "When you want to think before coding"
                ),
                notFor = listOf(
                    "Quick, simple tasks (use /ship or /queue)",
                    "When you just want to start building"
                ),
                relatedCommands = listOf("/queue", "/ship", "/pilot"),
                proTips = listOf(
                    "Use --file with a requirements doc for structured input",
                    "Don't rush the brainstorming — thoroughness now saves time later",
                    "Review the plan before executing — plans can be edited manually"
                )
            )
        ),

        // ═══════════════════════════════════════════════════════════════════════════
        // WORKFLOW COMMANDS
        // ═══════════════════════════════════════════════════════════════════════════

        Command(
            name = "/queue",
            description = "Save a task for later when AI is busy with something else.",
            icon = Icons.Default.Queue,
            usage = "/queue <task>",
            examples = listOf(
                "/queue add error handling",
                "/queue list",
                "/queue clear"
            ),
            options = listOf(
                CommandOption("list", "Show all pending tasks"),
                CommandOption("clear", "Remove all tasks"),
                CommandOption("pop", "Remove oldest task")
            ),
            category = CommandCategory.WORKFLOW,
            sourceFileName = "queue.md",
            architecture = CommandArchitecture(
                philosophy = "Queue is your 'don't forget' system. When Claude is working on something and you think of another task, queue it instead of interrupting. Tasks are stored in a simple markdown file and can be processed later with /burn.",
                workflow = listOf(
                    "Parse the task description from arguments",
                    "Open or create .claude/queue.md",
                    "Add task with timestamp: '- [ ] task description (queued: timestamp)'",
                    "Confirm to user with queue count"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Minimal Interruption",
                        "Queuing is fast and non-blocking. Say 'queue add tests' and immediately return to what you were doing."
                    ),
                    KeyBehavior(
                        "Simple Markdown Storage",
                        "Tasks are stored in .claude/queue.md as a checkbox list. You can view and edit this file directly."
                    ),
                    KeyBehavior(
                        "Completion Tracking",
                        "Completed tasks move to a 'Completed' section with timestamps, creating a log of what was done."
                    )
                ),
                bestFor = listOf(
                    "Capturing tasks while Claude is busy",
                    "Brainstorming and capturing ideas quickly",
                    "Building a backlog for batch processing"
                ),
                notFor = listOf(
                    "Complex features needing discussion (use /flight-plan)",
                    "Immediate execution (use /ship)"
                ),
                relatedCommands = listOf("/burn", "/ship"),
                proTips = listOf(
                    "Use queue during /pilot sessions to capture ideas without losing focus",
                    "Run /queue list to see your backlog before a /burn",
                    "Keep tasks small and specific for better /burn results"
                )
            )
        ),

        Command(
            name = "/burn",
            description = "Execute all queued tasks sequentially, like a rocket burn.",
            icon = Icons.Default.Whatshot,
            usage = "/burn",
            examples = listOf(
                "/burn",
                "/burn --dry-run",
                "/burn --first"
            ),
            options = listOf(
                CommandOption("--dry-run", "Preview without executing"),
                CommandOption("--first", "Execute only first task"),
                CommandOption("--skip-failed", "Auto-skip failed tasks")
            ),
            category = CommandCategory.WORKFLOW,
            sourceFileName = "burn.md",
            architecture = CommandArchitecture(
                philosophy = "Burn is your batch processor. It takes all queued tasks and executes them sequentially using the Ship workflow. Each task gets its own commit. Like a rocket burn that propels you through your backlog, it clears the queue efficiently.",
                workflow = listOf(
                    "Create progress log (.claude/prompts/logs/burn_timestamp.md)",
                    "Read .claude/queue.md and parse pending tasks",
                    "Display tasks and confirm execution",
                    "For each task:",
                    "  - Log: 'BURNING: task description'",
                    "  - Execute using Ship workflow (implement, document, commit)",
                    "  - Mark task complete in queue.md",
                    "  - Save progress log (enables resumption)",
                    "Handle failures: Ask to skip or abort",
                    "Finalize progress log with summary",
                    "Report completion statistics"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Sequential Execution with Ship Workflow",
                        "Each task runs through the full Ship workflow: implement changes, create documentation, commit. This ensures consistency and traceability."
                    ),
                    KeyBehavior(
                        "Individual Commits",
                        "Each task gets its own numbered commit. This makes it easy to revert individual changes if needed."
                    ),
                    KeyBehavior(
                        "Resumable Progress",
                        "Like Ship, Burn creates a progress log. If Claude disconnects mid-burn, /reboot can continue from the interrupted task."
                    ),
                    KeyBehavior(
                        "Failure Handling",
                        "If a task fails, Burn asks whether to skip and continue or abort. With --skip-failed, it automatically skips failures."
                    )
                ),
                bestFor = listOf(
                    "Processing a backlog of small tasks",
                    "End-of-day cleanup of accumulated tasks",
                    "Batch implementation of related changes"
                ),
                notFor = listOf(
                    "Complex tasks needing discussion (use /pilot for each)",
                    "When tasks have dependencies on each other"
                ),
                relatedCommands = listOf("/queue", "/ship", "/reboot"),
                proTips = listOf(
                    "Run --dry-run first to preview what will be executed",
                    "Keep queued tasks independent — dependencies can cause issues",
                    "Review commits after burn to verify everything looks correct"
                )
            )
        ),

        Command(
            name = "/reboot",
            description = "Resume interrupted work from progress logs.",
            icon = Icons.Default.Refresh,
            usage = "/reboot [session_id]",
            examples = listOf(
                "/reboot",
                "/reboot ship_0015_20260117",
                "/reboot --list"
            ),
            options = listOf(
                CommandOption("--list", "List all interrupted sessions"),
                CommandOption("--clean", "Mark stale sessions as abandoned")
            ),
            category = CommandCategory.WORKFLOW,
            sourceFileName = "reboot.md",
            architecture = CommandArchitecture(
                philosophy = "Reboot is your safety net. AI sessions can be interrupted — network issues, timeouts, crashes. Because Ship, Burn, and Pilot all create detailed progress logs, Reboot can analyze what was completed and resume from exactly where work stopped. No lost progress.",
                workflow = listOf(
                    "Find session to resume:",
                    "  - If session_id provided, find that log",
                    "  - Otherwise, find most recent IN_PROGRESS or INTERRUPTED log",
                    "  - If multiple found, show list and ask user to choose",
                    "Read progress log and extract:",
                    "  - Session type (ship, burn, pilot)",
                    "  - Original request",
                    "  - Completed phases/tasks",
                    "  - Remaining work",
                    "Verify current state matches snapshot (branch, uncommitted changes)",
                    "Display summary and confirm continuation",
                    "Resume from incomplete phase/task",
                    "Continue logging until completion"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Automatic Session Detection",
                        "Without arguments, Reboot finds the most recent interrupted session. It looks for logs with IN_PROGRESS or INTERRUPTED status."
                    ),
                    KeyBehavior(
                        "State Verification",
                        "Before resuming, Reboot checks that you're on the same git branch and warns about any discrepancies. This prevents resuming in an inconsistent state."
                    ),
                    KeyBehavior(
                        "Type-Aware Resumption",
                        "Reboot understands different session types (ship, burn, pilot) and hands off to the appropriate command's workflow."
                    ),
                    KeyBehavior(
                        "Progress Preservation",
                        "Work completed before interruption is preserved. Reboot doesn't re-do completed phases — it picks up exactly where Claude stopped."
                    )
                ),
                bestFor = listOf(
                    "Resuming after network disconnection",
                    "Continuing after Claude timeout",
                    "Recovering from unexpected session end"
                ),
                notFor = listOf(
                    "Starting new work (use /ship, /pilot, etc.)",
                    "When you want to abandon the previous session"
                ),
                relatedCommands = listOf("/ship", "/burn", "/pilot"),
                proTips = listOf(
                    "Run /reboot --list to see all interrupted sessions",
                    "Use /reboot --clean to mark old sessions as abandoned",
                    "Check the progress log if you want to see exactly what was done"
                )
            )
        ),

        // ═══════════════════════════════════════════════════════════════════════════
        // BUILD & DEPLOY COMMANDS
        // ═══════════════════════════════════════════════════════════════════════════

        Command(
            name = "/build",
            description = "Compile the Android app.",
            icon = Icons.Default.Construction,
            usage = "/build [clean]",
            examples = listOf(
                "/build",
                "/build clean"
            ),
            options = listOf(
                CommandOption("clean", "Clean build from scratch")
            ),
            category = CommandCategory.BUILD,
            sourceFileName = "build.md",
            architecture = CommandArchitecture(
                philosophy = "Build is the simplest compile command. It runs Gradle's assembleDebug task and reports success or failure with helpful error analysis. Use it to verify code compiles without deploying.",
                workflow = listOf(
                    "Set JAVA_HOME to Android Studio's JBR",
                    "Run gradlew assembleDebug (or clean assembleDebug if 'clean' specified)",
                    "Check for BUILD SUCCESSFUL or BUILD FAILED",
                    "Report build time and APK location on success",
                    "Analyze and display errors on failure with fix suggestions"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Error Analysis",
                        "On failure, Build identifies the file, line number, and error type. It suggests fixes for common issues."
                    ),
                    KeyBehavior(
                        "Clean Option",
                        "Use 'clean' when you suspect cached build artifacts are causing issues. It deletes build outputs before compiling."
                    )
                ),
                bestFor = listOf(
                    "Verifying code compiles after changes",
                    "Debugging build errors",
                    "CI/CD verification"
                ),
                notFor = listOf(
                    "Running the app (use /run)",
                    "Installing to device (use /install)"
                ),
                relatedCommands = listOf("/install", "/run", "/release"),
                proTips = listOf(
                    "Use /build after refactoring to catch compile errors quickly",
                    "Try /build clean if you get strange caching issues"
                )
            )
        ),

        Command(
            name = "/install",
            description = "Build and install to connected device or emulator.",
            icon = Icons.Default.InstallMobile,
            usage = "/install",
            examples = listOf("/install"),
            category = CommandCategory.BUILD,
            sourceFileName = "install.md",
            architecture = CommandArchitecture(
                philosophy = "Install builds and deploys in one step. It verifies a device is connected, runs Gradle's installDebug task, and reports results. Use when you want to deploy but not automatically launch.",
                workflow = listOf(
                    "Run 'adb devices' to verify device connection",
                    "If no device, inform user and stop",
                    "Set JAVA_HOME to Android Studio's JBR",
                    "Run gradlew installDebug",
                    "Report success with device name, or failure with error details"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Device Check",
                        "Before building, Install verifies a device/emulator is connected. This prevents wasted build time."
                    ),
                    KeyBehavior(
                        "Install Error Handling",
                        "If install fails (e.g., app already installed with different signature), suggests fixes like uninstalling first."
                    )
                ),
                bestFor = listOf(
                    "Deploying to device without launching",
                    "When you want to manually launch specific activities"
                ),
                notFor = listOf(
                    "Full workflow (use /run)",
                    "Build-only verification (use /build)"
                ),
                relatedCommands = listOf("/build", "/run"),
                proTips = listOf(
                    "Start emulator before running /install",
                    "If install fails with signature mismatch, run: adb uninstall com.example.yourapp"
                )
            )
        ),

        Command(
            name = "/run",
            description = "Build, install, and launch the app.",
            icon = Icons.Default.PlayArrow,
            usage = "/run",
            examples = listOf("/run"),
            category = CommandCategory.BUILD,
            sourceFileName = "run.md",
            architecture = CommandArchitecture(
                philosophy = "Run is the complete development cycle command. It builds, installs, AND launches the app — one command to see your changes running. This is the command you'll use most during active development.",
                workflow = listOf(
                    "Run 'adb devices' to verify device connection",
                    "If no device, inform user and stop",
                    "Set JAVA_HOME to Android Studio's JBR",
                    "Run gradlew installDebug",
                    "If build/install fails, report error and stop",
                    "Launch app: adb shell am start -n {package}/.MainActivity",
                    "Report complete success with timing"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Full Pipeline",
                        "Build → Install → Launch in one command. No need to remember multiple steps."
                    ),
                    KeyBehavior(
                        "Automatic Launch",
                        "Uses ADB to start the MainActivity, bringing your app to the foreground immediately."
                    ),
                    KeyBehavior(
                        "Timing Information",
                        "Reports build time so you can track how long compilation takes."
                    )
                ),
                bestFor = listOf(
                    "Active development — see changes immediately",
                    "Testing after making code changes",
                    "The most common build command"
                ),
                notFor = listOf(
                    "Just checking if code compiles (use /build)",
                    "Release builds (use /release)"
                ),
                relatedCommands = listOf("/build", "/install", "/release"),
                proTips = listOf(
                    "Keep your emulator running for faster iteration",
                    "Use /run after every significant change to verify"
                )
            )
        ),

        Command(
            name = "/release",
            description = "Build, version, and publish a signed release APK.",
            icon = Icons.Default.Publish,
            usage = "/release [version]",
            examples = listOf(
                "/release",
                "/release patch",
                "/release minor",
                "/release 2.0.0"
            ),
            options = listOf(
                CommandOption("patch", "Bump patch version: 1.0.0 → 1.0.1"),
                CommandOption("minor", "Bump minor version: 1.0.0 → 1.1.0"),
                CommandOption("major", "Bump major version: 1.0.0 → 2.0.0"),
                CommandOption("X.Y.Z", "Set specific version number")
            ),
            category = CommandCategory.BUILD,
            sourceFileName = "release.md",
            architecture = CommandArchitecture(
                philosophy = "Release is your complete release management system. It handles versioning, signing, building, storing artifacts, updating changelog, and committing — everything needed to publish. APKs are stored in .claude/releases/ with semantic versioning.",
                workflow = listOf(
                    "Check keystore setup (.claude/android-keystore/)",
                    "If missing, guide user through keystore creation/configuration",
                    "Determine version (current, patch/minor/major bump, or specific)",
                    "Verify Gradle has signingConfigs block, add if missing",
                    "Update versionName and versionCode in build.gradle.kts",
                    "Run gradlew assembleRelease",
                    "Copy APK to .claude/releases/{app}-v{version}.apk",
                    "Update VERSION file and CHANGELOG.md",
                    "Commit all changes with release commit",
                    "Create git tag v{version}",
                    "Push commits and tags"
                ),
                keyBehaviors = listOf(
                    KeyBehavior(
                        "Guided Keystore Setup",
                        "If no keystore is configured, Release walks you through creating one or configuring an existing one. You only need to do this once."
                    ),
                    KeyBehavior(
                        "Semantic Versioning",
                        "Use 'patch' for bug fixes, 'minor' for new features, 'major' for breaking changes. Release handles the version math."
                    ),
                    KeyBehavior(
                        "Artifact Storage",
                        "Release APKs are stored in .claude/releases/ with versioned filenames. All artifacts are tracked in git for reproducibility."
                    ),
                    KeyBehavior(
                        "Automatic Changelog",
                        "Prompts for release notes and updates CHANGELOG.md with proper formatting and date."
                    ),
                    KeyBehavior(
                        "Git Tags",
                        "Creates annotated git tags (v1.0.0, v1.1.0, etc.) for easy release tracking."
                    )
                ),
                bestFor = listOf(
                    "Creating release builds for distribution",
                    "Play Store submissions",
                    "Sharing APKs with testers"
                ),
                notFor = listOf(
                    "Development testing (use /run)",
                    "Quick iteration (use /build)"
                ),
                relatedCommands = listOf("/build", "/run"),
                proTips = listOf(
                    "Back up your keystore file — losing it means you can't update your Play Store app",
                    "Use 'patch' for small bug fixes between releases",
                    "Always test the release APK on a real device before publishing"
                )
            )
        )
    )

    fun getByCategory(category: CommandCategory): List<Command> {
        return commands.filter { it.category == category }
    }

    fun getByName(name: String): Command? {
        return commands.find { it.name == name }
    }
}
