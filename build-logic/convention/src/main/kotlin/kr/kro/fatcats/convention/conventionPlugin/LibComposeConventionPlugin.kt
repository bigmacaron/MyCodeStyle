package kr.kro.fatcats.convention.conventionPlugin

import com.android.build.gradle.LibraryExtension
import kr.kro.fatcats.convention.configureAndroidCompose
import kr.kro.fatcats.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class LibComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-parcelize")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }

        }
    }
}



