package ir.rezarasuolzadeh.passengers.api

import ir.rezarasuolzadeh.passengers.response.InfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PassengerAPI {

    @GET("passenger")
    suspend fun getPassengers(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): InfoResponse

}