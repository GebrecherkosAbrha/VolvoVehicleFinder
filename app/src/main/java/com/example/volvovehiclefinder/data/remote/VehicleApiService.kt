package com.example.volvovehiclefinder.data.remote

import com.example.volvovehiclefinder.model.VehicleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApiService {
    @GET("vehicles")
    suspend fun getVehicles(): VehicleResponse

    @GET("vehicles/search")
    suspend fun searchVehicles(
        @Query("query") query: String
    ): VehicleResponse
}
