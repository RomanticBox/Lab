plugins {
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/base", "src/main", "src/test"))
            exclude { t ->
                t.getFile().name == "Main.java"
            }
        }
    }

    register("rtest") {
        java {
            setSrcDirs(listOf("src/base", "src/main"))
        }
    }
}

dependencies {
    val junitVersion = "5.5.2"
    val junit4Version = "4.12"
    val junitPlatformVersion = "1.5.2"

    implementation("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
    implementation("org.hamcrest:hamcrest:2.1")
    compileOnly("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    implementation(files("lib/testrunner.jar"))
}

fun testSubmissionIntegrity(): String {
    val mainDir = File("src/main")
    val pledgeFile = mainDir.listFiles { file, s ->
        file == mainDir && s.endsWith(".txt")
    }!![0]
    val studentId = pledgeFile.name.substringBeforeLast('.')

    if (studentId.substring(4) == "xxxxxx") {
        throw GradleException("Rename $studentId.txt to your actual student id#.")
    }

    if (!pledgeFile.readText().startsWith("In completing this assignment, I pledge that I have not given nor received any unauthorized assistance.")) {
        throw GradleException("Pledge does not match on $studentId.txt file. Follow the guideline.")
    }

    println("Check your pledge file to make sure!!")
    return studentId
}

tasks {
    javadoc {
        enabled = false
    }

    compileTestJava {
        enabled = false
    }

    build {
        enabled = false
    }

    jar {
        enabled = false
    }

    register("run") {
        enabled = false
        println("This task is disabled.")
        println("To test your implementation: % gradlew -q runTestRunner")
        println("To make a zip file for submission: % gradlew -q zipSubmission")
        println("To run the main class: % gradlew -q runMain")
    }

    register<JavaExec>("runMain") {
        standardInput = System.`in`
        dependsOn("rtestClasses")
        classpath = sourceSets["rtest"].runtimeClasspath
        main = "Main"
    }

    register<JavaExec>("runTestRunner") {
        dependsOn("classes")
        args(listOf("HeapTests", "SchedulerTests"))

        classpath = sourceSets["main"].runtimeClasspath
        main = "testrunner.TestRunner"
    }

    register<Zip>("zipSubmission") {
        val studentId = testSubmissionIntegrity();
        setProperty("destinationDirectory", project.buildDir)
        setProperty("archiveFileName",  "$studentId.zip")

        dependsOn("classes")

        from("src/main") {
            include("*.java")
            exclude("Main.java")
            include("*.txt")
        }

        println("Your submission file lies on ${project.buildDir}/$studentId.zip")
    }
}
