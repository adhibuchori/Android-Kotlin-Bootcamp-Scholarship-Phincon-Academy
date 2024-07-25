package com.adhibuchori.narumi.data.firebase

import android.os.Bundle
import com.adhibuchori.narumi.domain.firebase.IFirebaseRepository
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseRepository(
    private val firebaseAnalytics: FirebaseAnalytics,
) : IFirebaseRepository {
    override fun logEvent(eventName: String, bundle: Bundle) {
        firebaseAnalytics.logEvent(eventName, bundle)
    }
}