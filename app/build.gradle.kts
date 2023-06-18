plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.movieapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 22
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    applicationVariants.all{
        val variantName = name
//        addJavaSourceFoldersToModel(
//            File("build/generated/ksp/${variantName}/kotlin")
//        )

        kotlin.sourceSets{
            getByName(variantName) {
                kotlin.srcDir("build/generated/ksp/${variantName}/kotlin")
            }
        }
    }
}

ksp{
    arg("compose-destinations.generateNavGraphs", "false")
}

object Version{
    const val compose_version = "1.5.0-alpha04"
    const val compose_nav = "2.5.3"
    const val activity_compose_version = "1.6.1"
    const val viewmodel_compose_version = "2.5.1"
    const val coroutine = "2.6.1"
    const val material3 = "1.1.0-rc01"
    const val material3_icon = "1.4.3"
    const val ktor = "2.3.1"
    const val paging = "3.1.1"
}

object Lib {
    const val compose_bom ="androidx.compose:compose-bom:2023.04.01"
    const val material3 = "androidx.compose.material3:material3"
    const val material = "aandroidx.compose.material:material"
    const val compose_ui = "androidx.compose.ui:ui"
    const val compose_preview = "androidx.compose.ui:ui-tooling-preview"
    const val compose_tooling = "androidx.compose.ui:ui-tooling"
    const val compose_graphic = "androidx.compose.ui:ui-graphics"
    const val compose_navigation = "androidx.navigation:navigation-compose:${Version.compose_nav}"
    const val material_icon = "androidx.compose.material:material-icons-core:${Version.material3_icon}"
    const val activity_compose = "androidx.activity:activity-compose:1.6.1"
    const val viewmodel_compose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.viewmodel_compose_version}"
    const val kotlin_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1"
    const val viewmodel_coroutine = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.coroutine}"
    const val lifecycle_coroutine = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.coroutine}"
    const val compose_destination = "io.github.raamcosta.compose-destinations:core:1.9.42-beta"
    const val compose_destination_ksp = "io.github.raamcosta.compose-destinations:ksp:1.9.42-beta"
    const val koin_compose = "io.insert-koin:koin-androidx-compose:3.4.5"
    const val koin_annotation = "io.insert-koin:koin-annotations:1.2.2"
    const val koin_ksp = "io.insert-koin:koin-ksp-compiler:1.2.2"
    const val ktor = "io.ktor:ktor-client-core:2.3.1"
    const val ktor_android_engine = "io.ktor:ktor-client-android:${Version.ktor}"
    const val ktor_logging = "io.ktor:ktor-client-logging:${Version.ktor}"
    const val ktor_gson = "io.ktor:ktor-serialization-gson:${Version.ktor}"
    const val ktor_content_negotiation = "io.ktor:ktor-client-content-negotiation:${Version.ktor}"
    const val ktor_auth = "io.ktor:ktor-client-auth:${Version.ktor}"
    const val paging3 = "androidx.paging:paging-runtime:${Version.paging}"
    const val paging3_compose = "androidx.paging:paging-compose:3.2.0-beta01"
    const val coil = "io.coil-kt:coil-compose:2.4.0"
    const val youtube_player = "com.pierfrancescosoffritti.androidyoutubeplayer:core:12.0.0"
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    //compose
    implementation(Lib.compose_preview)
    implementation(Lib.material3)
    implementation(Lib.material_icon)
    implementation(Lib.activity_compose)
    implementation(Lib.viewmodel_compose)
    implementation(Lib.compose_ui)
    implementation(platform(Lib.compose_bom))
    implementation(Lib.lifecycle_coroutine)
    implementation(Lib.activity_compose)
    implementation(Lib.compose_graphic)
    implementation(Lib.kotlin_coroutine)
    androidTestImplementation(platform(Lib.compose_bom))
    debugImplementation(Lib.compose_tooling)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //coroutine
    implementation(Lib.viewmodel_coroutine)
    implementation(Lib.lifecycle_coroutine)

    //koin
    implementation(Lib.koin_compose)
    implementation(Lib.koin_annotation)
    ksp(Lib.koin_ksp)

    //navigation
    implementation(Lib.compose_navigation)
    implementation(Lib.compose_destination)
    ksp(Lib.compose_destination_ksp)

    //image loader
    implementation(Lib.coil)

    //ktor
    implementation(Lib.ktor)
    implementation(Lib.ktor_android_engine)
    implementation(Lib.ktor_logging)
    implementation(Lib.ktor_gson)
    implementation(Lib.ktor_content_negotiation)
    implementation(Lib.ktor_auth)

    //paging3
    implementation(Lib.paging3)
    implementation(Lib.paging3_compose)

    //youtube player
    implementation(Lib.youtube_player)

    //testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}