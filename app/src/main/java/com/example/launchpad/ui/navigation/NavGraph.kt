package com.example.launchpad.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.launchpad.data.model.GuideCategory
import com.example.launchpad.ui.screens.about.AboutScreen
import com.example.launchpad.ui.screens.commands.CommandsScreen
import com.example.launchpad.ui.screens.guides.GuideDetailScreen
import com.example.launchpad.ui.screens.guides.GuidesScreen
import com.example.launchpad.ui.screens.home.HomeScreen
import com.example.launchpad.ui.screens.settings.SettingsScreen

@Composable
fun LaunchpadNavGraph(
    navController: NavHostController,
    onThemeToggle: () -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                initialOffsetX = { 30 },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
                targetOffsetX = { 30 },
                animationSpec = tween(300)
            )
        }
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onCategoryClick = { category ->
                    navController.navigate(Screen.GuideDetail.createRoute(category.name))
                },
                onNavigateToGuides = {
                    navController.navigate(Screen.Guides.route)
                }
            )
        }

        composable(Screen.Guides.route) {
            GuidesScreen(
                onCategoryClick = { category ->
                    navController.navigate(Screen.GuideDetail.createRoute(category.name))
                }
            )
        }

        composable(
            route = Screen.GuideDetail.route,
            arguments = listOf(
                navArgument("categoryId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            val category = try {
                GuideCategory.valueOf(categoryId)
            } catch (e: Exception) {
                GuideCategory.UTILITY
            }
            GuideDetailScreen(
                category = category,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Commands.route) {
            CommandsScreen()
        }

        composable(Screen.About.route) {
            AboutScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle
            )
        }
    }
}
