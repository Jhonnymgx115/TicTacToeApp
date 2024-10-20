package com.tictactoeapp.game


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tictactoeapp.game.ui.theme.bebasNeueFamily
import com.tictactoeapp.game.ui.theme.interFamily

val emailStyle = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    letterSpacing = 1.sp,
    fontSize = 17.sp
)

@Composable
fun AboutView(navController: NavController){

    Box(){
        Background()

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MyTopAppBar(navController)

            Spacer(modifier = Modifier.height(80.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.tic_tac_toe),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("TRIQUI",
                    color= Color.Black,
                    style = TextStyle(
                        fontFamily = bebasNeueFamily,
                        fontSize = 80.sp,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text("Desarrollado por: ", style = TextStyle(
                fontFamily = bebasNeueFamily,
                fontSize = 30.sp,
                letterSpacing = 1.sp
            ))

            Spacer(modifier = Modifier.height(50.dp))

            Column(){
                Row(){
                    Image(
                        painter = painterResource(R.drawable.right_arrow_svgrepo_com),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text("María Paula Flórez Vargas", style = textStyle)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text("       mariap.florezv@upb.edu.co",
                    color = Color.Black,
                    style = emailStyle
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(){
                    Image(
                        painter = painterResource(R.drawable.right_arrow_svgrepo_com),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text("Camilo Hernández Romero", style= textStyle)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text("       camilo.hernandez@upb.edu.co",
                    color = Color.Black,
                    style = emailStyle
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(){
                    Image(
                        painter = painterResource(R.drawable.right_arrow_svgrepo_com),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text("Jaider Joham Morales Franco", style= textStyle)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text("       jaider.morales@upb.edu.co",
                    color = Color.Black,
                    style = emailStyle
                )
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Acerca de", color = Color.White, style= textStyle)
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("mode_selection") }) {
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow_svgrepo_com),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.info_circle_svgrepo_com),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier.shadow(elevation = 12.dp)
    )
}




