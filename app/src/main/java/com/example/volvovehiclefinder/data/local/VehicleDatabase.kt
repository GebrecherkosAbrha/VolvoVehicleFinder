package com.example.volvovehiclefinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.volvovehiclefinder.model.Vehicle

@Database(entities = [Vehicle::class], version = 1)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}
