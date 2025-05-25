package com.vpnreseller.core_data.di;

import com.vpnreseller.core_data.local.dao.PaymentTransactionDao;
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
public final class DatabaseModule_ProvidePaymentTransactionDaoFactory implements Factory<PaymentTransactionDao> {
  private final Provider<VpnResellerDatabase> databaseProvider;

  public DatabaseModule_ProvidePaymentTransactionDaoFactory(
      Provider<VpnResellerDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PaymentTransactionDao get() {
    return providePaymentTransactionDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePaymentTransactionDaoFactory create(
      Provider<VpnResellerDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePaymentTransactionDaoFactory(databaseProvider);
  }

  public static PaymentTransactionDao providePaymentTransactionDao(VpnResellerDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePaymentTransactionDao(database));
  }
}
