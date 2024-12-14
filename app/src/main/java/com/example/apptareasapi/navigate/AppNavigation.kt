package com.example.apptareasapi.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apptareasapi.Home.HomeScreen
import com.example.apptareasapi.clases.User
import com.example.apptareasapi.login.LoginScreen
import com.example.apptareasapi.login.SignupScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home")
}

@Composable
fun AppNavigation(onHomeNavigate: (User, String) -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { user, token ->
                    onHomeNavigate(user, token)
                },
                onNavigateToSignup = {
                    navController.navigate(Screen.Signup.route)
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                viewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            )
        }

        composable(Screen.Signup.route) {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Signup.route) { inclusive = true }
                    }
                },
                viewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            )
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}
