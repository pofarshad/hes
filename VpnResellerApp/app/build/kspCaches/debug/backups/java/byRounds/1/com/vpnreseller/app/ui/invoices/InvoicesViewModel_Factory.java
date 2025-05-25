package com.vpnreseller.app.ui.invoices;

import com.vpnreseller.core_data.repository.InvoiceRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class InvoicesViewModel_Factory implements Factory<InvoicesViewModel> {
  private final Provider<InvoiceRepository> invoiceRepositoryProvider;

  public InvoicesViewModel_Factory(Provider<InvoiceRepository> invoiceRepositoryProvider) {
    this.invoiceRepositoryProvider = invoiceRepositoryProvider;
  }

  @Override
  public InvoicesViewModel get() {
    return newInstance(invoiceRepositoryProvider.get());
  }

  public static InvoicesViewModel_Factory create(
      Provider<InvoiceRepository> invoiceRepositoryProvider) {
    return new InvoicesViewModel_Factory(invoiceRepositoryProvider);
  }

  public static InvoicesViewModel newInstance(InvoiceRepository invoiceRepository) {
    return new InvoicesViewModel(invoiceRepository);
  }
}
