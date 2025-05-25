package com.vpnreseller.core_data.google;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.io.InputStream;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
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
public final class GoogleSheetsService_Factory implements Factory<GoogleSheetsService> {
  private final Provider<InputStream> credentialsStreamProvider;

  private final Provider<String> applicationNameProvider;

  public GoogleSheetsService_Factory(Provider<InputStream> credentialsStreamProvider,
      Provider<String> applicationNameProvider) {
    this.credentialsStreamProvider = credentialsStreamProvider;
    this.applicationNameProvider = applicationNameProvider;
  }

  @Override
  public GoogleSheetsService get() {
    return newInstance(credentialsStreamProvider.get(), applicationNameProvider.get());
  }

  public static GoogleSheetsService_Factory create(Provider<InputStream> credentialsStreamProvider,
      Provider<String> applicationNameProvider) {
    return new GoogleSheetsService_Factory(credentialsStreamProvider, applicationNameProvider);
  }

  public static GoogleSheetsService newInstance(InputStream credentialsStream,
      String applicationName) {
    return new GoogleSheetsService(credentialsStream, applicationName);
  }
}
