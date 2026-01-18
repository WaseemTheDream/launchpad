package com.example.launchpad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.launchpad.ui.navigation.LaunchpadBottomNavBar
import com.example.launchpad.ui.navigation.LaunchpadNavGraph
import com.example.launchpad.ui.theme.LaunchpadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemDarkTheme = isSystemInDarkTheme()
            var isDarkTheme by remember { mutableStateOf(systemDarkTheme) }
            val navController = rememberNavController()

            LaunchpadTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        LaunchpadBottomNavBar(navController = navController)
                    }
                ) { innerPadding ->
                    LaunchpadNavGraph(
                        navController = navController,
                        onThemeToggle = { isDarkTheme = !isDarkTheme },
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
