package com.adhibuchori.narumi.di

import androidx.room.Room
import com.adhibuchori.narumi.data.auth.AuthRepository
import com.adhibuchori.narumi.data.transaction.TransactionRepository
import com.adhibuchori.narumi.data.trip.TripRepository
import com.adhibuchori.narumi.domain.auth.IAuthRepository
import com.adhibuchori.narumi.domain.transaction.ITransactionRepository
import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.utils.base.BaseModule
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object DatabaseModule : BaseModule {
    private val firebaseModule = module {
        single { Firebase.auth }
    }

    private val roomModule = module {
        single {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "narumi_database")
                .build()
        }
    }

    private val repositoryModule = module {
        single<IAuthRepository> { AuthRepository(get()) }
        single<ITripRepository> { TripRepository() }
        single<ITransactionRepository> { TransactionRepository(get()) }
    }

    override fun getModules(): List<Module> {
        return listOf(
            firebaseModule,
            repositoryModule,
            roomModule
        )
    }
}