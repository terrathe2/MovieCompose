import dependencies.Dependencies

plugins {
    id("commons.movie-android-feature")
}

android {
    namespace = "com.redhaputra.moviedetail"
}

dependencies {
    implementation(Dependencies.PAGING)
    implementation(Dependencies.YOUTUBE_PLAYER)
}