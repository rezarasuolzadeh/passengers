package ir.rezarasuolzadeh.passengers.utils.mappers

import ir.rezarasuolzadeh.passengers.model.InfoModel
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.response.InfoResponse
import ir.rezarasuolzadeh.passengers.response.PassengerResponse
import ir.rezarasuolzadeh.passengers.utils.interfaces.Mapper
import javax.inject.Inject

class InfoMapper @Inject constructor(
    private val passengerMapper: Mapper<PassengerResponse, PassengerModel>
) : Mapper<InfoResponse, InfoModel> {

    override fun mapResponseToModel(response: InfoResponse): InfoModel {
        return InfoModel(
            totalPassengers = response.totalPassengers ?: -1,
            data = response.data.map { passengerMapper.mapResponseToModel(it) },
            totalPages = response.totalPages ?: -1
        )
    }

    override fun mapModelToResponse(model: InfoModel): InfoResponse {
        return InfoResponse(
            totalPassengers = model.totalPassengers,
            data = model.data.map { passengerMapper.mapModelToResponse(it) },
            totalPages = model.totalPages
        )
    }

}