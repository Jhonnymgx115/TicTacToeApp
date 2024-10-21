import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tictactoeapp.game.Background
import com.tictactoeapp.game.R
import com.tictactoeapp.game.textStyle
import com.tictactoeapp.game.ui.theme.bebasNeueFamily
import com.tictactoeapp.game.ui.theme.interFamily

@Composable()
fun InstructionsScreen(navController: NavController){
    Box() {
        Background()
        Column (
            modifier = Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row ( verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = { navController.navigate("mode_selection") {
                        popUpTo("instructions") { inclusive = true }
                    } }
                ) {
                    Text(stringResource(R.string.back) ,style= textStyle)
                }
                Spacer(modifier = Modifier.width(40.dp))
                Text(
                    text = stringResource(R.string.app_name),
                    style = TextStyle(
                        fontFamily = bebasNeueFamily,
                        fontSize = 80.sp,
                        letterSpacing = 1.sp
                    ),
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            TextTitle(stringResource(R.string.intro_1))
            Spacer(modifier = Modifier.height(20.dp))
            TextBody(stringResource(R.string.intro_2))
            Spacer(modifier = Modifier.height(20.dp))
            TextTitle(stringResource(R.string.intro_3))
            Spacer(modifier = Modifier.height(20.dp))
            TextBody(stringResource(R.string.intro_4))
            Spacer(modifier = Modifier.height(20.dp))
            TextTitle(stringResource(R.string.intro_5))
            Spacer(modifier = Modifier.height(20.dp))
            TextBody(stringResource(R.string.intro_6))
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable()
fun TextTitle(text: String){
    Text(
        text = text,
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = interFamily,
        )
    )
}

@Composable()
fun TextBody(text: String){
    Text(
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            fontFamily = interFamily,
        )
    )
}
