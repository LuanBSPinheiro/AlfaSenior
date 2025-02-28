package com.triplealfa.alfasenior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.triplealfa.alfasenior.ui.screens.HomeScreen
import com.triplealfa.alfasenior.ui.screens.ModuleScreen
import com.triplealfa.alfasenior.ui.screens.AboutScreen
import com.triplealfa.alfasenior.ui.screens.InternetScreen
import com.triplealfa.alfasenior.ui.screens.PhoneScreen
import com.triplealfa.alfasenior.ui.screens.SafetyScreen
import com.triplealfa.alfasenior.ui.screens.WhatsAppScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlfaSeniorApp()
        }
    }
}

@Composable
fun AlfaSeniorApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("module/{moduleName}") { backStackEntry ->
            ModuleScreen(navController, backStackEntry.arguments?.getString("moduleName") ?: "")
        }
        composable("help") { AboutScreen(navController) }
        composable("module/phone") { PhoneScreen(navController) }
        composable("module/internet") { InternetScreen(navController) }
        composable("module/safety") { SafetyScreen(navController) }
        composable("module/whatsapp") { WhatsAppScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun AlfaSeniorAppPreview() {
    AlfaSeniorApp()
}