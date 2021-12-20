package ir.rezarasuolzadeh.passengers.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirlineResponse(
    @Json(name = "established")
    val established: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "website")
    val website: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "head_quaters")
    val headQuarters: String?,
    @Json(name = "logo")
    val logo: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "slogan")
    val slogan: String?
)
