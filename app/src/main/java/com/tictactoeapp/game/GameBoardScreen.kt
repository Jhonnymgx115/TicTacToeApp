package com.tictactoeapp.game

import android.widget.Switch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.tictactoeapp.game.ui.theme.interFamily
import kotlinx.coroutines.*

data class Score(var xWins: Int = 0, var oWins: Int = 0, var draws: Int = 0)

val textStyle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = interFamily,
)
val gameText = TextStyle(
    fontSize = 30.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = interFamily,
)

@Composable
fun GameBoardScreen(navController: NavController, gameMode: String, player1Name: String, player2Name: String) {
    var board by remember { mutableStateOf(List(9) { "" }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var switchState by remember { mutableStateOf(true) }
    var winner by remember { mutableStateOf<String?>(null) }
    var isGameOver by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(Score()) }
    val coroutineScope = rememberCoroutineScope()

    val aiSide = if (gameMode == "ai") "O" else null

    fun checkWinner(board: List<String>): String? {
        val winningCombinations = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
            listOf(0, 4, 8), listOf(2, 4, 6) // Diagonals
        )

        for (combination in winningCombinations) {
            if (board[combination[0]] != "" &&
                board[combination[0]] == board[combination[1]] &&
                board[combination[0]] == board[combination[2]]
            ) {
                return board[combination[0]]
            }
        }

        if (board.none { it.isEmpty() }) {
            return "Draw"
        }

        return null
    }

    suspend fun makeMove(index: Int) {
        if (board[index].isEmpty() && !isGameOver) {
            board = board.toMutableList().also { it[index] = currentPlayer }
            winner = checkWinner(board)
            if (winner != null) {
                isGameOver = true
                when (winner) {
                    "X" -> score.xWins++
                    "O" -> score.oWins++
                    "Draw" -> score.draws++
                }
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                switchState = if (currentPlayer == "X") true else false
                if (gameMode == "ai" && currentPlayer == aiSide) {
                    // AI's turn
                    delay(1000)
                    val aiMove = board.indices.filter { board[it].isEmpty() }.random()
                    makeMove(aiMove)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text= "X", color=MaterialTheme.colorScheme.primary, style= textStyle)
                Text(text="${score.xWins} wins", color = MaterialTheme.colorScheme.primary, style = textStyle
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text= "O", color=MaterialTheme.colorScheme.secondary, style=textStyle)
                Text(text="${score.oWins} wins", color=MaterialTheme.colorScheme.secondary, style=textStyle)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu Icon",
                    tint = MaterialTheme.colorScheme.tertiary
                )
                Text(text="${score.draws} draws", color=MaterialTheme.colorScheme.tertiary, style=textStyle)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        GameBoard(board) { index ->  coroutineScope.launch {
            makeMove(index)
        } }
        Spacer(modifier = Modifier.height(16.dp))
        when {
            winner == "Draw" -> Text("It's a draw!")
            winner != null -> Text("${if (winner == "X") player1Name else player2Name} wins!")
            else -> Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Current player: ${if (currentPlayer == "X") player1Name else player2Name}",
                    style= textStyle)
                CustomSwitch(
                    checked = switchState,
                    onCheckedChange = {
                        newValue -> switchState = newValue
                    }
                )
            }


        }
        if (isGameOver) {
            Button(onClick = {
                board = List(9) { "" }
                currentPlayer = "X"
                switchState = true
                winner = null
                isGameOver = false
            }) {
                Text("Play Again")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("mode_selection") }) {
            Text("Back to Menu")
        }
    }
}

@Composable
fun GameBoard(board: List<String>, onCellClick: (Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        for (row in 0 until 3) {
            if(row >0 ){
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.tertiary,
                    thickness = 4.dp,
                    modifier = Modifier
                        .width(350.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (col in 0 until 3) {
                    if(col >0) {
                        VerticalDivider(
                            color = MaterialTheme.colorScheme.tertiary,
                            thickness = 4.dp,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                    val index = row * 3 + col
                    GameCell(board[index]) { onCellClick(index) }
                }
            }
        }
    }
}

@Composable
fun GameCell(value: String, onClick: () -> Unit) {
    val colorX = MaterialTheme.colorScheme.primary
    val color0 = MaterialTheme.colorScheme.secondary
    Button(
        onClick = onClick,
        modifier = Modifier.size(110.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        when{
            value == "X" -> Image(
                painter = painterResource(id = R.drawable.close_bold_svgrepo_com),
                contentDescription = "X" ,
                modifier = Modifier.size(250.dp)
            )
            value == "O" -> Image(
                painter = painterResource(id = R.drawable.circle_svgrepo_com),
                contentDescription = "X",
                modifier = Modifier.size(250.dp)
            )
            else -> {

            }
        }
    }
}

@Composable
fun CustomSwitch (checked: Boolean, onCheckedChange: (Boolean) -> Unit){


    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        thumbContent = if (checked) {
            {
                Text("X", color=MaterialTheme.colorScheme.primary, style= textStyle)
            }
        } else {
            {
                Text("O", color=MaterialTheme.colorScheme.secondary, style= textStyle)
            }
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.background,
            checkedTrackColor = MaterialTheme.colorScheme.primary,
            uncheckedThumbColor = MaterialTheme.colorScheme.background,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondary,
        ),
    )
}