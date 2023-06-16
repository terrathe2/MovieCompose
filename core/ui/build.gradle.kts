import BuildModules.CORE
import dependencies.Dependencies

plugins {
    id("commons.movie-android-lib")
}

android {
    namespace = "com.redhaputra.ui"
}

dependencies {
    implementation(project(CORE.DESIGN_SYSTEM))
    implementation(Dependencies.ACT_COMPOSE)
    implementation(Dependencies.COMPOSE_MATERIAL)
}