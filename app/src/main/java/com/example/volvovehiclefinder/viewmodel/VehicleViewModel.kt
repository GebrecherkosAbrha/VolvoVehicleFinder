package com.example.volvovehiclefinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volvovehiclefinder.data.repository.VehicleRepository
import com.example.volvovehiclefinder.model.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val repository: VehicleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<VehicleUiState>(VehicleUiState.Loading)
    val uiState: StateFlow<VehicleUiState> = _uiState

    init {
        loadVehicles()
    }

    fun loadVehicles() {
        viewModelScope.launch {
            repository.vehicles
                .catch { 
                    _uiState.value = VehicleUiState.Error("Failed to load vehicles")
                }
                .collect { vehicles ->
                    _uiState.value = VehicleUiState.Success(vehicles)
                }
        }
    }

    fun searchVehicles(query: String) {
        viewModelScope.launch {
            _uiState.value = VehicleUiState.Loading
            try {
                val results = repository.searchVehicles(query)
                _uiState.value = VehicleUiState.Success(results)
            } catch (e: Exception) {
                _uiState.value = VehicleUiState.Error("Failed to search vehicles")
            }
        }
    }

    fun refreshVehicles() {
        viewModelScope.launch {
            try {
                repository.refreshVehicles()
            } catch (e: Exception) {
                _uiState.value = VehicleUiState.Error("Failed to refresh vehicles")
            }
        }
    }
}

sealed class VehicleUiState {
    object Loading : VehicleUiState()
    data class Success(val vehicles: List<Vehicle>) : VehicleUiState()
    data class Error(val message: String) : VehicleUiState()
}
