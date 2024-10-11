package com.tictactoeapp.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tictactoeapp.game.ui.theme.MediumTurquoise

@Composable
fun ModeSelectionScreen(navController: NavController) {
    Box(){

       Background()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tic Tac Toe",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Choose play mode",
                style = textStyle
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                modifier = Modifier.width(200.dp),
                onClick = { navController.navigate("side_selection") }
            ) {
                Text("Play with AI" ,style= textStyle)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.width(200.dp),
                onClick = { navController.navigate("name_input/friend") }
            ) {
                Text("With a friend", style= textStyle)
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                modifier = Modifier.width(200.dp),
                onClick = { navController.navigate("instructions") },
                colors =  ButtonDefaults.buttonColors(MediumTurquoise)
            ) {
                Text("Instructions", style= textStyle)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModeSelectionPreview(){

}