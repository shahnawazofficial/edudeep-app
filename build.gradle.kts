// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Google Services Plugin for Firebase
    id("com.google.gms.google-services") version "4.4.0" apply false // Use the correct version here
}

buildscript {
    dependencies {
        // Ensure that the Google services classpath is included
        classpath("com.google.gms:google-services:4.4.0")  // Add Firebase plugin dependency
    }
}

allprojects {
    // REMOVE this block entirely:
    // repositories {
    //     mavenCentral()
    // }
}
