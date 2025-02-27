package com.example.volvovehiclefinder.data.repository

import com.example.volvovehiclefinder.data.local.VehicleDao
import com.example.volvovehiclefinder.data.remote.VehicleApiService
import com.example.volvovehiclefinder.model.Vehicle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepository @Inject constructor(
    private val vehicleDao: VehicleDao,
    private val vehicleApiService: VehicleApiService
) {
    val vehicles: Flow<List<Vehicle>> = vehicleDao.getAllVehicles()

    suspend fun refreshVehicles() {
        try {
            val vehicles = vehicleApiService.getVehicles().vehicles
            vehicleDao.insertVehicles(vehicles)
        } catch (e: Exception) {
            // Handle error
        }
    }

    suspend fun searchVehicles(query: String): List<Vehicle> {
        return try {
            vehicleApiService.searchVehicles(query).vehicles
        } catch (e: Exception) {
            // Return cached results if API call fails
            emptyList()
        }
    }

    suspend fun getVehicleById(id: String): Vehicle? {
        return vehicleDao.getVehicleById(id)
    }
}
