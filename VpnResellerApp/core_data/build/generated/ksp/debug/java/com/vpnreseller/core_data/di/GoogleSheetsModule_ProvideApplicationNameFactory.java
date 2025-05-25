package com.vpnreseller.core_data.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata({
    "javax.inject.Named",
    "dagger.hilt.android.qualifiers.ApplicationContext"
})
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
public final class GoogleSheetsModule_ProvideApplicationNameFactory implements Factory<String> {
  private final Provider<Context> contextProvider;

  public GoogleSheetsModule_ProvideApplicationNameFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public String get() {
    return provideApplicationName(contextProvider.get());
  }

  public static GoogleSheetsModule_ProvideApplicationNameFactory create(
      Provider<Context> contextProvider) {
    return new GoogleSheetsModule_ProvideApplicationNameFactory(contextProvider);
  }

  public static String provideApplicationName(Context context) {
    return Preconditions.checkNotNullFromProvides(GoogleSheetsModule.INSTANCE.provideApplicationName(context));
  }
}
