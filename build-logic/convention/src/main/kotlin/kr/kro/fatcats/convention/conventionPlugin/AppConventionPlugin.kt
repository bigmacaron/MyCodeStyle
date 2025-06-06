package kr.kro.fatcats.convention.conventionPlugin

import com.android.build.api.dsl.ApplicationExtension
import kr.kro.fatcats.convention.configureAppDefault
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                configureAppDefault(this)
            }
        }
    }
}