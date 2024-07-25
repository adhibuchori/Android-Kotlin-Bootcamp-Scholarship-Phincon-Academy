package com.adhibuchori.narumi.ui.main.history

import androidx.lifecycle.ViewModel
import com.adhibuchori.narumi.domain.transaction.ITransactionRepository

class HistoryViewModel(
    transactionRepository: ITransactionRepository,
) : ViewModel() {

    val historyData = transactionRepository.fetchTransactions()
}