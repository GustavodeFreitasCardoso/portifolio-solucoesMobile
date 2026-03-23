package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraScreen()
        }
    }
}

@Composable
fun CalculadoraScreen() {

    var tela by remember { mutableStateOf("0") }
    var valorInicial by remember { mutableStateOf(0.0) }
    var operador by remember { mutableStateOf("") }
    var novoNumero by remember { mutableStateOf(true) }

    fun onDigitClick(digit: String) {
        if (novoNumero || tela == "0") {
            tela = digit
            novoNumero = false
        } else {
            tela += digit
        }
    }

    fun onOperatorClick(op: String) {
        valorInicial = tela.toDoubleOrNull() ?: 0.0
        operador = op
        novoNumero = true
    }

    fun onEqualClick() {

        val second = tela.toDoubleOrNull() ?: 0.0

        val result = when (operador) {
            "+" -> valorInicial + second
            "-" -> valorInicial - second
            "*" -> valorInicial * second
            "/" -> if (second != 0.0) valorInicial / second else 0.0
            else -> second
        }

        tela = result.toString().removeSuffix(".0")
        operador = ""
        novoNumero = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(12.dp)
    ) {

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = tela,
            color = Color.White,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        val buttons = listOf(
            listOf("7","8","9","/"),
            listOf("4","5","6","*"),
            listOf("1","2","3","-"),
            listOf("0","C","=","+")
        )

        buttons.forEach { row ->

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                row.forEach { label ->

                    val weight = if (label == "0") 2f else 1f

                    CalcButton(
                        label = label,
                        modifier = Modifier
                            .weight(weight)
                            .height(80.dp),
                        onClick = {

                            when {

                                label in "0".."9" ->
                                    onDigitClick(label)

                                label == "C" -> {
                                    tela = "0"
                                    operador = ""
                                    valorInicial = 0.0
                                    novoNumero = true
                                }

                                label == "=" ->
                                    onEqualClick()

                                else ->
                                    onOperatorClick(label)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CalcButton(
    label: String,
    modifier: Modifier,
    onClick: () -> Unit
) {

    val operatorColor = Color(0xFFFF6A00)   // laranja POCO
    val numberColor = Color(0xFF2A2A2A)

    val isOperator = label in listOf("/","*","-","+","=")

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (isOperator) operatorColor else numberColor,
            contentColor = Color.White
        )
    ) {

        Text(
            text = label,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}