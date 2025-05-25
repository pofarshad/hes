package com.vpnreseller.core_data.di

import com.vpnreseller.core_data.repository.InvoiceRepository
import com.vpnreseller.core_data.repository.InvoiceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InvoiceRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindInvoiceRepository(
        invoiceRepositoryImpl: InvoiceRepositoryImpl
    ): InvoiceRepository
}
