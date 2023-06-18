include(
    ":app",
    ":core:data",
    ":core:designsystem",
    ":core:ui",
    ":core:model",
    ":core:navigation",
    ":core:network",
    ":features:genres",
    ":features:listmovie",
    ":features:moviedetail"
)

rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "MovieCompose"
