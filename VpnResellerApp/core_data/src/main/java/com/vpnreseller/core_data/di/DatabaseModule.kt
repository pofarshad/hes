package com.vpnreseller.core_data.di

import android.content.Context
import androidx.room.Room
import com.vpnreseller.core_data.local.VpnResellerDatabase
import com.vpnreseller.core_data.local.dao.InvoiceDao
import com.vpnreseller.core_data.local.dao.InvoiceLineItemDao
import com.vpnreseller.core_data.local.dao.RepresentativeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): VpnResellerDatabase {
        return Room.databaseBuilder(
            context,
            VpnResellerDatabase::class.java,
            VpnResellerDatabase.DATABASE_NAME
        )
        .fallbackToDestructiveMigration() // TODO: Replace with proper migrations when needed
        .build()
    }

    @Provides
    fun provideInvoiceDao(database: VpnResellerDatabase): InvoiceDao {
        return database.invoiceDao()
    }

    @Provides
    fun provideInvoiceLineItemDao(database: VpnResellerDatabase): InvoiceLineItemDao {
        return database.invoiceLineItemDao()
    }

    @Provides
    fun provideRepresentativeDao(database: VpnResellerDatabase): RepresentativeDao {
        return database.representativeDao()
    }
}

/**
 * Repository bindings module.
 * Binds repository implementations to their interfaces.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindInvoiceRepository(
        impl: InvoiceRepositoryImpl
    ): InvoiceRepository

    // TODO: Add other repository bindings as needed
}
