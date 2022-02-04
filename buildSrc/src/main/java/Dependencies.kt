object Versions {
    const val ANDROID_GRADLE_PLUGIN = "7.1.0"
    const val KOTLIN = "1.6.0"

    const val APP_COMPAT = "1.4.1"
    const val CONSTRAINT_LAYOUT = "2.1.3"
    const val CORE_KTX = "1.7.0"
    const val DAGGER = "2.40.5"
    const val GLIDE = "4.12.0"
    const val LOGGING_INTERCEPTOR = "4.9.3"
    const val MATERIAL = "1.5.0"
    const val NAVIGATION = "2.4.0"
    const val RETROFIT = "2.9.0"
    const val ROOM = "2.4.1"

    const val ANDROID_X_TEST = "1.4.0"
    const val ESPRESSO = "3.4.0"
    const val JUNIT = "4.12"
    const val MOCKITO = "3.5.13"
}

object Libs {
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val DAGGER = "com.google.dagger:hilt-android:${Versions.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:hilt-compiler:${Versions.DAGGER}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
}

object Tests {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLIN}"
    const val MOCKITO = "org.mockito:mockito-core:${Versions.MOCKITO}"

    const val TEST_CORE = "androidx.test:core:${Versions.ANDROID_X_TEST}"
    const val TEST_RUNNER = "androidx.test:runner:${Versions.ANDROID_X_TEST}"
    const val TEST_RULES = "androidx.test:rules:${Versions.ANDROID_X_TEST}"
    const val TEST_EXT_J_UNIT = "androidx.test.ext:junit:1.1.3"

    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"

}
