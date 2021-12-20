package ir.rezarasuolzadeh.passengers.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoResponse(
    @Json(name = "totalPassengers")
    val totalPassengers: Int?,
    @Json(name = "data")
    val data: List<PassengerResponse> = emptyList(),
    @Json(name = "totalPages")
    val totalPages: Int?
)