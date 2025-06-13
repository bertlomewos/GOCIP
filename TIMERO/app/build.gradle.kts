plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.timero"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.timero"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Default Android dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Third-party libraries
    implementation("de.hdodenhof:circleimageview:3.1.0")
    // implementation("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1") // <-- I have removed this line to fix the build
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
