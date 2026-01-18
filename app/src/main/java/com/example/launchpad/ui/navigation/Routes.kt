package com.example.launchpad.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Guides : Screen("guides")
    object GuideDetail : Screen("guide/{categoryId}") {
        fun createRoute(categoryId: String) = "guide/$categoryId"
    }
    object Commands : Screen("commands")
    object About : Screen("about")
    object Settings : Screen("settings")
}

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val iconName: String
) {
    object Home : BottomNavItem("home", "Home", "home")
    object Guides : BottomNavItem("guides", "Guides", "menu_book")
    object Commands : BottomNavItem("commands", "Commands", "terminal")
    object About : BottomNavItem("about", "About", "info")
    object Settings : BottomNavItem("settings", "Settings", "settings")
}
