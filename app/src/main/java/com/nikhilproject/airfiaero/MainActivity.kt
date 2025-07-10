package com.nikhilproject.airfiaero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.nikhilproject.presentation.navigation.AirFiAeroApp
import com.nikhilproject.presentation.theme.AirlinesExplorerTheme
import com.nikhilproject.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val isDark by settingsViewModel.isDarkTheme.collectAsState()

            AirlinesExplorerTheme(darkTheme = isDark) {
                AirFiAeroApp(rememberNavController())
            }
        }
    }
}
