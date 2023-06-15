import dependencies.Dependencies

plugins {
    id("commons.movie-android-lib")
}

android {
    namespace = "com.redhaputra.designsystem"
}

dependencies {
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
}