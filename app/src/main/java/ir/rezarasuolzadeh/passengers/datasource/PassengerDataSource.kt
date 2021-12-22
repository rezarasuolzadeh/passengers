package ir.rezarasuolzadeh.passengers.datasource

import ir.rezarasuolzadeh.passengers.api.PassengerAPI
import ir.rezarasuolzadeh.passengers.model.InfoModel
import ir.rezarasuolzadeh.passengers.utils.interfaces.DataSource
import ir.rezarasuolzadeh.passengers.utils.mappers.InfoMapper
import ir.rezarasuolzadeh.passengers.utils.request.PassengerRequest
import javax.inject.Inject

class PassengerDataSource @Inject constructor(
    private val api: PassengerAPI,
    private val infoMapper: InfoMapper
) : DataSource<PassengerRequest, InfoModel> {

    override suspend fun fetch(input: PassengerRequest): InfoModel {
        return infoMapper.mapResponseToModel(
            api.getPassengers(
                page = input.page,
                size = input.size
            )
        )
    }

}