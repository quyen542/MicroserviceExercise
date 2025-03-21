plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.mysql:mysql-connector-j:9.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // https://mvnrepository.com/artifact/org.flywaydb/flyway-mysql
    implementation("org.flywaydb:flyway-mysql:9.2.0")
    // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
    implementation("org.flywaydb:flyway-core:9.2.0")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.24.3")

    implementation("org.modelmapper:modelmapper:3.1.1")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // https://mvnrepository.com/artifact/com.nimbusds/nimbus-jose-jwt
    implementation("com.nimbusds:nimbus-jose-jwt:10.0.2")

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka
    implementation("org.springframework.kafka:spring-kafka:3.3.3")

    // https://mvnrepository.com/artifact/org.json/json
    implementation("org.json:json:20250107")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")


}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2024.0.0")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
