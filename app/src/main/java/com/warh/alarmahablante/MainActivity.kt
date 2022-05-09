package com.warh.alarmahablante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.warh.alarmahablante.ui.screens.AlarmasGuardadasScreen
import com.warh.alarmahablante.ui.screens.ConfigurarAlarmaScreen
import com.warh.alarmahablante.ui.theme.AlarmaHablanteTheme
import com.warh.alarmahablante.viewmodel.AlarmaApplication
import com.warh.alarmahablante.viewmodel.AlarmaViewModel
import com.warh.alarmahablante.viewmodel.AlarmaViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: AlarmaViewModel by viewModels {
            AlarmaViewModelFactory((application as AlarmaApplication).repository)
        }

        setContent {
            AlarmaHablanteTheme { NavigationHost(viewModel) }
        }
    }
}

@Composable
fun NavigationHost(viewModel: AlarmaViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "AlarmasGuardadas"){
        composable("AlarmasGuardadas") { AlarmasGuardadasScreen(navController = navController, viewModel = viewModel) }
        composable("ConfigurarAlarma") { ConfigurarAlarmaScreen(navController = navController, viewModel = viewModel) }
    }

}