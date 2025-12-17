package com.meufinanceiro.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
// MUDANÇA: Ícones Arredondados
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.TrendingDown
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.meufinanceiro.backend.db.AppDatabase
import com.meufinanceiro.backend.repository.TransacaoRepository
import com.meufinanceiro.backend.service.ResumoFinanceiroService
import com.meufinanceiro.ui.extensions.toCurrency
import com.meufinanceiro.ui.viewmodel.ResumoFinanceiroFactory
import com.meufinanceiro.ui.viewmodel.ResumoFinanceiroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumoFinanceiroScreen(navController: NavController) {

    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()

    // 1. CONSTRUÇÃO DA ARQUITETURA
    val db = remember { Room.databaseBuilder(context, AppDatabase::class.java, "meu_financeiro.db").build() }
    val service = remember { ResumoFinanceiroService(transacaoRepository = TransacaoRepository(dao = db.transacaoDao())) }
    val viewModel: ResumoFinanceiroViewModel = viewModel(factory = ResumoFinanceiroFactory(service))

    // 2. OBSERVANDO OS ESTADOS
    val receitas by viewModel.totalReceitas.collectAsState()
    val despesas by viewModel.totalDespesas.collectAsState()
    val saldo by viewModel.saldo.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resumo Financeiro", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Cartão de Receitas (Verde)
            CardResumo(
                titulo = "Receitas",
                valor = receitas,
                icon = Icons.Rounded.TrendingUp,
                color = MaterialTheme.colorScheme.primary, // Verde Principal
                isDark = isDark
            )

            // Cartão de Despesas (Vermelho)
            CardResumo(
                titulo = "Despesas",
                valor = despesas,
                icon = Icons.Rounded.TrendingDown,
                color = MaterialTheme.colorScheme.error, // Vermelho
                isDark = isDark
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            Spacer(modifier = Modifier.height(8.dp))

            // Cartão de Saldo (Dinâmico)
            val corSaldo = if (saldo >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error

            CardResumo(
                titulo = "Saldo Final",
                valor = saldo,
                icon = Icons.Rounded.AccountBalanceWallet,
                color = corSaldo,
                isDark = isDark,
                isTotal = true // Destaque visual
            )
        }
    }
}

// 4. COMPONENTE REUTILIZÁVEL (Visual Tech)
@Composable
fun CardResumo(
    titulo: String,
    valor: Double,
    icon: ImageVector,
    color: Color,
    isDark: Boolean,
    isTotal: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            // Fundo Sólido (CyberSurface ou Branco)
            containerColor = MaterialTheme.colorScheme.surface
        ),
        // BORDA TECH:
        // No Dark Mode, a borda brilha suavemente com a cor do card.
        // No Light Mode, uma borda cinza sutil ou a cor do card mais transparente.
        border = BorderStroke(
            width = if (isTotal) 2.dp else 1.dp, // Saldo tem borda mais grossa
            color = if (isDark) color.copy(alpha = 0.5f) else color.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Ícone + Título
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Fundo do ícone
                Surface(
                    shape = CircleShape,
                    color = color.copy(alpha = 0.1f),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }

            // Valor
            Text(
                text = valor.toCurrency(),
                fontSize = if (isTotal) 24.sp else 20.sp, // Saldo maior
                fontWeight = FontWeight.Bold,
                color = color // O número assume a cor (Verde ou Vermelho)
            )
        }
    }
}