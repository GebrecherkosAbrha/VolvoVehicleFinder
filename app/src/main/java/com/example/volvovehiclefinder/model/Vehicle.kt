package com.example.volvovehiclefinder.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class Vehicle(
    @PrimaryKey
    val id: String,
    val model: String,
    val year: Int,
    val price: Double,
    val mileage: Int,
    val fuelType: String,
    val transmission: String,
    val imageUrl: String,
    val description: String
)

data class VehicleResponse(
    val vehicles: List<Vehicle>
)

enum class FuelType {
    PETROL, DIESEL, HYBRID, ELECTRIC
}

enum class TransmissionType {
    AUTOMATIC, MANUAL
}
