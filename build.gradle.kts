plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	id("com.github.davidmc24.gradle.plugin.avro") version "1.7.0" // Avro plugin
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_22
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://packages.confluent.io/maven/")
	}
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.kafka:spring-kafka")

	implementation("org.apache.avro:avro:1.11.0")
	implementation("org.apache.avro:avro-tools:1.11.0")
	implementation("io.confluent:kafka-avro-serializer:6.2.0")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("printTestTasks") {
	group = "Reporting"
	description = "Prints all test tasks"

	doLast {
		tasks.withType<Test> {
			println("Test task: $name")
		}
	}
}
sourceSets {
	main {
		// Specify the Avro source directory (where your .avsc files are located)
		resources {
			srcDir("src/main/avro")
		}

		// Specify where the generated Avro Java classes will be placed
		java {
			srcDir(layout.buildDirectory.dir("generated-main-avro-java"))
		}
	}
}

