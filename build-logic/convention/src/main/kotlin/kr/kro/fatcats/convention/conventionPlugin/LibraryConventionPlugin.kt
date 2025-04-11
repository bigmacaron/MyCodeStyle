package kr.kro.fatcats.convention.conventionPlugin

import com.android.build.api.dsl.LibraryExtension
import kr.kro.fatcats.convention.configureAppDefault
import kr.kro.fatcats.convention.configureFlavorDefault
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-parcelize")
            }
            extensions.configure<LibraryExtension> {
                configureAppDefault(this)
//                configureFlavorDefault(this)
            }
        }
    }
}

