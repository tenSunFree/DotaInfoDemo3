apply {
    from("$rootDir/library-build.gradle")
}


plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(SqlDelight.plugin)
}

dependencies {
    // "implementation"(project(Modules.heroDomain))

    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)

    "implementation"(SqlDelight.runtime)

    // "implementation"(project(Modules.core))
}

sqldelight {
    database("BaseDb") {
        packageName = "com.example.dotainfodemo3.datasource"
        sourceFolders = listOf("sqldelight")
    }
}