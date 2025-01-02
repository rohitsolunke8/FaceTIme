package com.example.facetime.ui_layer.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.facetime.ui_layer.home.HomeScreen
import com.example.facetime.ui_layer.login.LoginScreen
import com.example.facetime.ui_layer.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val currentUser = FirebaseAuth.getInstance().currentUser

    val navController = rememberNavController()

    val destiny = if (currentUser != null) {
        home
    } else {
        login
    }

    NavHost(navController = navController, startDestination = destiny) {
        composable<login> {
            LoginScreen(navController)
        }
        composable<signup> {
            SignUpScreen(navController)
        }
        composable<home> {
            HomeScreen(navController)
        }
    }
}