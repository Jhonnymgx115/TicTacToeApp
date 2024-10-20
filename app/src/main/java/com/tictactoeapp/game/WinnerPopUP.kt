package com.tictactoeapp.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun popUp (
    onDismiss:()->Unit,
    onConfirmation:()->Unit,
    painter: Painter,
    texto: String,
){

    Dialog(
        onDismissRequest = onDismiss

    ){Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ){

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painter,
                contentDescription = "desc",
                modifier = Modifier.size(80.dp)
            )
            Text(
                text=texto,
                style = textStyle
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ){
                TextButton(
                    onClick = { onDismiss() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Menú")
                }
                TextButton(
                    onClick = { onConfirmation() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Jugar de nuevo")
                }
            }

        }

    }

    }

}