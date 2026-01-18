package com.example.launchpad.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.launchpad.ui.theme.*

enum class GuideCategory(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val examples: List<String>
) {
    UTILITY(
        title = "Utility Apps",
        description = "Practical tools that solve everyday problems",
        icon = Icons.Default.Build,
        color = CategoryUtility,
        examples = listOf("Calculator", "Timer", "Unit Converter", "Notes", "QR Scanner")
    ),
    GAMES(
        title = "Games",
        description = "Fun and engaging entertainment apps",
        icon = Icons.Default.SportsEsports,
        color = CategoryGames,
        examples = listOf("Trivia", "Puzzle", "Card Games", "Casual", "Word Games")
    ),
    ECOMMERCE(
        title = "E-Commerce",
        description = "Shopping and marketplace applications",
        icon = Icons.Default.ShoppingCart,
        color = CategoryEcommerce,
        examples = listOf("Product Catalog", "Shopping Cart", "Checkout", "Order Tracking")
    ),
    SOCIAL(
        title = "Social Apps",
        description = "Connect and communicate with others",
        icon = Icons.Default.People,
        color = CategorySocial,
        examples = listOf("Feed", "Messaging", "Profiles", "Notifications", "Stories")
    ),
    PRODUCTIVITY(
        title = "Productivity",
        description = "Get things done more efficiently",
        icon = Icons.Default.CheckCircle,
        color = CategoryProductivity,
        examples = listOf("Task Manager", "Calendar", "Reminders", "Habit Tracker")
    ),
    CREATIVE(
        title = "Creative Tools",
        description = "Express yourself through art and media",
        icon = Icons.Default.Palette,
        color = CategoryCreative,
        examples = listOf("Drawing", "Photo Editor", "Music", "Video", "Design")
    )
}

data class Guide(
    val category: GuideCategory,
    val overview: String,
    val keyFeatures: List<String>,
    val architectureTips: List<String>,
    val examplePrompts: List<String>,
    val codeSnippet: String? = null
)
