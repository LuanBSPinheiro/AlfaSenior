package com.triplealfa.alfasenior.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.triplealfa.alfasenior.R
import com.triplealfa.alfasenior.ui.constants.Dimens
import com.triplealfa.alfasenior.ui.theme.ModuleCard
import com.triplealfa.alfasenior.ui.theme.getHighContrast
import com.triplealfa.alfasenior.ui.theme.setHighContrast

@Composable
fun HomeScreen(navController: NavController, context: Context, onThemeChange: (Boolean) -> Unit) {
    val highContrastFlow = getHighContrast(context).collectAsState(initial = false)
    val highContrast = highContrastFlow.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens._16dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens._20dp)
    ) {
        Text(
            text = stringResource(R.string.welcome_alfasenior),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.modo_alto_contraste),
                style = MaterialTheme.typography.bodyMedium
            )
            Switch(
                checked = highContrast,
                onCheckedChange = {
                    setHighContrast(context, it)
                    onThemeChange(it)
                }
            )
        }

        ModuleCard(
            title = stringResource(R.string.learn_whatsapp),
            icon = Icons.AutoMirrored.Filled.Message,
            onClick = { navController.navigate("module/whatsapp") },
            modifier = Modifier.fillMaxWidth()
        )

        ModuleCard(
            title = stringResource(R.string.learn_phone),
            icon = Icons.Filled.Phone,
            onClick = { navController.navigate("module/phone") },
            modifier = Modifier.fillMaxWidth()
        )
        ModuleCard(
            title = stringResource(R.string.learn_internet),
            icon = Icons.Filled.Public,
            onClick = { navController.navigate("module/internet") },
            modifier = Modifier.fillMaxWidth()
        )
        ModuleCard(
            title = stringResource(R.string.learn_safety),
            icon = Icons.Filled.Shield,
            onClick = { navController.navigate("module/safety") },
            modifier = Modifier.fillMaxWidth()
        )
        ModuleCard(
            title = stringResource(R.string.about),
            icon = Icons.AutoMirrored.Outlined.Help,
            onClick = { navController.navigate("about") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        context = androidx.compose.ui.platform.LocalContext.current,
        onThemeChange = {})
}
