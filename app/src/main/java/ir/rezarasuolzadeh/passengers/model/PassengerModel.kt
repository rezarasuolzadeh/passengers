package ir.rezarasuolzadeh.passengers.model

data class PassengerModel(
    val trips: Int,
    val V: Int,
    val name: String,
    val id: String,
    val airline: List<AirlineModel>
)