package com.vpnreseller.core_data.di;

import com.vpnreseller.core_data.local.dao.RepresentativeDao;
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
public final class DatabaseModule_ProvideRepresentativeDaoFactory implements Factory<RepresentativeDao> {
  private final Provider<VpnResellerDatabase> databaseProvider;

  public DatabaseModule_ProvideRepresentativeDaoFactory(
      Provider<VpnResellerDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RepresentativeDao get() {
    return provideRepresentativeDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideRepresentativeDaoFactory create(
      Provider<VpnResellerDatabase> databaseProvider) {
    return new DatabaseModule_ProvideRepresentativeDaoFactory(databaseProvider);
  }

  public static RepresentativeDao provideRepresentativeDao(VpnResellerDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRepresentativeDao(database));
  }
}
