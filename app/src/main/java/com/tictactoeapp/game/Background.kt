package com.tictactoeapp.game


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Background (){
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

}
