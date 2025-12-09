package com.meufinanceiro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ResumoFinanceiroScreen(
    navController: NavController,
    // depois conectamos isso ao backend
    totalReceitas: Double = 2500.0,
    totalDespesas: Double = 780.0
) {
    val saldo = totalReceitas - totalDespesas

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Resumo Financeiro") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            CardResumo(
                titulo = "Receitas",
                valor = totalReceitas,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                textColor = MaterialTheme.colorScheme.primary
            )

            CardResumo(
                titulo = "Despesas",
                valor = totalDespesas,
                color = MaterialTheme.colorScheme.error.copy(alpha = 0.15f),
                textColor = MaterialTheme.colorScheme.error
            )

            CardResumo(
                titulo = "Saldo",
                valor = saldo,
                color = if (saldo >= 0)
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                else
                    MaterialTheme.colorScheme.error.copy(alpha = 0.15f),
                textColor = if (saldo >= 0)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun CardResumo(
    titulo: String,
    valor: Double,
    color: androidx.compose.ui.graphics.Color,
    textColor: androidx.compose.ui.graphics.Color
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                color = textColor
            )

            Text(
                text = "R$ %.2f".format(valor),
                fontSize = 26.sp,
                color = textColor
            )
        }
    }
}
