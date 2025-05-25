package com.vpnreseller.app.ui.invoices

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.vpnreseller.app.R

@RunWith(AndroidJUnit4::class)
class InvoiceImportScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun invoiceImportScreen_inputAndImport() {
        var importClicked = false

        composeTestRule.setContent {
            InvoiceImportScreen(
                onImportComplete = { importClicked = true }
            )
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.label_spreadsheet_id))
            .performTextInput("spreadsheetId123")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.label_range))
            .performTextInput("Sheet1!A1:E10")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.button_import))
            .performClick()

        assert(importClicked)
    }
}
