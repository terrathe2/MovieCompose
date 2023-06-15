import dependencies.Dependencies

plugins {
    id("commons.movie-android-lib")
}

android {
    namespace = "com.redhaputra.model"
}

dependencies {
    implementation(Dependencies.ANOTATION)
    implementation(Dependencies.MOSHI)
}