package com.adhibuchori.narumi.domain.firebase

import android.os.Bundle

interface IFirebaseRepository {
    fun logEvent(eventName: String, bundle: Bundle)
}