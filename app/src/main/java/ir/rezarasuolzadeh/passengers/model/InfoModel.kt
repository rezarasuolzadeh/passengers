package ir.rezarasuolzadeh.passengers.model

data class InfoModel(
    val totalPassengers: Int,
    val data: List<PassengerModel>,
    val totalPages: Int
)