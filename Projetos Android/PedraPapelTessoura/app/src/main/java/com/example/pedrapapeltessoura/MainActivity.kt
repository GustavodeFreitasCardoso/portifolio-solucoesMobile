package com.example.pedrapapeltessoura

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mediaPlayer = MediaPlayer.create(this, R.raw.som_jogada)

        setContent {
            PedraPapelTesouraApp(mediaPlayer)
        }
    }
}

@Composable
fun PedraPapelTesouraApp(mediaPlayer: MediaPlayer) {

    val opcoes = listOf(
        R.drawable.pedra,
        R.drawable.papel,
        R.drawable.tesoura
    )

    var imagemAtual by remember { mutableStateOf(R.drawable.pedra) }

    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = "Pedra Papel Tesoura",
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = imagemAtual),
                contentDescription = "Resultado",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {

                    mediaPlayer.start()

                    scope.launch {

                        repeat(10) {

                            imagemAtual = opcoes.random()
                            delay(100)

                        }

                        imagemAtual = opcoes[Random.nextInt(3)]
                    }

                }
            ) {

                Text("Jogar")

            }

        }

    }

}
