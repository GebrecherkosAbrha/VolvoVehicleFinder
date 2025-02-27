package com.example.volvovehiclefinder.di

import android.content.Context
import androidx.room.Room
import com.example.volvovehiclefinder.data.local.VehicleDatabase
import com.example.volvovehiclefinder.data.remote.VehicleApiService
import com.example.volvovehiclefinder.data.remote.MockVehicleDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideVehicleDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        VehicleDatabase::class.java,
        "vehicle_database"
    ).build()

    @Provides
    @Singleton
    fun provideVehicleDao(database: VehicleDatabase) = database.vehicleDao()

    @Provides
    @Singleton
    fun provideMockDataSource(): MockVehicleDataSource = MockVehicleDataSource()

    @Provides
    @Singleton
    fun provideVehicleApiService(mockDataSource: MockVehicleDataSource): VehicleApiService =
        object : VehicleApiService {
            override suspend fun getVehicles(): VehicleResponse = mockDataSource.getVehicles()
            override suspend fun searchVehicles(query: String): VehicleResponse = mockDataSource.searchVehicles(query)
        }
}
