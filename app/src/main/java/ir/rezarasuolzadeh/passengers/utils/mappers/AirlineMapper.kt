package ir.rezarasuolzadeh.passengers.utils.mappers

import ir.rezarasuolzadeh.passengers.model.AirlineModel
import ir.rezarasuolzadeh.passengers.response.AirlineResponse
import ir.rezarasuolzadeh.passengers.utils.interfaces.Mapper
import javax.inject.Inject

class AirlineMapper @Inject constructor() : Mapper<AirlineResponse, AirlineModel> {

    override fun mapResponseToModel(response: AirlineResponse): AirlineModel {
        return AirlineModel(
            established = response.established ?: "",
            country = response.country ?: "",
            website = response.website ?: "",
            name = response.name ?: "",
            headQuarters = response.headQuarters ?: "",
            logo = response.logo ?: "",
            id = response.id ?: -1,
            slogan = response.slogan ?: ""
        )
    }

    override fun mapModelToResponse(model: AirlineModel): AirlineResponse {
        return AirlineResponse(
            established = model.established,
            country = model.country,
            website = model.website,
            name = model.name,
            headQuarters = model.headQuarters,
            logo = model.logo,
            id = model.id,
            slogan = model.slogan
        )
    }

}