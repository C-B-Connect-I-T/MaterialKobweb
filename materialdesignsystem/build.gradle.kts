import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary
import kotlinx.html.link

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.library)
    // alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.detekt)
    `maven-publish`
}

group = "com.materialdesignsystem"
version = "0.1.0"

kobweb {
    library {
        index {
            head.add {
                link {
                    rel = "stylesheet"
                    href = "https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
                }
                link {
                    rel = "stylesheet"
                    href = "https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
                }
                link {
                    rel = "stylesheet"
                    href = "https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
                }
            }
        }
    }
}

kotlin {
    configAsKobwebLibrary()

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.kobweb.silk.icons.fa)
            // implementation(libs.kobwebx.markdown)
        }
    }
}

publishing {
    publications {
        register("mavenJsLibrary", MavenPublication::class) {
            from(components["kotlin"])
            groupId = "com.github.C-B-Connect-I-T"
            artifactId = "MaterialKobweb"
            version = version
        }
    }
}
