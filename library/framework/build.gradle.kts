import extension.PROVIDER
import extension.addHiltDependency
import extension.addNetworkDependency

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
}

android {
    namespace = "com.fikrisandi.framework"

}

dependencies {
    addHiltDependency()
    addNetworkDependency()
    implementation(Paging3Libs.runtime)

    PROVIDER
}