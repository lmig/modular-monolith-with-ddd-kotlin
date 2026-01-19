plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(":orders:domain"))
    implementation("org.springframework:spring-context:6.0.14")

    // MapStruct for mapping DTO <-> domain
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
}