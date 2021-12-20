package ir.rezarasuolzadeh.passengers.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.rezarasuolzadeh.passengers.repository.PassengerRepository
import ir.rezarasuolzadeh.passengers.utils.interfaces.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPassengerViewModel(newsRepository: PassengerRepository): Repository

}