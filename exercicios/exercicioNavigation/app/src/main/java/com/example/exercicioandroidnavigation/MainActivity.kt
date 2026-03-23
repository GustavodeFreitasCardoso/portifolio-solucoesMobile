package com.example.exercicioandroidnavigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent{
            val navigationController = rememberNavController()
            NavHost(
                navController = navigationController,
                startDestination = "/tela01"
            ){
                composable("/tela01"){
                    Tela("/tela01",
                        clickAnterior = {navigationController.navigate("/tela03")},
                        clickProximo = {navigationController.navigate("/tela02")}
                    )
                }
                composable("/tela02"){
                    Tela("/tela02")
                }
                composable("/tela03"){
                    Tela("/tela03")
                }

            }
        }


    }

}
@Composable
fun Tela(
    nomeDaTela: String = "tela default",
    clickAnterior:() -> Unit,
    clickProximo:() -> Unit
    ){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = nomeDaTela, fontSize = 36.sp)
        Button(onClick = {clickProximo}) {
            Text("proxima tela")
        }
        Button(onClick = {clickAnterior}) {
            Text("tela anterior")
        }
    }
}
@Composable
@Preview(showBackground = true )
fun Preview (){
    Tela("tela 01")
}