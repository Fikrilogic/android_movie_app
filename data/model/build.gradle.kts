import extension.FRAMEWORK

plugins {
    id("commons.android-library")
}

android {
    namespace = "com.fikrisandi.model"

}

dependencies {

    implementation(NetworkLibs.ktor_gson)
}