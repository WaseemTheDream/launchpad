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
    val category: CommandCategory
)

data class CommandOption(
    val flag: String,
    val description: String
)

enum class CommandCategory(val title: String) {
    DEVELOPMENT("Development"),
    BUILD("Build & Deploy"),
    WORKFLOW("Workflow")
}

object CommandRepository {
    val commands = listOf(
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
            category = CommandCategory.DEVELOPMENT
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
            category = CommandCategory.DEVELOPMENT
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
            category = CommandCategory.DEVELOPMENT
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
            category = CommandCategory.DEVELOPMENT
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
            category = CommandCategory.DEVELOPMENT
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
            category = CommandCategory.DEVELOPMENT
        ),
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
            category = CommandCategory.WORKFLOW
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
            category = CommandCategory.WORKFLOW
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
            category = CommandCategory.WORKFLOW
        ),
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
            category = CommandCategory.BUILD
        ),
        Command(
            name = "/install",
            description = "Build and install to connected device or emulator.",
            icon = Icons.Default.InstallMobile,
            usage = "/install",
            examples = listOf("/install"),
            category = CommandCategory.BUILD
        ),
        Command(
            name = "/run",
            description = "Build, install, and launch the app.",
            icon = Icons.Default.PlayArrow,
            usage = "/run",
            examples = listOf("/run"),
            category = CommandCategory.BUILD
        )
    )

    fun getByCategory(category: CommandCategory): List<Command> {
        return commands.filter { it.category == category }
    }
}
