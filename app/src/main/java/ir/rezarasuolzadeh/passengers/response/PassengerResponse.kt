package ir.rezarasuolzadeh.passengers.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PassengerResponse(
    @Json(name = "trips")
    val trips: Int?,
    @Json(name = "__v")
    val V: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "_id")
    val id: String?,
    @Json(name = "airline")
    val airline: List<AirlineResponse> = emptyList()
)