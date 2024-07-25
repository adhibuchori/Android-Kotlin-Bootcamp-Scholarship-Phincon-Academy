package com.adhibuchori.narumi.domain.transaction

import com.adhibuchori.narumi.domain.trip.TripModel
import kotlinx.coroutines.flow.Flow

interface ITransactionRepository {
    suspend fun checkout(tripData: TripModel, totalCost: Int)
    fun fetchTransactions(): Flow<List<TransactionModel>>
}