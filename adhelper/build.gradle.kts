import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "kr.kro.fatcats.adhelper"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    val localProps = Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }

    buildTypes {
        debug {
            // 디버그: 테스트 광고 단위 사용(없을 경우 빈 문자열)
            buildConfigField("String", "INTERSTITIAL_ID", localProps.getProperty("TEST_INTERSTITIAL_ID") ?: "\"\"")
            buildConfigField("String", "REWARDED_ID", localProps.getProperty("TEST_REWARDED_ID") ?: "\"\"")
            buildConfigField("String", "APP_OPEN_AD_UNIT_ID", localProps.getProperty("TEST_APP_OPEN_AD_UNIT_ID") ?: "\"\"")
        }
        release {
            // 릴리즈: 실제 광고 단위 사용(없을 경우 빈 문자열)
            buildConfigField("String", "INTERSTITIAL_ID", localProps.getProperty("INTERSTITIAL_ID") ?: "\"\"")
            buildConfigField("String", "REWARDED_ID", localProps.getProperty("REWARDED_ID") ?: "\"\"")
            buildConfigField("String", "APP_OPEN_AD_UNIT_ID", localProps.getProperty("APP_OPEN_AD_UNIT_ID") ?: "\"\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isMinifyEnabled = false
        }

    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.ads)
}