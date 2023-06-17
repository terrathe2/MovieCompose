package dependencies

import DependenciesVersions

object Dependencies {
    const val ACT_COMPOSE = "androidx.activity:activity-compose:${DependenciesVersions.ACT_COMPOSE}"
    const val ANOTATION = "androidx.annotation:annotation:${DependenciesVersions.ANNOTATION}"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:${DependenciesVersions.COMPOSE_BOM}"
    const val COMPOSE_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material"
    const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime"
    const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout-compose:${DependenciesVersions.CONSTRAINT_LAYOUT}"

    const val CORE = "androidx.core:core-ktx:${DependenciesVersions.CORE}"
    const val FLOW_LAYOUT = "androidx.compose.foundation:foundation"
    const val HILT = "com.google.dagger:hilt-android:${DependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${DependenciesVersions.HILT}"
    const val HILT_NAVIGATION =
        "androidx.hilt:hilt-navigation-compose:${DependenciesVersions.HILT_NAVIGATION}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib"
    const val KOTLIN_BOM = "org.jetbrains.kotlin:kotlin-bom:${DependenciesVersions.KOTLIN_BOM}"
    const val LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-compose:${DependenciesVersions.LIFECYCLE}"
    const val MOSHI = "com.squareup.moshi:moshi-kotlin:${DependenciesVersions.MOSHI}"
    const val NAVIGATION =
        "com.google.accompanist:accompanist-navigation-animation:${DependenciesVersions.ACCOMPANIST}"
    const val PAGING = "androidx.paging:paging-compose:${DependenciesVersions.PAGING}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER =
        "com.squareup.retrofit2:converter-moshi:${DependenciesVersions.RETROFIT}"
}