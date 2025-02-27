package com.example.volvovehiclefinder.viewmodel

import com.example.volvovehiclefinder.data.repository.VehicleRepository
import com.example.volvovehiclefinder.model.Vehicle
import com.example.volvovehiclefinder.model.VehicleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class VehicleViewModelTest {
    private lateinit var viewModel: VehicleViewModel
    private lateinit var repository: VehicleRepository
    private val testDispatcher = StandardTestDispatcher()

    private val testVehicle = Vehicle(
        id = "test-1",
        model = "XC90",
        year = 2024,
        price = 55900.00,
        mileage = 0,
        fuelType = "HYBRID",
        transmission = "AUTOMATIC",
        imageUrl = "https://example.com/xc90.jpg",
        description = "Test vehicle"
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        whenever(repository.vehicles).thenReturn(flowOf(listOf(testVehicle)))
        viewModel = VehicleViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadVehicles should update state with vehicles from repository`() = runTest {
        // Given
        val expected = VehicleUiState.Success(listOf(testVehicle))

        // When
        viewModel.loadVehicles()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.uiState.value is VehicleUiState.Success)
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `searchVehicles should filter vehicles based on query`() = runTest {
        // Given
        val query = "XC90"
        whenever(repository.searchVehicles(query)).thenReturn(listOf(testVehicle))

        // When
        viewModel.searchVehicles(query)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.uiState.value is VehicleUiState.Success)
        val vehicles = (viewModel.uiState.value as VehicleUiState.Success).vehicles
        assertTrue(vehicles.all { it.model.contains(query) })
    }

    @Test
    fun `searchVehicles should handle empty results`() = runTest {
        // Given
        val query = "nonexistent"
        whenever(repository.searchVehicles(query)).thenReturn(emptyList())

        // When
        viewModel.searchVehicles(query)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.uiState.value is VehicleUiState.Success)
        val vehicles = (viewModel.uiState.value as VehicleUiState.Success).vehicles
        assertTrue(vehicles.isEmpty())
    }
}
