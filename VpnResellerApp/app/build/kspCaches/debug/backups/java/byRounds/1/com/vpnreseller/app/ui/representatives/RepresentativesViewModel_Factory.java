package com.vpnreseller.app.ui.representatives;

import com.vpnreseller.core_data.repository.RepresentativeRepository;
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
public final class RepresentativesViewModel_Factory implements Factory<RepresentativesViewModel> {
  private final Provider<RepresentativeRepository> representativeRepositoryProvider;

  public RepresentativesViewModel_Factory(
      Provider<RepresentativeRepository> representativeRepositoryProvider) {
    this.representativeRepositoryProvider = representativeRepositoryProvider;
  }

  @Override
  public RepresentativesViewModel get() {
    return newInstance(representativeRepositoryProvider.get());
  }

  public static RepresentativesViewModel_Factory create(
      Provider<RepresentativeRepository> representativeRepositoryProvider) {
    return new RepresentativesViewModel_Factory(representativeRepositoryProvider);
  }

  public static RepresentativesViewModel newInstance(
      RepresentativeRepository representativeRepository) {
    return new RepresentativesViewModel(representativeRepository);
  }
}
