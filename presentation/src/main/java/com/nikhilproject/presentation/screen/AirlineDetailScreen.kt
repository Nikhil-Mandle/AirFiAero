package com.nikhilproject.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.viewmodel.AirlineViewModel

@Composable
fun AirlineDetailScreen(
    airlineId: String,
    viewModel: AirlineViewModel = hiltViewModel()
) {
    val airline by viewModel.selectedAirline.collectAsState()
    val uiState by viewModel.airLineListUiState.collectAsState()

    LaunchedEffect(airlineId) {
        viewModel.selectAirlineById(airlineId)
    }

    when (uiState) {
        is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Error -> {
            Text("Error: ${(uiState as UiState.Error).message}", color = Color.Red)
        }

        is UiState.Success -> {
            if (airline == null) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Airline not found", color = Color.Red)
                }
            } else {
                airline?.let { a ->
                    val uriHandler = LocalUriHandler.current

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = a.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(text = "Country: ${a.country}", fontWeight = FontWeight.Bold)
                        Text(text = "Headquarters: ${a.headquarters}", fontWeight = FontWeight.Bold)
                        Text(text = "Fleet size: ${a.fleetSize}", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))

                        ClickableText(
                            text = AnnotatedString(a.website),
                            onClick = { uriHandler.openUri(a.website) },
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold
                            ),
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = Color.LightGray
                        )
                    }
                }
            }

        }
    }
}


@Preview
@Composable
private fun AirlineDetailScreenPreview() {
    AirlineDetailScreen("1")
}