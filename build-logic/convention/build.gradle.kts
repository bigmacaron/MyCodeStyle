import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.tools.build.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "app.application"
            implementationClass = "kr.kro.fatcats.convention.conventionPlugin.AppConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "app.compose"
            implementationClass = "kr.kro.fatcats.convention.conventionPlugin.AppComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "lib.library"
            implementationClass = "kr.kro.fatcats.convention.conventionPlugin.LibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "lib.compose"
            implementationClass = "kr.kro.fatcats.convention.conventionPlugin.LibComposeConventionPlugin"
        }

        register("androidHilt") {
            id = "all.hilt"
            implementationClass = "kr.kro.fatcats.convention.conventionPlugin.HiltConventionPlugin"
        }

         register("testConvention") {
            id = "all.test"
            implementationClass = "kr.kro.fatcats.convention.conventionPlugin.TestConventionPlugin"
        }
    }
}