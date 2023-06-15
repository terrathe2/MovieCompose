import BuildModules.CORE

plugins {
    id("commons.movie-android-lib")
}

android {
    namespace = "com.redhaputra.data"
}

dependencies {
    implementation(project(CORE.NETWORK))
    implementation(project(CORE.MODEL))
}