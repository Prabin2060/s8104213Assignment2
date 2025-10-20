// Top-level build file
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false

    // UPDATE HILT VERSION
    id("com.google.dagger.hilt.android") version "2.50" apply false

    // ADD THIS KSP PLUGIN (match your 1.9.10 kotlin version)
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}