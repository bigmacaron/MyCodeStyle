plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.app.compose)
}

android {
    namespace = "kr.kro.fatcats.mycodestyle"
    compileSdk = 35

    defaultConfig {
        applicationId = "kr.kro.fatcats.mycodestyle"
        minSdk = 26
        targetSdk = 35
        versionCode = 102
        versionName = "1.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {


    implementation(project(":feature:main"))
    implementation(project(":core:room"))
    implementation(project(":adhelper"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}