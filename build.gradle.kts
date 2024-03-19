import org.gradle.api.publish.maven.MavenPom

plugins {
    kotlin("multiplatform") version "1.9.23"

    id("org.jetbrains.dokka") version "1.9.20"

    id("maven-publish")
}
repositories {
    mavenCentral()
}

group = "com.github.chantsune"
version = "0.2.0"

kotlin {
    explicitApiWarning()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js(IR) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        nodejs {
        }
    }
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX64()
    androidNativeX86()
    linuxX64()
    linuxArm64()
    mingwX64()
    macosX64()
    ios()
    tvos()
    watchos()
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
    doLast {
        val outputDir = outputDirectory.get().asFile.absolutePath
        File("$outputDir/index.html").apply {
            writeText(
                """
            <html><script>document.location = "./${project.name}"</script></html>
            """.trimIndent()
            )
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

val String.isAppleOS: Boolean get() = contains("Tvos")
        || contains("Ios")
        || contains("Watchos")
        || contains("Macos")

tasks.create("publishAppleToMavenLocal") {
    group = "publishing"
    tasks.withType<PublishToMavenLocal>().filter { it.name.isAppleOS }.all {
        dependsOn.add(it)
    }
}

tasks.create("publishAppleToBintrayRepository") {
    group = "publishing"
    tasks.withType<PublishToMavenRepository>().filter { it.name.isAppleOS }.all {
        dependsOn.add(it)
    }
}

publishing {
    fun getProperty(propertyName: String, envName: String): String? {
        return findProperty(propertyName) as? String ?: System.getenv(envName)
    }

    fun getBintrayUser(): String? {
        return getProperty("bintray_user", "BINTRAY_USER")
    }

    fun getBintrayKey(): String? {
        return getProperty("bintray_key", "BINTRAY_KEY")
    }

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

    repositories {
        maven {
            name = "bintray"
            val bintrayUsername = "chantsune"
            val bintrayRepoName = "kotlin-enum-extensions"
            val bintrayPackageName = "com.github.chantsune.kotlin.enumext"
            setUrl("https://api.bintray.com/content/$bintrayUsername/$bintrayRepoName/$bintrayPackageName/${project.version};publish=0;override=1")

            credentials {
                username = getBintrayUser()
                password = getBintrayKey()
            }
        }
    }
}
