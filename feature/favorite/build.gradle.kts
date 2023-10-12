import extension.COMPONENT
import extension.DOMAIN
import extension.FRAMEWORK
import extension.MODEL
import extension.PROVIDER
import extension.THEME
import extension.addDestinationDependency
import extension.addHiltDependency

plugins {
    id("commons.android-feature")
    id("commons.android-compose")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fikrisandi.favorite"
}

ksp{
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "favorite")
}

dependencies {

    THEME
    PROVIDER
    COMPONENT

    FRAMEWORK

    MODEL
    DOMAIN

    addHiltDependency()
    addDestinationDependency()
}