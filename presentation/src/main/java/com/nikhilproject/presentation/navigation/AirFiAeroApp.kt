package com.nikhilproject.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nikhilproject.presentation.viewmodel.SettingsViewModel


@Composable
fun AirFiAeroApp(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topBarConfig = getTopBarConfig(currentRoute, navController, drawerState, scope)
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val isDark by settingsViewModel.isDarkTheme.collectAsState()

    val content: @Composable () -> Unit = {
        Scaffold(
            topBar = {
                topBarConfig?.let { config ->
                    TopBar(
                        title = config.title,
                        navigationIcon = config.icon,
                        isDarkTheme = isDark,
                        onThemeToggle = { settingsViewModel.setTheme(it) },
                    ) {
                        config.onNavigationClick?.invoke()
                    }
                }
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

    content()
}
