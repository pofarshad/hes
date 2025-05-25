package com.vpnreseller.app.ui.invoices

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.InvoiceStatus
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class InvoiceListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val sampleInvoice = Invoice(
        id = "inv1",
        representativeId = "rep1",
        generationDate = Date(),
        totalAmount = 123.45,
        status = InvoiceStatus.UNPAID
    )

    @Test
    fun invoiceListScreen_displaysInvoice() {
        composeTestRule.setContent {
            InvoiceListScreen(
                invoices = listOf(sampleInvoice),
                onNavigateToInvoiceDetail = {},
                onNavigateToImport = {}
            )
        }

        composeTestRule.onNodeWithText("Invoice ID: inv1").assertExists()
        composeTestRule.onNodeWithText("Representative ID: rep1").assertExists()
        composeTestRule.onNodeWithText("Total: 123.45").assertExists()
        composeTestRule.onNodeWithText("Status: UNPAID").assertExists()
    }
}
