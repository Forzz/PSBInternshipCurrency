plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.forzz.psbinternshipcurrency"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.forzz.psbinternshipcurrency"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding.enable = true
    viewBinding.enable = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.work.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Jetpack navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Retrofit2
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // GSON
    implementation(libs.gson)

    // Dagger-Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Okhttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
}

kapt {
    correctErrorTypes = true
}