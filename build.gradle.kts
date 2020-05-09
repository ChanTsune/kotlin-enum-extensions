import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.70"

    id("maven-publish")
}
repositories {
    mavenCentral()
}
group = "com.example"
version = "0.0.0"

val NamedDomainObjectContainer<KotlinSourceSet>.jvmMain get() = named<KotlinSourceSet>("jvmMain")
val NamedDomainObjectContainer<KotlinSourceSet>.jvmTest get() = named<KotlinSourceSet>("jvmTest")
val NamedDomainObjectContainer<KotlinSourceSet>.jsMain get() = named<KotlinSourceSet>("jsMain")
val NamedDomainObjectContainer<KotlinSourceSet>.jsTest get() = named<KotlinSourceSet>("jsTest")
val NamedDomainObjectContainer<KotlinSourceSet>.macosMain get() = named<KotlinSourceSet>("macosMain")
val NamedDomainObjectContainer<KotlinSourceSet>.macosTest get() = named<KotlinSourceSet>("macosTest")


kotlin {
    jvm()
    js {
        browser {
        }
        nodejs {
        }
    }
    // For ARM, should be changed to iosArm32 or iosArm64
    // For Linux, should be changed to e.g. linuxX64
    // For MacOS, should be changed to e.g. macosX64
    // For Windows, should be changed to e.g. mingwX64
    macosX64("macos")
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        jvmMain {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        jvmTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        jsMain {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        jsTest {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        macosMain {
        }
        macosTest {
        }
    }
}
