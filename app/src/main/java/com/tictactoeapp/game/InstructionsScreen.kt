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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tictactoeapp.game.Background
import com.tictactoeapp.game.R

@Composable()
fun InstructionsScreen(navController: NavController){
    Box() {
        Background()
        Column (
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
