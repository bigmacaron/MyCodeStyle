plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.lib.compose)
    alias(libs.plugins.hilt)
}

android {
    namespace = "kr.kro.fatcats.mycodestyle.room"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.gson)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp (libs.room.compiler)
}