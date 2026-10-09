plugins {
    `java-library`
}

group = "com.cd"
version = "0.0.1-SNAPSHOT"
description = "common-lib"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
val springBootVersion = "4.1.1"
repositories {
    mavenCentral()
}

dependencies {
    val bootBom =
        "org.springframework.boot:spring-boot-dependencies:$springBootVersion"

    api(platform(bootBom))
    annotationProcessor(platform(bootBom))
    api("com.fasterxml.jackson.core:jackson-annotations")
    api("jakarta.persistence:jakarta.persistence-api")
    api("org.hibernate.orm:hibernate-core")
    api("org.springframework.data:spring-data-jpa")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
