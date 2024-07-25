package com.adhibuchori.narumi.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adhibuchori.narumi.data.transaction.TransactionDao
import com.adhibuchori.narumi.data.transaction.model.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}