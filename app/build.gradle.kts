import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin.Companion.isKaptVerbose

import dependencies.Dependencies
import BuildModules.CORE

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
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
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
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

    implementation(platform(Dependencies.KOTLIN_BOM))
    implementation(platform(Dependencies.COMPOSE_BOM))
    implementation(Dependencies.CORE)
    implementation(Dependencies.KOTLIN)
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation(Dependencies.ACT_COMPOSE)

    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_GRAPHICS)
    implementation(Dependencies.COMPOSE_TOOLING_PREVIEW)
    implementation(Dependencies.COMPOSE_MATERIAL)

    debugImplementation(Dependencies.COMPOSE_TOOLING)
    debugImplementation(Dependencies.COMPOSE_TEST_MANIFEST)
}