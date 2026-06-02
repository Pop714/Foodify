// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // hilt & ksp
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    // Firebase SDK
    id("com.google.gms.google-services") version "4.4.4" apply false
    // Crashlytics SDK
    id("com.google.firebase.crashlytics") version "3.0.7" apply false
}