package ir.rezarasuolzadeh.passengers.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ir.rezarasuolzadeh.passengers.model.InfoModel
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.utils.constants.Constants.PAGE_SIZE
import ir.rezarasuolzadeh.passengers.utils.interfaces.DataSource
import ir.rezarasuolzadeh.passengers.utils.interfaces.Repository
import ir.rezarasuolzadeh.passengers.utils.request.PassengerRequest
import javax.inject.Inject

@ActivityRetainedScoped
class PassengerRepository @Inject constructor(
    private val passengerDataSource: DataSource<PassengerRequest, InfoModel>
) : Repository {

    private var passengers = ArrayList<PassengerModel>()
    private var currentPage = 0

    override suspend fun getPassengers(): List<PassengerModel> {
        val response = passengerDataSource.fetch(
            PassengerRequest(
                page = currentPage,
                size = PAGE_SIZE
            )
        )

        passengers.addAll(response.data)
        currentPage++

        return passengers
    }

}