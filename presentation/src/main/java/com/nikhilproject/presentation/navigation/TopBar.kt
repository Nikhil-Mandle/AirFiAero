package com.nikhilproject.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigationIcon: ImageVector? = null,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    onNavigationIconClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.padding(end = 8.dp),
        title = { Text(text = title, textAlign = TextAlign.Center) },
        actions = {
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { onThemeToggle(it) }
            )

        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigationIconClick()
            }) {
                (navigationIcon)?.let { Icon(it, contentDescription = "") }
            }
        }
    )
}