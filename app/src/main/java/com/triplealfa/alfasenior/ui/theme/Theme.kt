package com.triplealfa.alfasenior.ui.theme

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.triplealfa.alfasenior.ui.constants.Dimens
import kotlinx.coroutines.delay
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
        fontSize = Dimens._24sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif
    ),
    bodyMedium = TextStyle(
        fontSize = Dimens._18sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif
    ),
    labelLarge = TextStyle(
        fontSize = Dimens._16sp,
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
fun AnimatedButton(
    text: String,
    icon: ImageVector? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconAtEnd: Boolean = false
) {
    var isPressed by remember { mutableStateOf(false) }

    Button(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = modifier
            .scale(if (isPressed) 0.95f else 1f)
            .animatedPressEffect(),
        shape = RoundedCornerShape(Dimens._12dp),
        colors = ButtonDefaults.buttonColors(),
        enabled = enabled
    ) {
        if (!iconAtEnd && icon != null) {
            Icon(imageVector = icon, contentDescription = text)
            Spacer(modifier = Modifier.width(Dimens._8dp))
        }
        Text(text = text, fontSize = Dimens._18sp)
        if (iconAtEnd && icon != null) {
            Spacer(modifier = Modifier.width(Dimens._8dp))
            Icon(imageVector = icon, contentDescription = text)
        }
    }
}

@Composable
fun ModuleCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(Dimens._12dp),
        colors = CardDefaults.cardColors(containerColor = if (MaterialTheme.colorScheme.background == Color.Black) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens._4dp),
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens._90dp)
            .clickable { onClick() }
            .padding(Dimens._12dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = Dimens._20dp, vertical = Dimens._16dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(Dimens._16dp))
            Text(text = title, fontSize = Dimens._22sp, lineHeight = Dimens._28sp, fontWeight = FontWeight.Medium, color = Color.Black)
        }
    }
}

fun Modifier.animatedPressEffect(): Modifier = composed {
    var isPressed by remember { mutableStateOf(false) }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(100)
            isPressed = false
        }
    }

    this.scale(if (isPressed) 0.95f else 1f)
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