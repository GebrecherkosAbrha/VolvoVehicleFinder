package com.example.volvovehiclefinder.data.remote

import com.example.volvovehiclefinder.model.Vehicle
import com.example.volvovehiclefinder.model.VehicleResponse
import javax.inject.Inject

class MockVehicleDataSource @Inject constructor() {
    private val mockVehicles = listOf(
        Vehicle(
            id = "xc90-1",
            model = "XC90 Recharge",
            year = 2024,
            price = 77900.00,
            mileage = 0,
            fuelType = "PLUG-IN HYBRID",
            transmission = "AUTOMATIC",
            imageUrl = "https://www.volvocars.com/images/v/-/media/project/contentplatform/data/media/my24/xc90-hybrid/xc90-recharge-my24-responsive.jpg",
            description = "Luxury 7-seat SUV with advanced safety features and plug-in hybrid technology"
        ),
        Vehicle(
            id = "xc60-1",
            model = "XC60",
            year = 2024,
            price = 49900.00,
            mileage = 0,
            fuelType = "MILD HYBRID",
            transmission = "AUTOMATIC",
            imageUrl = "https://www.volvocars.com/images/v/-/media/project/contentplatform/data/media/my24/xc60/xc60-my24-responsive.jpg",
            description = "Mid-size luxury SUV with Scandinavian design and comfort"
        ),
        Vehicle(
            id = "s90-1",
            model = "S90",
            year = 2024,
            price = 57900.00,
            mileage = 0,
            fuelType = "MILD HYBRID",
            transmission = "AUTOMATIC",
            imageUrl = "https://www.volvocars.com/images/v/-/media/project/contentplatform/data/media/my24/s90/s90-my24-responsive.jpg",
            description = "Executive sedan with premium features and elegant design"
        ),
        Vehicle(
            id = "v60-1",
            model = "V60 Cross Country",
            year = 2024,
            price = 48800.00,
            mileage = 0,
            fuelType = "MILD HYBRID",
            transmission = "AUTOMATIC",
            imageUrl = "https://www.volvocars.com/images/v/-/media/project/contentplatform/data/media/my24/v60-cross-country/v60-cross-country-my24-responsive.jpg",
            description = "Versatile wagon with all-road capability and refined comfort"
        )
    )

    fun getVehicles(): VehicleResponse {
        return VehicleResponse(mockVehicles)
    }

    fun searchVehicles(query: String): List<Vehicle> {
        return mockVehicles.filter { vehicle ->
            vehicle.model.contains(query, ignoreCase = true) ||
            vehicle.description.contains(query, ignoreCase = true) ||
            vehicle.fuelType.contains(query, ignoreCase = true) ||
            vehicle.year.toString().contains(query)
        }
    }
}
