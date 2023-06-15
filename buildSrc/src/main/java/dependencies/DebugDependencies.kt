package dependencies

object DebugDependencies {
    const val STETHO = "com.facebook.stetho:stetho:${DependenciesVersions.STETHO}"
    const val STETHO_OKHTTP3 = "com.facebook.stetho:stetho-okhttp3:${DependenciesVersions.STETHO}"

    const val COMPOSE_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling"
}