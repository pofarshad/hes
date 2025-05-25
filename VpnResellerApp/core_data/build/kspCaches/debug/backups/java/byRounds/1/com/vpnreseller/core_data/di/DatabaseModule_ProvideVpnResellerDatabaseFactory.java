package com.vpnreseller.core_data.di;

import android.content.Context;
import com.vpnreseller.core_data.local.database.VpnResellerDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideVpnResellerDatabaseFactory implements Factory<VpnResellerDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideVpnResellerDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public VpnResellerDatabase get() {
    return provideVpnResellerDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideVpnResellerDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideVpnResellerDatabaseFactory(contextProvider);
  }

  public static VpnResellerDatabase provideVpnResellerDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideVpnResellerDatabase(context));
  }
}
