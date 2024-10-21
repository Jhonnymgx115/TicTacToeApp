package com.tictactoeapp.game

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tictactoeapp.game.ui.theme.bebasNeueFamily

@Composable
fun NameInputScreen(navController: NavController, mode: String) {
    var player1Name by remember { mutableStateOf("") }
    var player2Name by remember { mutableStateOf("") }

    Box(){
        Background()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = stringResource(R.string.enter_player_names),
                style = gameText.copy(textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = player1Name,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.close_bold_svgrepo_com),
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Player1",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                onValueChange = { player1Name = it },
                label = { Text(stringResource(R.string.player_1)) }
            )
            Spacer(modifier = Modifier.height(46.dp))
            if (mode == "friend") {
                OutlinedTextField(
                    value = player2Name,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.circle_svgrepo_com),
                            modifier = Modifier.size(30.dp),
                            contentDescription = "Player2",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    },
                    onValueChange = { player2Name = it },
                    label = { Text(stringResource(R.string.player_2)) }
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
                Text(stringResource(R.string.start_game), style= textStyle)
            }
        }
    }
}