package com.example.launchpad.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Appearance Section
            item {
                SettingsSectionHeader(title = "Appearance")
            }

            item {
                SettingsToggleItem(
                    icon = Icons.Default.DarkMode,
                    title = "Dark Theme",
                    description = "Use dark color scheme",
                    isChecked = isDarkTheme,
                    onToggle = onThemeToggle
                )
            }

            // Development Section
            item {
                SettingsSectionHeader(title = "Development")
            }

            item {
                SettingsInfoItem(
                    icon = Icons.Default.Code,
                    title = "Package Name",
                    value = "com.example.launchpad"
                )
            }

            item {
                SettingsInfoItem(
                    icon = Icons.Default.Android,
                    title = "Min SDK",
                    value = "API 24 (Android 7.0)"
                )
            }

            item {
                SettingsInfoItem(
                    icon = Icons.Default.Update,
                    title = "Target SDK",
                    value = "API 36"
                )
            }

            // Claude Code Section
            item {
                SettingsSectionHeader(title = "Claude Code Integration")
            }

            item {
                SettingsInfoItem(
                    icon = Icons.Default.Terminal,
                    title = "Commands Available",
                    value = "7 commands"
                )
            }

            item {
                SettingsInfoItem(
                    icon = Icons.Default.AutoAwesome,
                    title = "Skills Available",
                    value = "3 skills"
                )
            }

            item {
                SettingsLinkItem(
                    icon = Icons.Default.Folder,
                    title = "Context Directory",
                    value = ".claude/context/"
                )
            }

            item {
                SettingsLinkItem(
                    icon = Icons.Default.Description,
                    title = "Prompts Directory",
                    value = ".claude/prompts/"
                )
            }

            // About Section
            item {
                SettingsSectionHeader(title = "About")
            }

            item {
                SettingsInfoItem(
                    icon = Icons.Default.Info,
                    title = "Version",
                    value = "1.0.0"
                )
            }

            item {
                SettingsInfoItem(
                    icon = Icons.Default.Build,
                    title = "Build Type",
                    value = "Debug"
                )
            }

            item {
                SettingsLinkItem(
                    icon = Icons.Default.OpenInNew,
                    title = "Source Code",
                    value = "View on GitHub"
                )
            }
        }
    }
}

@Composable
fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun SettingsToggleItem(
    icon: ImageVector,
    title: String,
    description: String,
    isChecked: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onToggle)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Switch(
                checked = isChecked,
                onCheckedChange = { onToggle() }
            )
        }
    }
}

@Composable
fun SettingsInfoItem(
    icon: ImageVector,
    title: String,
    value: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun SettingsLinkItem(
    icon: ImageVector,
    title: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Open link */ },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
        }
    }
}
