package com.adhibuchori.narumi.di

import com.adhibuchori.narumi.ui.MainActivity
import com.adhibuchori.narumi.ui.MainViewModel
import com.adhibuchori.narumi.ui.authentication.login.LoginFragment
import com.adhibuchori.narumi.ui.authentication.login.LoginViewModel
import com.adhibuchori.narumi.ui.authentication.register.RegisterFragment
import com.adhibuchori.narumi.ui.authentication.register.RegisterViewModel
import com.adhibuchori.narumi.ui.main.checkout.CheckoutFragment
import com.adhibuchori.narumi.ui.main.checkout.CheckoutViewModel
import com.adhibuchori.narumi.ui.main.detail.DetailFragment
import com.adhibuchori.narumi.ui.main.detail.DetailViewModel
import com.adhibuchori.narumi.ui.main.history.HistoryFragment
import com.adhibuchori.narumi.ui.main.history.HistoryViewModel
import com.adhibuchori.narumi.ui.main.home.HomeViewModel
import com.adhibuchori.narumi.ui.main.profile.ProfileFragment
import com.adhibuchori.narumi.ui.main.profile.ProfileViewModel
import com.adhibuchori.narumi.ui.main.search.SearchFragment
import com.adhibuchori.narumi.ui.main.search.SearchViewModel
import com.adhibuchori.narumi.utils.base.BaseModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule : BaseModule {
    private val viewModelModules = module {
        scope<LoginFragment> { viewModel { LoginViewModel(get()) } }
        scope<MainActivity> { viewModel { MainViewModel(get()) } }
        scope<RegisterFragment> { viewModel { RegisterViewModel(get()) } }
        scope<ProfileFragment> { viewModel { ProfileViewModel(get()) } }
        scope<SearchFragment> { viewModel { SearchViewModel(get()) } }
        scope<DetailFragment> { viewModel { DetailViewModel(get()) } }
        scope<CheckoutFragment> { viewModel { CheckoutViewModel(get(), get()) } }
        scope<HistoryFragment> { viewModel { HistoryViewModel(get()) } }
        single { HomeViewModel(get(), get()) }
    }

    override fun getModules(): List<Module> {
        return listOf(
            viewModelModules
        )
    }
}