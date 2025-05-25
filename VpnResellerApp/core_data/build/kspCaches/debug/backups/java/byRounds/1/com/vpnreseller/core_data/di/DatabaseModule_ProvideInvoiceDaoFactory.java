package com.vpnreseller.core_data.di;

import com.vpnreseller.core_data.local.dao.InvoiceDao;
import com.vpnreseller.core_data.local.database.VpnResellerDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideInvoiceDaoFactory implements Factory<InvoiceDao> {
  private final Provider<VpnResellerDatabase> databaseProvider;

  public DatabaseModule_ProvideInvoiceDaoFactory(Provider<VpnResellerDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public InvoiceDao get() {
    return provideInvoiceDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideInvoiceDaoFactory create(
      Provider<VpnResellerDatabase> databaseProvider) {
    return new DatabaseModule_ProvideInvoiceDaoFactory(databaseProvider);
  }

  public static InvoiceDao provideInvoiceDao(VpnResellerDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideInvoiceDao(database));
  }
}
