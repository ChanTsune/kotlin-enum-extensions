import org.gradle.api.publish.maven.MavenPom

plugins {
    kotlin("multiplatform") version "1.4.0"

    id("org.jetbrains.dokka") version "1.4.0-rc"

    id("maven-publish")
}
repositories {
    mavenCentral()
    jcenter()
}
group = "com.github.chantsune"
version = "0.0.0-alpha0"

kotlin {
    explicitApiWarning()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        nodejs {
        }
    }
    macosX64()
    linuxX64()
    mingwX64()
    ios()
    tvos()
    watchos()
    wasm32()
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

tasks.dokkaHtml.configure {
    outputDirectory = "$buildDir/dokka/html"
    dokkaSourceSets {
        register("commonMain") {
            displayName = "common"
            platform = "common"
        }
    }
    doLast {
        File("$outputDirectory/index.html").apply {
            writeText("""
            <html><script>document.location = "./${project.name}"</script></html>
            """.trimIndent())
        }
    }
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokkaHtml)
}
val sourceJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
}

publishing {
    fun MavenPom.initPom() {
        name.set(project.name)
        description.set("kotlin enum extension")
        url.set("https://github.com/ChanTsune/kotlin-enum-extensions")

        licenses {
            license {
                name.set("MIT")
                url.set("https://github.com/ChanTsune/kotlin-enum-extensions/blob/master/LICENSE")
                distribution.set("repo")
            }
            scm {
                url.set("https://github.com/ChanTsune/kotlin-enum-extensions.git")
            }
        }
    }
    publications.withType<MavenPublication>().all {
        artifact(dokkaJar)
        pom.initPom()
    }
    publications.withType<MavenPublication>().find { it.name == "kotlinMultiplatform" }?.artifact(sourceJar)

    repositories {
        maven {
            name = "bintray"
            val bintrayUsername = "chantsune"
            val bintrayRepoName = "kotlin-enum-extensions"
            val bintrayPackageName = "com.github.chantsune.kotlin.enumext"
            setUrl("https://api.bintray.com/content/$bintrayUsername/$bintrayRepoName/$bintrayPackageName/${project.version};publish=0;override=1")

            credentials {
                username = project.findProperty("bintray_user") as String?
                password = project.findProperty("bintray_key") as String?
            }
        }
    }
}
