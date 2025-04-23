plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.lib.compose)
    alias(libs.plugins.hilt)
}

android {
    namespace = "kr.kro.fatcats.mycodestyle.home"
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
}