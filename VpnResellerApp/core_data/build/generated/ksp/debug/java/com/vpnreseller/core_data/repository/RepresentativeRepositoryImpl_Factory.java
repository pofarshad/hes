package com.vpnreseller.core_data.repository;

import com.vpnreseller.core_data.local.dao.RepresentativeDao;
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
public final class RepresentativeRepositoryImpl_Factory implements Factory<RepresentativeRepositoryImpl> {
  private final Provider<RepresentativeDao> representativeDaoProvider;

  public RepresentativeRepositoryImpl_Factory(
      Provider<RepresentativeDao> representativeDaoProvider) {
    this.representativeDaoProvider = representativeDaoProvider;
  }

  @Override
  public RepresentativeRepositoryImpl get() {
    return newInstance(representativeDaoProvider.get());
  }

  public static RepresentativeRepositoryImpl_Factory create(
      Provider<RepresentativeDao> representativeDaoProvider) {
    return new RepresentativeRepositoryImpl_Factory(representativeDaoProvider);
  }

  public static RepresentativeRepositoryImpl newInstance(RepresentativeDao representativeDao) {
    return new RepresentativeRepositoryImpl(representativeDao);
  }
}
