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

    private var passengers = ArrayList<PassengerModel>()
    private var currentPage = 0

    override suspend fun getPassengers(): List<PassengerModel> {
        passengers.addAll(
            passengerAPI.getPassengers(page = currentPage, size = PAGE_SIZE).data.map {
                passengerMapper.mapResponseToModel(
                    it
                )
            }
        )
        currentPage ++
        return passengers
    }

    companion object {
        const val PAGE_SIZE = 10
    }

}