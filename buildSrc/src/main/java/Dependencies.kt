object Versions{
    const val core = "1.7.0"
    const val appCompact = "1.6.0"
    const val material = "1.8.0"
    const val constraintLayout = "2.1.4"
    const val legacySupport = "1.0.0"
    const val lifeCycle = "2.5.1"
    const val junit = "4.13.2"
    const val AndroidTestJunit = "1.1.5"
    const val espressoCore = "3.5.1"
    const val navigation = "2.5.3"
    const val okhttp3 = "4.10.0"
    const val retrofit = "2.9.0"
    const val retrofitConverterScales = "2.9.0"
    const val paging3 = "3.0.0"
    const val swipeRefreshLayout = "1.1.0"
    const val daggerHilt = "2.42"
    const val glide = "4.14.2"
    const val work = "2.7.1"
    const val exoPlayer = "2.18.2"
    const val photoView = "2.3.0"
    const val dexter = "6.2.3"
    const val progressFab = "1.6"
    const val lottie = "3.4.0"
}

object Deps{
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val lifeData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
}

object TestImplementation{
    const val junit = "junit:junit:${Versions.junit}"
}

object AndroidTestImplementation{
    const val junit = "androidx.test.ext:junit:${Versions.AndroidTestJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Navigation {
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object Okhttp3{
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
}

object Retrofit2{
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val converterScales = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitConverterScales}"
}

object Paging3 {
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging3}"
}

object SwipeRefreshLayout{
    const val refreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object DaggerHilt{
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object WorkManager{
    const val work  = "androidx.work:work-runtime:${Versions.work}"
}

object ExoPlayer {
    const val exoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.exoPlayer}"
}

object PhotoView{
    const val photoView = "com.github.chrisbanes:PhotoView:${Versions.photoView}"
}

object Dexter {
    const val dexter  = "com.karumi:dexter:${Versions.dexter}"
}

object ProgressFab{
    const val  progressFab = "com.dmitrymalkovich.android:progress-fab:${Versions.progressFab}"
}

object Lottie {
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
}




