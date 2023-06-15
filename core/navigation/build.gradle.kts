import dependencies.Dependencies

plugins {
    id("commons.movie-android-lib")
}

android {
    namespace = "com.redhaputra.navigation"
}

dependencies {
    api(Dependencies.NAVIGATION)
    implementation(Dependencies.COMPOSE_MATERIAL)
}