plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.22"

}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }



    sourceSets {
        val ktorVersion = "2.3.1"

        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-auth:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")

                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")


                api(libs.androidx.datastore.preferences.core)
                api(libs.androidx.datastore.core.okio)



                //Coroutines
                implementation(libs.kotlinx.coroutines.core)

                //Logger
                implementation(libs.napier)

                api(libs.koin.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                api(libs.androidx.datastore.preferences.core)
                api(libs.androidx.datastore.core.okio)

                api(libs.koin.core)

            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }


        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.alhussain.kmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
//dependencies {
//    implementation("io.ktor:ktor:2.3.1")
//}
