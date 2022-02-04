plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = App.COMPILE_SDK

    defaultConfig {
        minSdk = App.MIN_SDK
        targetSdk = App.TARGET_SDK
        versionCode = App.VERSION_CODE
        versionName = App.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            BuildConfig.Type.STRING,
            BuildConfig.Param.BASE_URL,
            BuildConfig.Value.BASE_URL
        )
    }

    buildTypes {
        getByName(BuildType.debug) {

        }

        getByName(BuildType.release) {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
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

    implementation(Libs.CORE_KTX)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)

    // UI
    implementation(Libs.APPCOMPAT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.MATERIAL)
    implementation(Libs.GLIDE)

    // Navigation
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)

    // Data
    implementation(Libs.GSON)
    implementation(Libs.LOGGING_INTERCEPTOR)
    implementation(Libs.RETROFIT)

    // Room
    implementation(Libs.ROOM_KTX)
    kapt(Libs.ROOM_COMPILER)

    // DI
    implementation(Libs.DAGGER)
    kapt(Libs.DAGGER_COMPILER)

    // Local unit tests
    testImplementation(Tests.JUNIT)
    testImplementation(Tests.COROUTINE)
    testImplementation(Tests.MOCKITO)
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    // Instrumentation tests
    androidTestImplementation(Tests.TEST_CORE)
    androidTestImplementation(Tests.TEST_RUNNER)
    androidTestImplementation(Tests.TEST_RULES)
    androidTestImplementation(Tests.TEST_EXT_J_UNIT)
    androidTestImplementation(Tests.ESPRESSO)
}
