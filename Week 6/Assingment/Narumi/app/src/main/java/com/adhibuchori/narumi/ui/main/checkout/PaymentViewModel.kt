package com.adhibuchori.narumi.ui.main.checkout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.adhibuchori.narumi.domain.usecase.GetPaymentListUseCase

class PaymentViewModel(application: Application) : AndroidViewModel(application) {

    private val useCase = GetPaymentListUseCase()

    val paymentList
        get() = useCase.invoke()

    fun getPaymentMethodList() = useCase.getListPaymentMethods()
}