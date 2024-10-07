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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ModeSelectionScreen(navController: NavController) {
    Box(){

        Column(

        ){
            Row(){
                Image(
                    painter = painterResource(id = R.drawable.close_bold_svgrepo_com),
                    contentDescription = "X" ,
                    modifier = Modifier
                        .size(200.dp)
                        .offset((170).dp, 90.dp)
                        .rotate(-17.61f)
                        .alpha(0.2f)
                )
                Image(
                    painter = painterResource(id = R.drawable.circle_svgrepo_com),
                    contentDescription = "X",
                    modifier = Modifier
                        .size(250.dp)
                        .offset((-230).dp, (-30).dp)
                        .alpha(0.2f)
                )
            }
            Row(){
                Image(
                    painter = painterResource(id = R.drawable.close_bold_svgrepo_com),
                    contentDescription = "X" ,
                    modifier = Modifier.size(290.dp)
                        .offset((-70).dp, 30.dp)
                        .rotate(24.6f)
                        .alpha(0.2f)
                )
                Image(
                    painter = painterResource(id = R.drawable.circle_svgrepo_com),
                    contentDescription = "X",
                    modifier = Modifier
                        .size(160.dp)
                        .offset(0.dp, 50.dp)
                        .alpha(0.2f)
                )
            }
            Row{
                Image(
                    painter = painterResource(id = R.drawable.circle_svgrepo_com),
                    contentDescription = "X",
                    modifier = Modifier
                        .size(250.dp)
                        .offset((0).dp, (30).dp)
                        .alpha(0.2f)
                )
                Image(
                    painter = painterResource(id = R.drawable.close_bold_svgrepo_com),
                    contentDescription = "X" ,
                    modifier = Modifier
                        .size(200.dp)
                        .offset((0).dp, 0.dp)
                        .rotate(-17.61f)
                        .alpha(0.2f)
                )
            }
        }

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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModeSelectionPreview(){

}