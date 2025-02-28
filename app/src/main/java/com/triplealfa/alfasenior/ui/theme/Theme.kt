package com.triplealfa.alfasenior.ui.theme

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.triplealfa.alfasenior.ui.constants.Dimens
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

// Definição de cores padrão
val PrimaryColor = Color(0xFF1E88E5)
val SecondaryColor = Color(0xFF43A047)
val BackgroundColor = Color(0xFFF5F5F5)
val OnPrimaryColor = Color.White
val OnBackgroundColor = Color.Black

// Definição de cores para Alto Contraste
val HighContrastPrimary = Color(0xFFFFEB3B)
val HighContrastSecondary = Color(0xFFFF5722)
val HighContrastBackground = Color(0xFF000000)
val HighContrastOnPrimary = Color.Black
val HighContrastOnBackground = Color.White

// Tipografia personalizada
val CustomTypography = Typography(
    headlineMedium = TextStyle(
        fontSize = Dimens.HeadlineMedium,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif
    ),
    bodyMedium = TextStyle(
        fontSize = Dimens.ButtonFontSize,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif
    ),
    labelLarge = TextStyle(
        fontSize = Dimens.LabelLarge,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.SansSerif
    )
)

// DataStore para salvar a preferência do tema
val Context.dataStore by preferencesDataStore(name = "settings")
val HIGH_CONTRAST_KEY = booleanPreferencesKey("high_contrast")

fun getHighContrast(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { preferences ->
        preferences[HIGH_CONTRAST_KEY] ?: false
    }
}

fun setHighContrast(context: Context, isEnabled: Boolean) {
    runBlocking {
        context.dataStore.edit { preferences ->
            preferences[HIGH_CONTRAST_KEY] = isEnabled
        }
    }
}

@Composable
fun AlfaSeniorTheme(highContrast: Boolean, content: @Composable () -> Unit) {
    val colorScheme = if (highContrast) {
        darkColorScheme(
            primary = HighContrastPrimary,
            secondary = HighContrastSecondary,
            background = HighContrastBackground,
            surface = HighContrastBackground,
            onPrimary = HighContrastOnPrimary,
            onBackground = HighContrastOnBackground,
            onSurface = HighContrastOnBackground
        )
    } else {
        lightColorScheme(
            primary = PrimaryColor,
            secondary = SecondaryColor,
            background = BackgroundColor,
            surface = BackgroundColor,
            onPrimary = OnPrimaryColor,
            onBackground = OnBackgroundColor,
            onSurface = OnBackgroundColor
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CustomTypography,
        content = content
    )
}