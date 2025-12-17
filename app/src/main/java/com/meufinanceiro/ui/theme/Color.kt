package com.meufinanceiro.ui.theme

import androidx.compose.ui.graphics.Color

// ================================
// PALETA "EXECUTIVE EMERALD" (SÉRIO & ESCURO)
// ================================

// --- MODO ESCURO ---
val CyberBlack = Color(0xFF020503)
val CyberSurface = Color(0xFF0E1612)

// AQUI ESTÁ A MUDANÇA NOS BOTÕES:
// Em vez de "Neon" (Radioativo), usamos o "Emerald" (Jóia).
// É brilhante o suficiente para ler, mas tem peso.
val ElectricGreen = Color(0xFF10B981)   // Emerald 500
val SolidGreen = Color(0xFF047857)      // Emerald 700

// Semântica Dark
val NeonSuccess = Color(0xFF4ADE80)
val NeonError = Color(0xFFF87171)

// --- MODO CLARO ---
val TechWhite = Color(0xFFF2F7F5)
val TechSurface = Color(0xFFFFFFFF)

val ModernGreen = Color(0xFF15803D)     // Verde Sólido
val TechDarkText = Color(0xFF064E3B)    // Texto Escuro

// Semântica Light
val LightSuccess = Color(0xFF16A34A)
val LightError = Color(0xFFDC2626)

// --- FUNDOS SUAVES ---
val SoftMintGray = Color(0xFFE8F5E9)

// --- GRADIENTES ---

// 1. Dark Mode: "FLORESTA NOTURNA" (O que você pediu)
// Começa quase preto e vai para um verde sério.
// É praticamente a versão noturna do seu tema claro.
val GradientCyberpunk = listOf(
    Color(0xFF022C22),  // Verde Black (Quase preto)
    Color(0xFF059669)   // Verde Esmeralda Profundo
)

// 2. Light Mode: "FLORESTA DIURNA"
val GradientLightMode = listOf(
    Color(0xFF064E3B),  // Verde Floresta
    Color(0xFF10B981)   // Verde Esmeralda
)