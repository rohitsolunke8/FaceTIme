package com.example.facetime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.facetime.ui.theme.FaceTImeTheme
import com.example.facetime.ui_layer.home.HomeScreen
import com.example.facetime.ui_layer.login.LoginScreen
import com.example.facetime.ui_layer.routes.home
import com.example.facetime.ui_layer.routes.login
import com.example.facetime.ui_layer.routes.signup
import com.example.facetime.ui_layer.signup.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaceTImeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        Modifier.padding(innerPadding)
                    ) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = login) {
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