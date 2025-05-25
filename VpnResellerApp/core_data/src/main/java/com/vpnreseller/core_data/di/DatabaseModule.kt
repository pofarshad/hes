package com.vpnreseller.core_data.di

import android.content.Context
import androidx.room.Room
import com.vpnreseller.core_data.local.dao.InvoiceDao
import com.vpnreseller.core_data.local.dao.PaymentTransactionDao
import com.vpnreseller.core_data.local.dao.RepresentativeDao
import com.vpnreseller.core_data.local.database.VpnResellerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for database dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideVpnResellerDatabase(
        @ApplicationContext context: Context
    ): VpnResellerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            VpnResellerDatabase::class.java,
            VpnResellerDatabase.DATABASE_NAME
        ).build()
    }
    
    @Provides
    fun provideRepresentativeDao(database: VpnResellerDatabase): RepresentativeDao {
        return database.representativeDao()
    }
    
    @Provides
    fun provideInvoiceDao(database: VpnResellerDatabase): InvoiceDao {
        return database.invoiceDao()
    }
    
    @Provides
    fun providePaymentTransactionDao(database: VpnResellerDatabase): PaymentTransactionDao {
        return database.paymentTransactionDao()
    }
}
