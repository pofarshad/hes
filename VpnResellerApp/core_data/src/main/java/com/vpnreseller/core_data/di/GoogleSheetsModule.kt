package com.vpnreseller.core_data.di

import android.content.Context
import com.vpnreseller.core_data.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.FileNotFoundException
import java.io.InputStream
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleSheetsModule {

    @Provides
    @Singleton
    fun provideCredentialsInputStream(@ApplicationContext context: Context): InputStream? {
        // Assuming your google_credentials.json is in app/src/main/assets
        // If it's in core_data/src/main/assets, adjust the context accordingly
        // or find a way to access app's assets from core_data module.
        // For now, this assumes it's accessible via the application context's assets.
        // This might require the credentials file to be in the app module's assets.
        return try {
            context.assets.open("google_credentials.json")
        } catch (e: FileNotFoundException) {
            // Log the error or handle it as needed, for now, return null
            null
        }
    }

    @Provides
    @Singleton
    @Named("applicationName")
    fun provideApplicationName(@ApplicationContext context: Context): String {
        return context.getString(R.string.app_name)
    }
}
