package com.adhibuchori.country.di

import com.adhibuchori.country.data.CountryRepository
import com.adhibuchori.country.domain.ICountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideCountryRepository(countryRepository: CountryRepository): ICountryRepository
}