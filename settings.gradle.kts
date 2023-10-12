pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MovieApp"
include(":app")
include(":commons:theme")
include(":commons:provider")
include(":commons:component")
include(":data:model")
include(":data:repository")
include(":data:remote")
include(":domain")
include(":library:framework")
include(":feature:genre")
include(":feature:home")
include(":feature:movie")
include(":data:local")
include(":feature:favorite")
