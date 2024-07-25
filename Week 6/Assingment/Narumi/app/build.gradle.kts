plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("com.google.firebase.crashlytics")
    id("kotlin-kapt")
}

android {
    namespace = "com.adhibuchori.narumi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.adhibuchori.narumi"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Circular Image View
    implementation(libs.circleimageview)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // View Pager2
    implementation(libs.androidx.viewpager2)

    // Circular Image View
    implementation(libs.circleimageview)

    // Data Store
    implementation(libs.androidx.datastore.preferences)

    // View Model and Live Data
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Koin
    implementation(libs.koin.android)

    // Firebase BoM
    implementation(platform(libs.firebase.bom))

    // Firebase Analytics
    implementation(libs.firebase.analytics)

    // Firebase Authentication and Storage
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.config.ktx)

    // Firebase Crashlytics
    implementation(libs.firebase.crashlytics)
    implementation(libs.google.firebase.analytics)

    // GSON
    implementation(libs.gson)

    // Glide
    implementation(libs.github.glide)

    // Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core)
    testImplementation(libs.core.testing)
}