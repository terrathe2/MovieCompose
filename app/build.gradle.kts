import dependencies.Dependencies
import dependencies.DebugDependencies
import BuildModules.CORE
import BuildModules.FEATURES

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT)
}

android {
    namespace = BuildAndroidConfig.APPLICATION_ID
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.VECTOR_DRAWABLE
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = BuildAndroidConfig.COMPOSE_VERSION
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(CORE.DESIGN_SYSTEM))
    implementation(project(CORE.NAVIGATION))
    implementation(project(FEATURES.GENRES))
    implementation(project(FEATURES.LIST_MOVIE))

    implementation(platform(Dependencies.KOTLIN_BOM))
    implementation(platform(Dependencies.COMPOSE_BOM))
    implementation(Dependencies.CORE)
    implementation(Dependencies.KOTLIN)

    implementation(Dependencies.ACT_COMPOSE)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_GRAPHICS)
    implementation(Dependencies.COMPOSE_TOOLING_PREVIEW)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_RUNTIME)

    // HILT
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)

    debugImplementation(DebugDependencies.STETHO)
    debugImplementation(DebugDependencies.COMPOSE_TOOLING)
    debugImplementation(DebugDependencies.COMPOSE_TEST_MANIFEST)
}