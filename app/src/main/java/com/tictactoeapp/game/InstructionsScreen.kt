import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tictactoeapp.game.Background

@Composable()
fun InstructionsScreen(navController: NavController){
    Box() {
        Background()
        Column (
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextTitle("Objetivo del Juego")
            Spacer(modifier = Modifier.height(20.dp))
            TextBody("El objetivo es ser el primer jugador en formar una línea de tres símbolos consecutivos (ya sea en fila, columna o diagonal) en un tablero de 3x3.")
            Spacer(modifier = Modifier.height(20.dp))
            TextTitle("Número de Jugadores")
            Spacer(modifier = Modifier.height(20.dp))
            TextBody("Un jugador utilizará el símbolo X.\n" +
                    "El otro jugador utilizará el símbolo O.")
            Spacer(modifier = Modifier.height(20.dp))
            TextTitle("Cómo Jugar")
            Spacer(modifier = Modifier.height(20.dp))
            TextBody("Prepara el tablero:\n" +
                    "El tablero es una cuadrícula de 3 filas y 3 columnas (un total de 9 casillas vacías).\n" +
                    "Elige quién va primero:\n" +
                    "Puedes decidir al azar o simplemente alternar en cada nueva partida.\n" +
                    "Turnos alternos:\n" +
                    "El primer jugador colocará su símbolo (X u O) en cualquier casilla vacía del tablero.\n" +
                    "Luego, el segundo jugador colocará su símbolo (O o X) en una casilla vacía.\n" +
                    "Los jugadores continúan alternando turnos hasta que uno de ellos gane o el juego termine en empate.\n" +
                    "Condiciones para ganar:\n" +
                    "Un jugador gana si logra colocar tres de sus símbolos consecutivos en una fila, columna o diagonal del tablero.\n" +
                    "Juego empatado:\n" +
                    "Si todas las casillas del tablero están llenas y ningún jugador ha logrado formar una línea de tres símbolos consecutivos, el juego termina en empate.")
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable()
fun TextTitle(text: String){
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable()
fun TextBody(text: String){
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium
    )
}
