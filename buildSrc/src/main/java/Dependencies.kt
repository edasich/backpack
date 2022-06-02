@file:Suppress("unused", "MayBeConstant")

object Dependencies {

    /* Kotlin */
    private val kotlinCoroutineVersion = "1.6.0"
    val kotlinCoroutineTestDependency = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutineVersion"

    /* Network */
    private val retrofitVersion = "2.9.0"
    private val okHttpVersion = "4.9.3"
    val retrofitDependency = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val retrofitConverterGsonDependency = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    val okHttpDependency = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    val mockWebServerDependency = "com.squareup.okhttp3:mockwebserver:$okHttpVersion"
    val okioDependency = "com.squareup.okio:okio:3.1.0"

    /* Test */
    private val junitVersion = "4.13"
    private val hamcrestVersion = "2.2"
    val junitDependency = "junit:junit:$junitVersion"
    val hamcrestDependency = "org.hamcrest:hamcrest:$hamcrestVersion"

}