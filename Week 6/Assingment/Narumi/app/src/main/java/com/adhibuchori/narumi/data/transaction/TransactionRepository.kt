package com.adhibuchori.narumi.data.transaction

import com.adhibuchori.narumi.di.AppDatabase
import com.adhibuchori.narumi.domain.transaction.ITransactionRepository
import com.adhibuchori.narumi.domain.transaction.TransactionMapper.mapEntityToModel
import com.adhibuchori.narumi.domain.transaction.TransactionModel
import com.adhibuchori.narumi.domain.trip.TripMapper.mapTripModelToTransaction
import com.adhibuchori.narumi.domain.trip.TripModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionRepository(
    database: AppDatabase,
) : ITransactionRepository {
    private val transactionDao = database.transactionDao()

    override suspend fun checkout(tripData: TripModel, totalCost: Int) {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val transaction = mapTripModelToTransaction(tripData.copy(tripOrderDate = currentDate), totalCost)
        transactionDao.addTransaction(transaction)
    }

    override fun fetchTransactions(): Flow<List<TransactionModel>> =
        transactionDao.fetchTransactions().map { transactionEntities ->
            transactionEntities.map {
                mapEntityToModel(it)
            }
        }
}