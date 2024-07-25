package com.adhibuchori.narumi.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhibuchori.narumi.R
import com.adhibuchori.narumi.data.checkout.PaymentMethod
import com.adhibuchori.narumi.data.checkout.PaymentResponse
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.gson.Gson

class GetPaymentListUseCase {
    private val firebaseRemoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    private val listPayment = MutableLiveData<List<PaymentMethod>>()

    operator fun invoke(): LiveData<List<PaymentMethod>> {
        firebaseRemoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                if (configUpdate.updatedKeys.contains("payment_list")) {
                    getListPaymentMethods()
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {}
        })
        return listPayment
    }

    fun getListPaymentMethods() {
        firebaseRemoteConfig.run {
            setDefaultsAsync(R.xml.remote_config_defaults)
            fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val paymentListJson = firebaseRemoteConfig.getString("payment_list")
                        val paymentResponse =
                            Gson().fromJson(paymentListJson, PaymentResponse::class.java)
                        listPayment.postValue(paymentResponse.data)
                    }
                }
        }
    }
}