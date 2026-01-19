plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":customers:domain"))
    implementation("org.springframework:spring-context:6.0.14")
}