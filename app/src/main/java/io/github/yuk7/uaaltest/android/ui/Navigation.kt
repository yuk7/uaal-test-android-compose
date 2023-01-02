package io.github.yuk7.uaaltest.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.yuk7.uaaltest.android.ui.blue.BlueScreen
import io.github.yuk7.uaaltest.android.ui.top.TopScreen
import io.github.yuk7.uaaltest.android.ui.yellow.YellowScreen

enum class Screen {
    TOP,
    BLUE,
    YELLOW
}

fun NavController.navigateToBlue() {
    navigate(Screen.BLUE.name)
}

fun NavController.navigateToYellow() {
    navigate(Screen.YELLOW.name)
}

@Composable
fun InitializeRoutes(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = Screen.TOP.name) {
        composable(Screen.TOP.name) { TopScreen(navHostController) }
        composable(Screen.BLUE.name) { BlueScreen(navHostController) }
        composable(Screen.YELLOW.name) { YellowScreen(navHostController) }
    }
}