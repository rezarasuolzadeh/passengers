package ir.rezarasuolzadeh.passengers.utils.state

import ir.rezarasuolzadeh.passengers.model.PassengerModel

sealed class ApiState {
    object Empty : ApiState()
    data class Loading(val passengers: List<PassengerModel>) : ApiState()
    data class Success(val passengers: List<PassengerModel>) : ApiState()
    data class Failure(val passengers: List<PassengerModel>, val msg: Throwable) : ApiState()
}