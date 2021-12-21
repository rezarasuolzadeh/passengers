package ir.rezarasuolzadeh.passengers.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ir.rezarasuolzadeh.passengers.api.PassengerAPI
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.utils.interfaces.Repository
import ir.rezarasuolzadeh.passengers.utils.mappers.PassengerMapper
import javax.inject.Inject

@ActivityRetainedScoped
class PassengerRepository @Inject constructor(
    private val passengerAPI: PassengerAPI,
    private val passengerMapper: PassengerMapper
) : Repository {

    override suspend fun getPassengers(): List<PassengerModel> {
        return passengerAPI.getPassengers(page = 10, size = 15).data.map {
            passengerMapper.mapResponseToModel(
                it
            )
        }
    }

}