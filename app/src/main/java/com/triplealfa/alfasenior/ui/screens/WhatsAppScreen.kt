package com.triplealfa.alfasenior.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.triplealfa.alfasenior.R
import com.triplealfa.alfasenior.ui.constants.Dimens

@Composable
fun WhatsAppScreen(navController: NavController) {
    var step by remember { mutableIntStateOf(-1) }

    val introText =
        stringResource(R.string.intro_whatsapp)

    val steps = listOf(
        stringResource(R.string.first_step_whatsapp),
        stringResource(R.string.second_step_whatsapp),
        stringResource(R.string.third_step_whatsapp),
        stringResource(R.string.fourth_step_whatsapp),
        stringResource(R.string.fifth_step_whatsapp)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.CardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(Dimens.CardCornerRadius),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDCF8C6))
        ) {
            Column(
                modifier = Modifier.padding(Dimens.CardPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Chat,
                    contentDescription = stringResource(R.string.whatsapp),
                    tint = Color(0xFF075E54)
                )
                Spacer(modifier = Modifier.height(Dimens.SpacerHeight))
                Text(
                    text = stringResource(R.string.module_whatsapp),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(Dimens.SpacerHeight))
                Text(
                    text = if (step == -1) introText else steps[step],
                    fontSize = Dimens.ButtonFontSize,
                    color = Color(0xFF1E293B)
                )
                Spacer(modifier = Modifier.height(Dimens.SpacerHeight))
            }
        }

        Spacer(modifier = Modifier.height(Dimens.SpacerHeight))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.height(Dimens.ButtonHeight),
                onClick = { if (step > -1) step-- },
                enabled = step > -1
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.previous)
                )
                Spacer(modifier = Modifier.width(Dimens.SpacerWidth))
                Text(
                    text = stringResource(R.string.previous),
                    fontSize = Dimens.ButtonFontSize,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                modifier = Modifier.height(Dimens.ButtonHeight),
                onClick = { if (step < steps.size - 1) step++ },
                enabled = step < steps.size - 1
            ) {
                Text(
                    if (step == -1) stringResource(R.string.start) else stringResource(R.string.next),
                    fontSize = Dimens.ButtonFontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimens.SpacerHeight))
        Button(
            modifier = Modifier.height(Dimens.ButtonHeight),
            onClick = { navController.popBackStack() }) {
            Text(
                text = stringResource(R.string.back),
                fontSize = Dimens.ButtonFontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsAppScreenPreview() {
    WhatsAppScreen(navController = rememberNavController())
}
