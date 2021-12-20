package ir.rezarasuolzadeh.passengers.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.rezarasuolzadeh.passengers.model.AirlineModel
import ir.rezarasuolzadeh.passengers.model.InfoModel
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.response.AirlineResponse
import ir.rezarasuolzadeh.passengers.response.InfoResponse
import ir.rezarasuolzadeh.passengers.response.PassengerResponse
import ir.rezarasuolzadeh.passengers.utils.interfaces.Mapper
import ir.rezarasuolzadeh.passengers.utils.mappers.AirlineMapper
import ir.rezarasuolzadeh.passengers.utils.mappers.InfoMapper
import ir.rezarasuolzadeh.passengers.utils.mappers.PassengerMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindInfoMapper(mapper: InfoMapper): Mapper<InfoResponse, InfoModel>

    @Binds
    @Singleton
    abstract fun bindPassengerMapper(mapper: PassengerMapper): Mapper<PassengerResponse, PassengerModel>

    @Binds
    @Singleton
    abstract fun bindAirlineMapper(mapper: AirlineMapper): Mapper<AirlineResponse, AirlineModel>

}