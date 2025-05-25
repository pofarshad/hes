package com.vpnreseller.core_data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.vpnreseller.core_data.local.dao.InvoiceDao
import com.vpnreseller.core_data.local.entity.InvoiceEntity
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity
import com.vpnreseller.core_domain.model.Invoice
import com.vpnreseller.core_domain.model.InvoiceLineItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class InvoiceRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var invoiceDao: InvoiceDao
    private lateinit var invoiceRepository: InvoiceRepositoryImpl

    @Before
    fun setup() {
        invoiceDao = mock()
        invoiceRepository = InvoiceRepositoryImpl(invoiceDao, mock())
    }

    @Test
    fun getAllInvoices_returnsInvoices() = runBlocking {
        val invoiceEntity = InvoiceEntity(
            id = "1",
            representativeId = "rep1",
            generationDate = System.currentTimeMillis(),
            totalAmount = 100.0,
            status = "UNPAID",
            isSentToTelegram = false,
            pdfPath = null,
            imagePath = null,
            importedSheetData = null
        )
        whenever(invoiceDao.getAllInvoices()).thenReturn(flowOf(listOf(invoiceEntity)))

        val invoices = invoiceRepository.getAllInvoices()

        invoices.collect { list ->
            assertThat(list).hasSize(1)
            assertThat(list[0].id).isEqualTo("1")
        }
    }

    @Test
    fun insertInvoice_callsDaoInsert() = runBlocking {
        val invoice = Invoice(
            id = "1",
            representativeId = "rep1",
            totalAmount = 100.0
        )
        val lineItems = listOf<InvoiceLineItem>()

        invoiceRepository.insertInvoice(invoice, lineItems)

        verify(invoiceDao).insertInvoice(any())
        verify(invoiceDao).insertLineItems(any())
    }
}
