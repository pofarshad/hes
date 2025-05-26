package com.vpnreseller.core_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vpnreseller.core_data.local.dao.InvoiceDao
import com.vpnreseller.core_data.local.dao.InvoiceLineItemDao
import com.vpnreseller.core_data.local.dao.RepresentativeDao
import com.vpnreseller.core_data.local.entity.Converters
import com.vpnreseller.core_data.local.entity.InvoiceEntity
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import com.vpnreseller.core_data.local.entity.RepresentativeEntity

/**
 * Main database class for the VPN Reseller application.
 * Defines all entities and provides access to DAOs.
 */
@Database(
    entities = [
        RepresentativeEntity::class,
        InvoiceEntity::class,
        InvoiceLineItemEntity::class
    ],
    version = 1,
    exportSchema = true // Enable schema export for version control
)
@TypeConverters(Converters::class)
abstract class VpnResellerDatabase : RoomDatabase() {
    abstract fun representativeDao(): RepresentativeDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun invoiceLineItemDao(): InvoiceLineItemDao

    companion object {
        const val DATABASE_NAME = "vpn_reseller_db"
    }
}

/**
 * Database migrations will be defined here when needed.
 * Example:
 *
 * val MIGRATION_1_2 = object : Migration(1, 2) {
 *     override fun migrate(database: SupportSQLiteDatabase) {
 *         database.execSQL(...)
 *     }
 * }
 */
