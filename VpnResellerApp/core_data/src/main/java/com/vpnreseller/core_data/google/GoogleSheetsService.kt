package com.vpnreseller.core_data.google

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.ValueRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.security.GeneralSecurityException

class GoogleSheetsService(
    private val credentialsStream: InputStream,
    private val applicationName: String
) {
    private val jsonFactory = GsonFactory.getDefaultInstance()
    private val httpTransport = GoogleNetHttpTransport.newTrustedTransport()

    private val sheetsService: Sheets = Sheets.Builder(httpTransport, jsonFactory, GoogleCredentialHelper.getCredentials(credentialsStream))
        .setApplicationName(applicationName)
        .build()

    suspend fun getSheetValues(spreadsheetId: String, range: String): List<List<Any>> = withContext(Dispatchers.IO) {
        val response: ValueRange = sheetsService.spreadsheets().values()
            .get(spreadsheetId, range)
            .execute()
        response.getValues() ?: emptyList()
    }
}
