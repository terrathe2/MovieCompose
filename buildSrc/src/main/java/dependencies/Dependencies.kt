package dependencies

import DependenciesVersions

object Dependencies {
    const val ACT_COMPOSE = "androidx.activity:activity-compose:${DependenciesVersions.ACT_COMPOSE}"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:${DependenciesVersions.COMPOSE_BOM}"
    const val COMPOSE_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material"
    const val COMPOSE_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling"
    const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_UI = "androidx.compose.ui:ui"

    const val CORE = "androidx.core:core-ktx:${DependenciesVersions.CORE}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib"
    const val KOTLIN_BOM = "org.jetbrains.kotlin:kotlin-bom:${DependenciesVersions.KOTLIN_BOM}"
}