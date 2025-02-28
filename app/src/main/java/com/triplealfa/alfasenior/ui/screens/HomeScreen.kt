package com.triplealfa.alfasenior.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.triplealfa.alfasenior.R
import com.triplealfa.alfasenior.ui.constants.Dimens

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.CardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.SpacerHeight)
    ) {
        Text(
            text = stringResource(R.string.welcome_alfasenior),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        ButtonWithIcon(
            text = stringResource(R.string.learn_whatsapp),
            icon = Icons.AutoMirrored.Filled.Message,
            onClick = { navController.navigate("module/whatsapp") }
        )
        ButtonWithIcon(
            text = stringResource(R.string.learn_phone),
            icon = Icons.Filled.Phone,
            onClick = { navController.navigate("module/phone") }
        )
        ButtonWithIcon(
            text = stringResource(R.string.learn_internet),
            icon = Icons.Filled.Public,
            onClick = { navController.navigate("module/internet") }
        )
        ButtonWithIcon(
            text = stringResource(R.string.learn_safety),
            icon = Icons.Filled.Shield,
            onClick = { navController.navigate("module/safety") }
        )
        ButtonWithIcon(
            text = stringResource(R.string.help),
            icon = Icons.AutoMirrored.Outlined.Help,
            onClick = { navController.navigate("help") }
        )
    }
}

@Composable
fun ButtonWithIcon(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.ButtonHeight)
    ) {
        Icon(imageVector = icon, contentDescription = text)
        Spacer(modifier = Modifier.width(Dimens.SpacerWidth))
        Text(text = text, fontSize = Dimens.ButtonFontSize, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
