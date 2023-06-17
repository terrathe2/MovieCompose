import dependencies.Dependencies

plugins {
    id("commons.movie-android-feature")
}

android {
    namespace = "com.redhaputra.listmovie"
}

dependencies {
    implementation(Dependencies.PAGING)
}