import dependencies.Dependencies

plugins {
    id("commons.movie-android-feature")
}

android {
    namespace = "com.redhaputra.genres"
}

dependencies {
    implementation(Dependencies.FLOW_LAYOUT)
}