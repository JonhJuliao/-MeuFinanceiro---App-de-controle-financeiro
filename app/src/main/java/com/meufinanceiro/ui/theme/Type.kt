package com.meufinanceiro.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.meufinanceiro.R

// 1. FONTE
val interFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold)
)

// 2. ESTILO NUMÉRICO
val techTextStyle = TextStyle(
    fontFamily = interFontFamily,
    fontFeatureSettings = "tnum"
)

// 3. VARIÁVEL DE TIPOGRAFIA (O Theme.kt procura por ISSO)
val FinanceiroTypography = Typography( // <--- NOME IMPORTANTE
    displayLarge = techTextStyle.copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 40.sp,
        lineHeight = 48.sp,
        letterSpacing = (-1).sp
    ),
    displayMedium = techTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    titleMedium = techTextStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = techTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodySmall = techTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = androidx.compose.ui.graphics.Color.Gray
    ),
    labelLarge = techTextStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    )
)