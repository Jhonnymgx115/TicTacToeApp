package com.tictactoeapp.game

import InstructionsScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tictactoeapp.game.ui.theme.TicTacToeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph()
                }
            }
        }
    }
}

@Composable
fun SetupNavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable("mode_selection") {
            ModeSelectionScreen(navController)
        }
        composable(
            "name_input/{mode}",
            arguments = listOf(navArgument("mode") { type = NavType.StringType })
        )
        { backStackEntry ->
            val mode = backStackEntry.arguments?.getString("mode") ?: "friend"
            NameInputScreen(navController, mode)
        }
        composable(
            "game_board/{mode}/{player1Name}/{player2Name}",
            arguments = listOf(
                navArgument("mode") { type = NavType.StringType },
                navArgument("player1Name") { type = NavType.StringType },
                navArgument("player2Name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val mode = backStackEntry.arguments?.getString("mode") ?: "friend"
            val player1Name = backStackEntry.arguments?.getString("player1Name") ?: "Player 1"
            val player2Name = backStackEntry.arguments?.getString("player2Name") ?: "Player 2"
            GameBoardScreen(navController, mode, player1Name, player2Name)
        }
        composable("instructions") {
            InstructionsScreen(navController)
        }
        composable("about"){
            AboutView(navController)
        }
    }
}