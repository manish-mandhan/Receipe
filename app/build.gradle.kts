import com.android.build.api.variant.BuildConfigField

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.compose.compiler)


    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    alias(libs.plugins.google.gms.google.services)
}
android {
    namespace = "com.manish.mandhan.receipe"
    compileSdk = 35
    signingConfigs {

    }

    defaultConfig {
        applicationId = "com.manish.mandhan.receipe"
        minSdk = 27
        targetSdk = 35
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":feature:search:presentation"))
    implementation(project(":feature:search:domain"))
    implementation(project(":feature:search:data"))
    implementation(project(":feature:profile:data"))
    implementation(project(":feature:profile:domain"))
    implementation(project(":feature:profile:presentation"))
    implementation(project(":feature:settings"))

    implementation(project(":common"))

    //splash
    implementation("androidx.core:core-splashscreen:1.0.0-beta02")


    // preference datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")


    // onesignal sdk
    implementation(libs.onesignal)


    //room support
    implementation(libs.androidx.room.runtime) {
        exclude(group = "com.intellij:annotations")
    }
    implementation(libs.androidx.room.ktx) {
        exclude(group = "com.intellij:annotations")
    }
    ksp(libs.androidx.room.compiler) {
        exclude(group = "com.intellij:annotations")
    }
    implementation(libs.firebase.messaging)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
//    implementation(libs.androidx.runtime)

    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.google.dragger.hilt)
    ksp(libs.google.dragger.hilt.compiler)

    // coil
    implementation(libs.coil)
    implementation(libs.androidx.hilt.navigation.compose)



    implementation(libs.androidx.navigation.compose)


    implementation(libs.converter.gson)
    implementation(libs.squareup.retrofit2)

}