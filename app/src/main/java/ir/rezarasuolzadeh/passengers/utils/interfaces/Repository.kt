package ir.rezarasuolzadeh.passengers.utils.interfaces

import ir.rezarasuolzadeh.passengers.model.PassengerModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getPassengers(): Flow<List<PassengerModel>>
}