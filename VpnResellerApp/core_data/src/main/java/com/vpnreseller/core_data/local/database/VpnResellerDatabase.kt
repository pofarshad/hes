package com.vpnreseller.core_data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters // Import TypeConverters
import android.content.Context
import com.vpnreseller.core_data.local.database.converters.MapConverter // Import MapConverter
import com.vpnreseller.core_data.local.dao.InvoiceDao
import com.vpnreseller.core_data.local.dao.PaymentTransactionDao
import com.vpnreseller.core_data.local.dao.RepresentativeDao
import com.vpnreseller.core_data.local.entity.InvoiceEntity // Changed from InvoiceHeaderEntity
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import com.vpnreseller.core_data.local.entity.PaymentTransactionEntity
import com.vpnreseller.core_data.local.entity.RepresentativeEntity

/**
 * Room database for VPN Reseller App
 * Contains all entities and provides access to DAOs
 */
@Database(
    entities = [
        RepresentativeEntity::class,
        InvoiceEntity::class, // Changed from InvoiceHeaderEntity
        InvoiceLineItemEntity::class,
        PaymentTransactionEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(MapConverter::class) // Added TypeConverter
abstract class VpnResellerDatabase : RoomDatabase() {
    
    abstract fun representativeDao(): RepresentativeDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun paymentTransactionDao(): PaymentTransactionDao
    
    companion object {
        const val DATABASE_NAME = "vpn_reseller_database"
        
        @Volatile
        private var INSTANCE: VpnResellerDatabase? = null
        
        fun getDatabase(context: Context): VpnResellerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VpnResellerDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
