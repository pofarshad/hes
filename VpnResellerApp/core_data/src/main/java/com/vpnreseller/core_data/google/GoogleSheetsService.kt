package com.vpnreseller.core_data.google

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.ValueRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.security.GeneralSecurityException
import javax.inject.Inject
import javax.inject.Named

class GoogleSheetsService @Inject constructor(
    private val credentialsStream: InputStream?, // Changed to nullable
    @Named("applicationName") private val applicationName: String
) {
    private val jsonFactory = GsonFactory.getDefaultInstance()
    private val httpTransport by lazy { GoogleNetHttpTransport.newTrustedTransport() } // Lazy init

    // Initialize sheetsService lazily and only if credentialsStream is not null
    private val sheetsService: Sheets? by lazy {
        credentialsStream?.let { stream ->
            try {
                Sheets.Builder(httpTransport, jsonFactory, GoogleCredentialHelper.getCredentials(stream))
                    .setApplicationName(applicationName)
                    .build()
            } catch (e: GeneralSecurityException) {
                // Log error or handle appropriately
                null // Failed to initialize
            } catch (e: Exception) {
                // Catch any other potential exceptions during initialization
                null
            }
        }
    }

    // Custom exception for when service is not initialized
    class ServiceNotInitializedException(message: String = "GoogleSheetsService not initialized. Credentials might be missing or invalid.") : IllegalStateException(message)

    suspend fun getSheetValues(spreadsheetId: String, range: String): List<List<Any>> = withContext(Dispatchers.IO) {
        val service = sheetsService ?: throw ServiceNotInitializedException()
        val response: ValueRange = service.spreadsheets().values()
            .get(spreadsheetId, range)
            .execute()
        response.getValues() ?: emptyList()
    }
}
