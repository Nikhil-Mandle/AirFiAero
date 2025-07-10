package com.nikhilproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.nikhilproject.presentation.screen.AirlineDetailScreen
import com.nikhilproject.presentation.screen.AirlineListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AirlineListScreenNav,
        modifier = modifier
    ) {
        composable<AirlineListScreenNav> {
            AirlineListScreen(
            ) { airlineId ->
                navController.navigate(AirlineDetailScreenNav(airlineId))
            }
        }

        composable<AirlineDetailScreenNav> { navEntry ->
            val args = navEntry.toRoute<AirlineDetailScreenNav>()
            AirlineDetailScreen(airlineId = args.airlineId)
        }
    }
}