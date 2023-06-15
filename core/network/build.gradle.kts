import BuildModules.CORE
import dependencies.Dependencies
import dependencies.DebugDependencies

plugins {
    id("commons.movie-android-lib")
}

android {
    namespace = "com.redhaputra.network"
}

dependencies {
    implementation(project(CORE.MODEL))

    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)

    debugImplementation(DebugDependencies.STETHO)
    debugImplementation(DebugDependencies.STETHO_OKHTTP3)
}