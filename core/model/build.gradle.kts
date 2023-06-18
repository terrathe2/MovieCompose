import dependencies.Dependencies

plugins {
    id("commons.movie-android-lib")
    id("kotlin-parcelize")
}

android {
    namespace = "com.redhaputra.model"
}

dependencies {
    implementation(Dependencies.ANOTATION)
    implementation(Dependencies.MOSHI)
}