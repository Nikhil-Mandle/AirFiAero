package com.nikhilproject.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun getTopBarConfig(
    currentRoute: String?,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
): TopBarConfig? {
    return when {
        currentRoute == AirlineListScreenNav::class.qualifiedName -> TopBarConfig(
            title = "AirFi Aero",
            onNavigationClick = {

            }
        )

        currentRoute?.startsWith(
            AirlineDetailScreenNav::class.qualifiedName ?: ""
        ) == true -> TopBarConfig(
            title = "Airline Detail",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        else -> null
    }
}

data class TopBarConfig(
    val title: String,
    val icon: ImageVector? = null,
    val endIcon: ImageVector? = null,
    val onEndIconClick: (() -> Unit)? = null,
    val onNavigationClick: (() -> Unit)? = null
)