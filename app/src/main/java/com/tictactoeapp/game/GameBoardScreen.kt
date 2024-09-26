package com.tictactoeapp.game

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Score(var xWins: Int = 0, var oWins: Int = 0, var draws: Int = 0)

@Composable
fun GameBoardScreen(navController: NavController, gameMode: String, player1Name: String, player2Name: String) {
    var board by remember { mutableStateOf(List(9) { "" }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }
    var isGameOver by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(Score()) }

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

    fun makeMove(index: Int) {
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
                if (gameMode == "ai" && currentPlayer == aiSide) {
                    // AI's turn
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
            text = "Tic Tac Toe",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("X Wins: ${score.xWins} | O Wins: ${score.oWins} | Draws: ${score.draws}")
        Spacer(modifier = Modifier.height(32.dp))
        GameBoard(board) { index -> makeMove(index) }
        Spacer(modifier = Modifier.height(16.dp))
        when {
            winner == "Draw" -> Text("It's a draw!")
            winner != null -> Text("${if (winner == "X") player1Name else player2Name} wins!")
            else -> Text("Current player: ${if (currentPlayer == "X") player1Name else player2Name}")
        }
        if (isGameOver) {
            Button(onClick = {
                board = List(9) { "" }
                currentPlayer = "X"
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
    Column {
        for (row in 0 until 3) {
            Row {
                for (col in 0 until 3) {
                    val index = row * 3 + col
                    GameCell(board[index]) { onCellClick(index) }
                }
            }
        }
    }
}

@Composable
fun GameCell(value: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(100.dp)
    ) {
        Text(text = value, style = MaterialTheme.typography.headlineLarge)
    }
}