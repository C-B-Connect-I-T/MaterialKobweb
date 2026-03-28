pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "materialdesignsystem"

include(":materialdesignsystem")
include(":sample")
