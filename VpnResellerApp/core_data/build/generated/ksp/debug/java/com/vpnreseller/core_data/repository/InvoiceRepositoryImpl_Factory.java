package com.vpnreseller.core_data.repository;

import com.vpnreseller.core_data.google.GoogleSheetsService;
import com.vpnreseller.core_data.local.dao.InvoiceDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class InvoiceRepositoryImpl_Factory implements Factory<InvoiceRepositoryImpl> {
  private final Provider<InvoiceDao> invoiceDaoProvider;

  private final Provider<GoogleSheetsService> googleSheetsServiceProvider;

  public InvoiceRepositoryImpl_Factory(Provider<InvoiceDao> invoiceDaoProvider,
      Provider<GoogleSheetsService> googleSheetsServiceProvider) {
    this.invoiceDaoProvider = invoiceDaoProvider;
    this.googleSheetsServiceProvider = googleSheetsServiceProvider;
  }

  @Override
  public InvoiceRepositoryImpl get() {
    return newInstance(invoiceDaoProvider.get(), googleSheetsServiceProvider.get());
  }

  public static InvoiceRepositoryImpl_Factory create(Provider<InvoiceDao> invoiceDaoProvider,
      Provider<GoogleSheetsService> googleSheetsServiceProvider) {
    return new InvoiceRepositoryImpl_Factory(invoiceDaoProvider, googleSheetsServiceProvider);
  }

  public static InvoiceRepositoryImpl newInstance(InvoiceDao invoiceDao,
      GoogleSheetsService googleSheetsService) {
    return new InvoiceRepositoryImpl(invoiceDao, googleSheetsService);
  }
}
