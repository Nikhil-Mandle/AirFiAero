package com.nikhilproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.domain.model.Airline
import com.nikhilproject.domain.usecase.GetAirlinesUseCase
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirlineViewModel @Inject constructor(
    private val getAirlinesUseCase: GetAirlinesUseCase,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _airLineListUiState = MutableStateFlow<UiState<List<Airline>>>(UiState.Loading)
    val airLineListUiState: StateFlow<UiState<List<Airline>>> = _airLineListUiState

    private val _filteredAirlines = MutableStateFlow<List<Airline>>(emptyList())
    val filteredAirlines: StateFlow<List<Airline>> = _filteredAirlines

    private val _selectedAirline = MutableStateFlow<Airline?>(null)
    val selectedAirline: StateFlow<Airline?> = _selectedAirline

    init {
        fetchAirlines()
        observeSearchQuery()
    }


    private fun fetchAirlines() {
        viewModelScope.launch {
            _airLineListUiState.value = UiState.Loading

            if (!networkUtils.isNetworkAvailable()) {
                _airLineListUiState.value = UiState.Error("Please check your internet connection")
                return@launch
            }

            try {
                getAirlinesUseCase().collect { airlines ->
                    _airLineListUiState.value = UiState.Success(airlines)
                    _filteredAirlines.value = airlines
                }
            } catch (e: Exception) {
                _airLineListUiState.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            combine(_searchQuery, _airLineListUiState) { query, state ->
                if (state is UiState.Success) {
                    state.data.filter {
                        it.name.contains(query, ignoreCase = true)
                    }
                } else {
                    emptyList()
                }
            }.collect {
                _filteredAirlines.value = it
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun selectAirlineById(id: String) {
        viewModelScope.launch {
            airLineListUiState
                .filterIsInstance<UiState.Success<List<Airline>>>()
                .firstOrNull()
                ?.let { successState ->
                    val found = successState.data.find { it.id == id }
                    _selectedAirline.value = found
                }
        }
    }
}
