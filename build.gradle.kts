buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        val gradle = "7.0.4"
        classpath("com.android.tools.build:gradle:$gradle")

        val kotlin = "1.5.21"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")

        val hilt = "2.38.1"
        classpath( "com.google.dagger:hilt-android-gradle-plugin:$hilt")
    }
}