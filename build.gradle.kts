plugins {
	kotlin("jvm") version "2.2.10"
	kotlin("plugin.spring") version "2.2.10"
	id("org.springframework.boot") version "4.0.0-M3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.greg"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Greg"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(24)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    //implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.postgresql:postgresql")

    implementation("org.springframework.boot:spring-boot-flyway")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")

    implementation("tools.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}
