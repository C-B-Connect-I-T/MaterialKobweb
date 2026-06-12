import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
}

group = "com.materialkobweb.sample"
version = "0.1.0"

kobweb {
    app {
        index {
            description.set("Sample application for Material Design System components")
        }
    }
}

kotlin {
    configAsKobwebApplication()

    sourceSets {
        jsMain.dependencies {
            implementation(project(":materialKobweb"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
        }
    }
}
