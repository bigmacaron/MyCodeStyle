package kr.kro.fatcats.convention.conventionPlugin

import kr.kro.fatcats.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                "implementation"(libs.findLibrary("hilt-android").get())
                "ksp"(libs.findLibrary("hilt-android-compiler").get())
                "ksp"(libs.findLibrary("hilt-compiler").get())
                "implementation"(libs.findLibrary("hilt-navigation-compose").get())
            }

        }
    }

}