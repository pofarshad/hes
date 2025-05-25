package com.vpnreseller.core_data.google

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import java.io.InputStream

object GoogleCredentialHelper {
    fun getCredentials(credentialsStream: InputStream): GoogleCredential {
        return GoogleCredential.fromStream(credentialsStream)
            .createScoped(listOf("https://www.googleapis.com/auth/spreadsheets.readonly"))
    }
}
