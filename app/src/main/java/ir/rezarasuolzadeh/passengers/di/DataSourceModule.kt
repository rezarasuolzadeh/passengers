package ir.rezarasuolzadeh.passengers.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.rezarasuolzadeh.passengers.datasource.PassengerDataSource
import ir.rezarasuolzadeh.passengers.model.InfoModel
import ir.rezarasuolzadeh.passengers.utils.interfaces.DataSource
import ir.rezarasuolzadeh.passengers.utils.request.PassengerRequest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindPassengerDataSource(dataSource: PassengerDataSource): DataSource<PassengerRequest, InfoModel>

}