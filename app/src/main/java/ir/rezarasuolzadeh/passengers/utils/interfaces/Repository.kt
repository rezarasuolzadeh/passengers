package ir.rezarasuolzadeh.passengers.utils.interfaces

import ir.rezarasuolzadeh.passengers.model.PassengerModel

interface Repository {
    suspend fun getPassengers(): List<PassengerModel>
}