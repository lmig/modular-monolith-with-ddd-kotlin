plugins {
    kotlin("jvm") version "1.9.10" apply false
    id("org.springframework.boot") version "3.2.0" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    group = "io.example"
    version = "0.1.0-SNAPSHOT"
    dependencies {
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
    }
}