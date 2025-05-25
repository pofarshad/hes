package com.vpnreseller.core_data.di

import com.vpnreseller.core_data.repository.RepresentativeRepository
import com.vpnreseller.core_data.repository.RepresentativeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepresentativeRepository(
        representativeRepositoryImpl: RepresentativeRepositoryImpl
    ): RepresentativeRepository
}
