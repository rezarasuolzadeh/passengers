package ir.rezarasuolzadeh.passengers.utils.mappers

import ir.rezarasuolzadeh.passengers.model.AirlineModel
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.response.AirlineResponse
import ir.rezarasuolzadeh.passengers.response.PassengerResponse
import ir.rezarasuolzadeh.passengers.utils.interfaces.Mapper
import javax.inject.Inject

class PassengerMapper @Inject constructor(
    private val airlineMapper: Mapper<AirlineResponse, AirlineModel>
) : Mapper<PassengerResponse, PassengerModel> {

    override fun mapResponseToModel(response: PassengerResponse): PassengerModel {
        return PassengerModel(
            trips = response.trips ?: -1,
            V = response.V ?: -1,
            name = response.name ?: "NO NAME",
            id = response.id ?: "",
            airline = response.airline.map { airlineMapper.mapResponseToModel(it) }
        )
    }

    override fun mapModelToResponse(model: PassengerModel): PassengerResponse {
        return PassengerResponse(
            trips = model.trips,
            V = model.V,
            name = model.name,
            id = model.id,
            airline = model.airline.map { airlineMapper.mapModelToResponse(it) }
        )
    }

}