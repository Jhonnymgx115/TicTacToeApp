package com.tictactoeapp.game

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NameInputScreen(navController: NavController, mode: String) {
    var player1Name by remember { mutableStateOf("") }
    var player2Name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter player names",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = player1Name,
            onValueChange = { player1Name = it },
            label = { Text("Player 1 Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (mode == "friend") {
            OutlinedTextField(
                value = player2Name,
                onValueChange = { player2Name = it },
                label = { Text("Player 2 Name") }
            )
        } else {
            player2Name = "AI"
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                navController.navigate("game_board/$mode/${player1Name.ifEmpty { "Player 1" }}/${player2Name.ifEmpty { "Player 2" }}")
            }
        ) {
            Text("Start Game")
        }
    }
}