package com.example.cacaaotesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCacaAoTesouro()
        }
    }
}

@Composable
fun AppCacaAoTesouro() {
    val controleNavegacao = rememberNavController()
    val contexto = LocalContext.current

    // Variáveis para o Extra do Cronômetro
    var tempoInicio by remember { mutableLongStateOf(0L) }
    var tempoFinalExibicao by remember { mutableLongStateOf(0L) }

    NavHost(
        navController = controleNavegacao,
        startDestination = "tela_inicial",
        // Extra: Animações de transição (Slide horizontal)
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        // --- TELA INICIAL ---
        composable("tela_inicial") {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Caça ao Tesouro!", fontSize = 30.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    tempoInicio = System.currentTimeMillis()
                    controleNavegacao.navigate("pista_1")
                }) {
                    Text("Iniciar Caça ao Tesouro")
                }
            }
        }

        // --- PISTAS (MÍNIMO 3) ---
        composable("pista_1") {
            TelaDePista(
                pergunta = "O que tem dentes mas não come?",
                respostaCorreta = "pente",
                aoAvancar = { controleNavegacao.navigate("pista_2") },
                aoVoltar = { controleNavegacao.popBackStack() }
            )
        }

        composable("pista_2") {
            TelaDePista(
                pergunta = "O que enche uma casa, mas não enche uma mão?",
                respostaCorreta = "botão",
                aoAvancar = { controleNavegacao.navigate("pista_3") },
                aoVoltar = { controleNavegacao.popBackStack() }
            )
        }

        composable("pista_3") {
            TelaDePista(
                pergunta = "O que é que quanto mais seco, mais molhado fica?",
                respostaCorreta = "toalha",
                aoAvancar = {
                    tempoFinalExibicao = (System.currentTimeMillis() - tempoInicio) / 1000


                    controleNavegacao.navigate("tela_tesouro")
                },
                aoVoltar = { controleNavegacao.popBackStack() }
            )
        }

        // --- TELA DO TESOURO ---
        composable("tela_tesouro") {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "🎉 Parabéns!", fontSize = 40.sp)
                Text(text = "Você encontrou o tesouro!", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Tempo total: $tempoFinalExibicao segundos", color = Color.Gray)

                Spacer(modifier = Modifier.height(30.dp))

                Button(onClick = {
                    // EXTRA: Reseta o histórico para o botão "voltar" físico não retornar ao tesouro
                    controleNavegacao.navigate("tela_inicial") {
                        popUpTo("tela_inicial") { inclusive = true }
                    }
                }) {
                    Text("Recomeçar Jogo")
                }
            }
        }
    }
}

@Composable
fun TelaDePista(pergunta: String, respostaCorreta: String, aoAvancar: () -> Unit, aoVoltar: () -> Unit) {
    var textoDigitado by remember { mutableStateOf("") }
    var mensagemErro by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pista:", fontSize = 18.sp, color = Color.Gray)
        Text(text = pergunta, fontSize = 22.sp)

        Spacer(modifier = Modifier.height(24.dp))

        // EXTRA: Campo de texto
        OutlinedTextField(
            value = textoDigitado,
            onValueChange = {
                textoDigitado = it
                // Limpa o erro enquanto o usuário digita novamente
                if (mensagemErro.isNotEmpty()) mensagemErro = ""
            },
            label = { Text("Digite sua resposta") },
            singleLine = true
        )

        if (mensagemErro.isNotEmpty()) {
            Text(text = mensagemErro, color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            TextButton(onClick = aoVoltar) {
                Text("Voltar")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                // Validação de resposta
                if (textoDigitado.trim().lowercase() == respostaCorreta.lowercase()) {
                    aoAvancar()
                } else {
                    // REQUISITO ATUALIZADO: Mensagem de erro e esvaziar a barra
                    mensagemErro = "Resposta errada! Tente novamente."
                    textoDigitado = ""
                }
            }) {
                Text("Próxima Pista")
            }
        }
    }
}