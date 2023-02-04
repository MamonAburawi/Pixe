import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.info.pixels"
    compileSdk = 32

    defaultConfig {
        minSdk = 19
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")


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
}

dependencies {

    implementation(Deps.core)
    implementation(Deps.appCompact)
    implementation(Deps.material)
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espressoCore)

    // note: if you want to add the dagger to any library for example like this library don't add dagger plugin to library only add it to main app module just add the dagger implementation and kapt.

    // dagger hilt
    implementation (DaggerHilt.daggerHilt)
    kapt(DaggerHilt.daggerCompiler)

    // okhttp3
    implementation (Okhttp3.okhttp3)
    implementation (Okhttp3.loggingInterceptor)

    // retrofit2
    implementation (Retrofit2.retrofit)
    implementation (Retrofit2.converterGson)
    implementation (Retrofit2.converterScales)





}