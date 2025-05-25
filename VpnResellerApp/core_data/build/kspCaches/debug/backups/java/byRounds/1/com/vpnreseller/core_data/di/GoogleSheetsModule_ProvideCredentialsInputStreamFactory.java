package com.vpnreseller.core_data.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.io.InputStream;
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
public final class GoogleSheetsModule_ProvideCredentialsInputStreamFactory implements Factory<InputStream> {
  private final Provider<Context> contextProvider;

  public GoogleSheetsModule_ProvideCredentialsInputStreamFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public InputStream get() {
    return provideCredentialsInputStream(contextProvider.get());
  }

  public static GoogleSheetsModule_ProvideCredentialsInputStreamFactory create(
      Provider<Context> contextProvider) {
    return new GoogleSheetsModule_ProvideCredentialsInputStreamFactory(contextProvider);
  }

  public static InputStream provideCredentialsInputStream(Context context) {
    return Preconditions.checkNotNullFromProvides(GoogleSheetsModule.INSTANCE.provideCredentialsInputStream(context));
  }
}
