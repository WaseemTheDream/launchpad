package com.example.launchpad.ui.screens.guides

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.launchpad.data.model.GuideCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideDetailScreen(
    category: GuideCategory,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = category.color.copy(alpha = 0.1f)
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // Hero Section
            item {
                GuideHeroSection(category = category)
            }

            // Overview
            item {
                SectionHeader(title = "Overview")
                Text(
                    text = getOverviewText(category),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }

            // Key Features
            item {
                SectionHeader(title = "Key Features to Include")
            }

            items(getKeyFeatures(category)) { feature ->
                FeatureItem(feature = feature)
            }

            // Architecture Tips
            item {
                SectionHeader(title = "Architecture Tips")
            }

            items(getArchitectureTips(category)) { tip ->
                TipItem(tip = tip)
            }

            // Example Prompts
            item {
                SectionHeader(title = "Try These Prompts")
            }

            items(getExamplePrompts(category)) { prompt ->
                PromptCard(prompt = prompt)
            }

            // Example Apps
            item {
                SectionHeader(title = "Example Apps in This Category")
                ExampleAppsRow(examples = category.examples)
            }
        }
    }
}

@Composable
fun GuideHeroSection(category: GuideCategory) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        category.color,
                        category.color.copy(alpha = 0.7f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = category.description,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 12.dp)
    )
}

@Composable
fun FeatureItem(feature: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = feature,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun TipItem(tip: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.Lightbulb,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = tip,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun PromptCard(prompt: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Terminal,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = prompt,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { /* Copy to clipboard */ }) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copy",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun ExampleAppsRow(examples: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        examples.forEach { example ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    text = example,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

// Content helpers
private fun getOverviewText(category: GuideCategory): String {
    return when (category) {
        GuideCategory.UTILITY -> "Utility apps solve everyday problems with focused, single-purpose functionality. They're often the easiest apps to build and perfect for learning mobile development. Focus on doing one thing exceptionally well."
        GuideCategory.GAMES -> "Games bring joy and entertainment to users. From simple casual games to complex puzzles, game development teaches you animation, state management, and user engagement. Start simple and iterate."
        GuideCategory.ECOMMERCE -> "E-commerce apps enable buying and selling. They require product catalogs, shopping carts, and checkout flows. Learn about lists, state management, and user flows."
        GuideCategory.SOCIAL -> "Social apps connect people. They feature user profiles, feeds, messaging, and notifications. Master data synchronization and real-time updates."
        GuideCategory.PRODUCTIVITY -> "Productivity apps help users accomplish goals. Task managers, calendars, and habit trackers teach persistence, scheduling, and state management."
        GuideCategory.CREATIVE -> "Creative tools empower expression. Drawing apps, photo editors, and music tools require canvas work, gesture handling, and media manipulation."
    }
}

private fun getKeyFeatures(category: GuideCategory): List<String> {
    return when (category) {
        GuideCategory.UTILITY -> listOf(
            "Clean, focused UI with minimal distractions",
            "Fast launch time and responsive interactions",
            "Offline-first functionality",
            "Simple data persistence",
            "Clear visual feedback for actions"
        )
        GuideCategory.GAMES -> listOf(
            "Game loop and state management",
            "Animations and visual effects",
            "Score tracking and persistence",
            "Sound effects and haptic feedback",
            "Difficulty progression"
        )
        GuideCategory.ECOMMERCE -> listOf(
            "Product catalog with search and filters",
            "Shopping cart with quantity management",
            "User authentication and profiles",
            "Checkout flow with form validation",
            "Order history and tracking"
        )
        GuideCategory.SOCIAL -> listOf(
            "User registration and profiles",
            "Content feed with pull-to-refresh",
            "Like, comment, and share actions",
            "Direct messaging",
            "Push notifications"
        )
        GuideCategory.PRODUCTIVITY -> listOf(
            "Task creation and management",
            "Due dates and reminders",
            "Categories and tags",
            "Progress tracking",
            "Data backup and sync"
        )
        GuideCategory.CREATIVE -> listOf(
            "Canvas for drawing/editing",
            "Tool palette and settings",
            "Undo/redo functionality",
            "Export and sharing",
            "Gallery for saved work"
        )
    }
}

private fun getArchitectureTips(category: GuideCategory): List<String> {
    return when (category) {
        GuideCategory.UTILITY -> listOf(
            "Use ViewModel for calculation state",
            "Consider Room for history/settings",
            "Keep the UI layer thin and focused"
        )
        GuideCategory.GAMES -> listOf(
            "Separate game logic from UI",
            "Use coroutines for game loops",
            "Consider a state machine for game states"
        )
        GuideCategory.ECOMMERCE -> listOf(
            "Use Repository pattern for products",
            "Implement proper cart state management",
            "Consider offline-first with Room + API"
        )
        GuideCategory.SOCIAL -> listOf(
            "Plan your data models carefully",
            "Implement proper authentication flow",
            "Use pagination for feeds"
        )
        GuideCategory.PRODUCTIVITY -> listOf(
            "Use Room for local persistence",
            "Implement proper date/time handling",
            "Consider WorkManager for reminders"
        )
        GuideCategory.CREATIVE -> listOf(
            "Use Canvas for custom drawing",
            "Implement command pattern for undo/redo",
            "Consider file storage for exports"
        )
    }
}

private fun getExamplePrompts(category: GuideCategory): List<String> {
    return when (category) {
        GuideCategory.UTILITY -> listOf(
            "/ship add a calculator screen",
            "/ship create a unit converter",
            "/ship build a tip calculator"
        )
        GuideCategory.GAMES -> listOf(
            "/ship create a trivia game",
            "/ship add a puzzle screen",
            "/ship build a memory card game"
        )
        GuideCategory.ECOMMERCE -> listOf(
            "/ship add a product list screen",
            "/ship create a shopping cart",
            "/ship build a checkout flow"
        )
        GuideCategory.SOCIAL -> listOf(
            "/ship add a user profile screen",
            "/ship create a feed with posts",
            "/ship build a messaging screen"
        )
        GuideCategory.PRODUCTIVITY -> listOf(
            "/ship add a task list screen",
            "/ship create a calendar view",
            "/ship build a habit tracker"
        )
        GuideCategory.CREATIVE -> listOf(
            "/ship add a drawing canvas",
            "/ship create a color picker",
            "/ship build a photo filter screen"
        )
    }
}
