import BuildModules.CORE
import dependencies.Dependencies

plugins {
    id("commons.movie-android-lib")
}

android {
    namespace = "com.redhaputra.data"
}

dependencies {
    implementation(project(CORE.NETWORK))
    implementation(project(CORE.MODEL))

    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
}