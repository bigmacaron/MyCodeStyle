plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.lib.compose)
    alias(libs.plugins.hilt)
}

android {
    namespace = "kr.kro.fatcats.mycodestyle.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
}