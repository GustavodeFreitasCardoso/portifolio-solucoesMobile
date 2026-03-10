package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //o APP começa aqui(fun main)
        enableEdgeToEdge() // habilita de borda a borda (tela)
        setContent { // espera receber conteudo para tela
            TelaHome()
            // preciso colocar uma view aqui

        }
    }
}
@Composable
fun TelaHome(){
    var valor by remember { mutableStateOf(value =5 ) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Valor $valor")
        Button(onClick = {
            
            valor++
            println(valor)

        })
        {
            Text(text = "++++")
        }

        Text(text = "Hello world!")
        Text(text = "Bem-vindo a SATC!")
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly

        ) {
            Text(text = "Projeto Mobile!")
            Text(text = "Gustavo")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    TelaHome()
}