plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.12.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "stswengists.pack.Student"
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

group = "com.stswengists"
version = "1.0-SNAPSHOT"

