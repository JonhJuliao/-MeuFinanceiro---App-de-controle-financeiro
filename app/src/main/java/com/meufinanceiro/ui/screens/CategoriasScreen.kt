package com.meufinanceiro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*

@Composable
fun CategoriasScreen(
    navController: NavController,
    // quando integrar com o backend, isso vira lista vinda do ViewModel
    categoriasIniciais: List<String> = listOf("Alimentação", "Transporte", "Lazer"),
    onAddCategoria: (String) -> Unit = {},
    onDeleteCategoria: (String) -> Unit = {}
) {
    var categorias by remember { mutableStateOf(categoriasIniciais.toMutableList()) }
    var novaCategoria by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Categorias") },
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
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Campo + botão de adicionar
            OutlinedTextField(
                value = novaCategoria,
                onValueChange = { novaCategoria = it },
                label = { Text("Nova categoria") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Category, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val nome = novaCategoria.trim()
                    if (nome.isNotEmpty() && nome !in categorias) {
                        categorias.add(nome)
                        onAddCategoria(nome)
                        novaCategoria = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Adicionar", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de categorias
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(categorias) { categoria ->
                    CategoriaCard(
                        nome = categoria,
                        onDelete = {
                            categorias.remove(categoria)
                            onDeleteCategoria(categoria)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoriaCard(
    nome: String,
    onDelete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = nome,
                style = MaterialTheme.typography.titleMedium
            )

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Excluir categoria",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
