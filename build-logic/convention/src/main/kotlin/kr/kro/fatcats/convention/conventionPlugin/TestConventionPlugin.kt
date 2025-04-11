package kr.kro.fatcats.convention.conventionPlugin

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.google.devtools.ksp")

        extensions.configure<LibraryExtension> {
            defaultConfig {
                testInstrumentationRunner = "kr.kro.fatcats.incall.call.util.HiltTestRunner"
            }
            packaging.resources.excludes += setOf(
                "META-INF/LICENSE*",
                "META-INF/NOTICE*",
                "META-INF/AL2.0",
                "META-INF/LGPL2.1"
            )
        }

        dependencies {
            // 유닛 테스트용
            "testImplementation"("junit:junit:4.13.2")
            "testImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
            "testImplementation"("io.mockk:mockk:1.13.10")

            // 계측 테스트용
            "androidTestImplementation"("androidx.test:runner:1.6.0")
            "androidTestImplementation"("androidx.test:rules:1.6.0")
            "androidTestImplementation"("androidx.test.ext:junit-ktx:1.1.5")
            "androidTestImplementation"("com.google.dagger:hilt-android-testing:2.51.1")
            "kspAndroidTest"("com.google.dagger:hilt-android-compiler:2.51.1")
            "androidTestImplementation"("androidx.compose.ui:ui-test-junit4")
            "debugImplementation"("androidx.compose.ui:ui-test-manifest")
            "androidTestImplementation"("io.mockk:mockk-android:1.13.10")

            // Compose BOM
            "androidTestImplementation"(platform("androidx.compose:compose-bom:2024.03.00"))
        }
    }
}
