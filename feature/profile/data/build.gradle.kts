plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}
configurations.all {
    resolutionStrategy {
        force("org.jetbrains:annotations:23.0.0")
    }
}

android {
    namespace = "com.manish.mandhan.profile.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":feature:profile:domain"))
    implementation(project(":common"))


    // hilt
    implementation(libs.google.dragger.hilt)
    ksp(libs.google.dragger.hilt.compiler)

    //room
    implementation(libs.androidx.room.runtime) {
        exclude(group = "com.intellij:annotations")
    }
    implementation(libs.androidx.room.ktx) {
        exclude(group = "com.intellij:annotations")
    }
    ksp(libs.androidx.room.compiler) {
        exclude(group = "com.intellij:annotations")
    }

    // retro
    implementation(libs.squareup.retrofit2)
//    implementation(libs.converter.gson)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}