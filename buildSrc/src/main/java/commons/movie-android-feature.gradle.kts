package commons

import BuildModules.CORE
import dependencies.Dependencies
import org.gradle.kotlin.dsl.project

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION

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
}

dependencies {
    implementation(project(CORE.DATA))
    implementation(project(CORE.DESIGN_SYSTEM))
    api(project(CORE.NAVIGATION))

    implementation(platform(Dependencies.KOTLIN_BOM))
    implementation(platform(Dependencies.COMPOSE_BOM))
    implementation(Dependencies.KOTLIN)

    implementation(Dependencies.ACT_COMPOSE)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_GRAPHICS)
    implementation(Dependencies.COMPOSE_TOOLING_PREVIEW)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_RUNTIME)


    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
}