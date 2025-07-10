package com.nikhilproject.presentation

import com.nikhilproject.domain.model.Airline
import com.nikhilproject.domain.usecase.GetAirlinesUseCase
import com.nikhilproject.presentation.viewmodel.AirlineViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AirlineViewModelTest {

    // Use StandardTestDispatcher for coroutine control
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getAirlinesUseCase: GetAirlinesUseCase
    private lateinit var viewModel: AirlineViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Override Main dispatcher
        getAirlinesUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Always reset
    }

    @Test
    fun `fetchAirlines should emit Success when use case returns data`() = runTest {
        // Arrange
        val mockData = listOf(
            Airline("1", "IndiGo", "India", "Mumbai", 350, "https://www.goindigo.in", "")
        )
        coEvery { getAirlinesUseCase() } returns flowOf(mockData)

        // Act
        viewModel = AirlineViewModel(getAirlinesUseCase)
        advanceUntilIdle() // Ensures coroutine finishes

        // Assert
        val state = viewModel.airLineListUiState.value
        assert(state is UiState.Success)
        assertEquals(mockData, (state as UiState.Success).data)
    }
}

