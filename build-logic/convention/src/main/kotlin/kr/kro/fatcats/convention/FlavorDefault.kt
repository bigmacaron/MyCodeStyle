package kr.kro.fatcats.convention

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class Dimension {
    server,
    version
}

enum class ApiHost {
    BIZ,
    DEV,
    QA
}

enum class DimensionFlavor(
    val dimension : Dimension,
    val apiHost : String? = null,
) {
    Biz(dimension = Dimension.server, apiHost = ApiHost.BIZ.name),
    Dev(dimension = Dimension.server, apiHost = ApiHost.DEV.name),
    QA(dimension = Dimension.server, apiHost = ApiHost.QA.name),
    normal(dimension = Dimension.version),
    servererror(dimension = Dimension.version)
}

val DimensionFlavorList = mutableListOf(Dimension.server.name, Dimension.version.name)

fun configureFlavorDefault(
    commonExtension: CommonExtension<*,*,*,*,*,*>,
    flavorConfigurationBlock: ProductFlavor.(flavor: DimensionFlavor) -> Unit = {}
) {
    commonExtension.apply {
        buildFeatures {
            buildConfig = true
        }

        flavorDimensions.addAll(DimensionFlavorList)

        productFlavors {
            DimensionFlavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    flavorConfigurationBlock(this, it)
                    if (!it.apiHost.isNullOrEmpty()) {
                        buildConfigField ("String", "API_HOST", "\"${it.apiHost}\"")
                    }
                }
            }
        }

        sourceSets {
            named("normal") {
                java.srcDir("src/main/java")
                java.srcDir("src/normal/java")
                assets.srcDirs ("src/normal/assets")
            }
            named("servererror") {
                java.srcDir("src/main/java")
                java.srcDir("src/servererror/java")
                assets.srcDirs ("src/servererror/assets")
            }
        }
    }
}