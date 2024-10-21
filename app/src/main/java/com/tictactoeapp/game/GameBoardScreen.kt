package com.tictactoeapp.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import com.tictactoeapp.game.ui.theme.interFamily
import kotlinx.coroutines.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.compose.*
import com.tictactoeapp.game.ui.theme.bebasNeueFamily


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
fun GameBoardScreen(
    navController: NavController, gameMode: String, player1Name: String, player2Name: String
) {
    var board by remember { mutableStateOf(List(9) { "" }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var switchState by remember { mutableStateOf(true) }
    var winner by remember { mutableStateOf<String?>(null) }
    var isGameOver by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(Score()) }
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    val aiSide = if (gameMode == "ai") "O" else null



    fun checkWinner(board: List<String>): String? {
        val winningCombinations = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
            listOf(0, 4, 8), listOf(2, 4, 6) // Diagonals
        )

        for (combination in winningCombinations) {
            if (board[combination[0]] != "" && board[combination[0]] == board[combination[1]] && board[combination[0]] == board[combination[2]]) {
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
                switchState = currentPlayer == "X"
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
        Text(
            text = stringResource(R.string.app_name),
            style = TextStyle(
                fontFamily = bebasNeueFamily,
                fontSize = 80.sp,
                letterSpacing = 1.sp
            ),
        )
        Spacer(modifier = Modifier.height(32.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "X", color = MaterialTheme.colorScheme.primary, style = textStyle)
                Text(
                    text = "${score.xWins} " + stringResource(R.string.wins),
                    color = MaterialTheme.colorScheme.primary,
                    style = textStyle
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "O", color = MaterialTheme.colorScheme.secondary, style = textStyle)
                Text(
                    text = "${score.oWins} " + stringResource(R.string.wins),
                    color = MaterialTheme.colorScheme.secondary,
                    style = textStyle
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu Icon",
                    tint = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "${score.draws} " + stringResource(R.string.draws),
                    color = MaterialTheme.colorScheme.tertiary,
                    style = textStyle
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        GameBoard(board) { index ->
            coroutineScope.launch {
                makeMove(index)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when {
            winner == "Draw" -> Text(stringResource(R.string.its_a_draw))
            winner != null -> Text("${if (winner == "X") player1Name else player2Name} "+ stringResource(R.string.victory))
            else -> Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.current_player) + " ${if (currentPlayer == "X") player1Name else player2Name}",
                    style = textStyle
                )
                CustomSwitch(checked = switchState, onCheckedChange = { newValue ->
                    switchState = newValue
                })
            }


        }
        if (isGameOver) {
            showDialog = true
            if(winner != "Draw"){
                winAnimation()
            }
            if (showDialog){
                popUp(onDismiss = {
                    showDialog = false
                    isGameOver=false
                    navController.navigate("mode_selection")
                },
                    onConfirmation = {
                        board = List(9) { "" }
                        currentPlayer = "X"
                        switchState = true
                        winner = null
                        isGameOver = false
                        showDialog=false
                    },
                    when{
                        winner == "Draw" -> painterResource(id = R.drawable.draw)
                        winner == "X" -> painterResource(id = R.drawable.close_bold_svgrepo_com)
                        else -> painterResource(id = R.drawable.circle_svgrepo_com)
                    },
                    when (winner){
                        "Draw" -> stringResource(R.string.its_a_draw).toString()
                        "X" -> (player1Name + " " + stringResource(R.string.victory)).toString()
                        else -> (player2Name + " " + stringResource(R.string.victory)).toString()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("mode_selection") }) {
            Text(stringResource(R.string.back_to_menu))
        }
    }
}

@Composable
fun GameBoard(board: List<String>, onCellClick: (Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        for (row in 0 until 3) {
            if (row > 0) {
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
                    if (col > 0) {
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
    val visible = remember { mutableStateOf(value.isNotEmpty()) }
    val durationAnimation = 5000

    LaunchedEffect(value) {
        visible.value = value.isNotEmpty()
    }

    Button(
        onClick = { onClick() },
        modifier = Modifier.size(110.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        when (value) {
            "X" -> {
                AnimatedVisibility(
                    visible = visible.value, enter = fadeIn(
                        // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                        initialAlpha = 0.1f
                    ), exit = fadeOut(
                        // Overwrites the default animation with tween
                        animationSpec = tween(durationMillis = durationAnimation)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.close_bold_svgrepo_com),
                        contentDescription = "X",
                        modifier = Modifier.size(250.dp) // Aplicar escala a la imagen
                    )
                }

            }

            "O" -> {
                AnimatedVisibility(
                    visible = visible.value, enter = fadeIn(
                        // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                        initialAlpha = 0.1f
                    ), exit = fadeOut(
                        // Overwrites the default animation with tween
                        animationSpec = tween(durationMillis = durationAnimation)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.circle_svgrepo_com),
                        contentDescription = "O",
                        modifier = Modifier.size(250.dp)
                    )
                }
            }

            else -> {
            }
        }
    }

}


@Composable
fun CustomSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {

    Switch(
        modifier = Modifier
            .scale(1.3f)
            .padding(10.dp),
        checked = checked,
        enabled = false,
        onCheckedChange = onCheckedChange,
        thumbContent = if (checked) {
            {
                Text("X", color = MaterialTheme.colorScheme.primary, style = textStyle)
            }
        } else {
            {
                Text("O", color = MaterialTheme.colorScheme.secondary, style = textStyle)
            }
        },
        colors = SwitchDefaults.colors(
            disabledCheckedThumbColor = MaterialTheme.colorScheme.background,
            disabledCheckedTrackColor = MaterialTheme.colorScheme.primary,
            disabledUncheckedThumbColor = MaterialTheme.colorScheme.background,
            disabledUncheckedTrackColor = MaterialTheme.colorScheme.secondary,
        ),
    )
}

@Composable
fun winAnimation (){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.confetti))
    val progress by animateLottieCompositionAsState(composition)

    // Muestra la animación
    LottieAnimation(
        composition,
        progress,
        modifier = Modifier.fillMaxSize()
    )
}