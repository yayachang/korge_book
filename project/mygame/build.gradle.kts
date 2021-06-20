import com.soywiz.korge.gradle.KorgeGradlePlugin
import com.soywiz.korge.gradle.korge

buildscript {
	val korgePluginVersion: String by project

	repositories {
		mavenLocal()
		maven { url = uri("https://dl.bintray.com/korlibs/korlibs") }
		maven { url = uri("https://plugins.gradle.org/m2/") }
		mavenCentral()
		google()
		jcenter()
	}
	dependencies {
		classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
		classpath("org.jetbrains.kotlin:kotlin-serialization:1.2.1")

	}
}

plugins {
	kotlin("multiplatform") version "1.4.30"
	kotlin("plugin.serialization") version "1.4.30"
}

apply<KorgeGradlePlugin>()

korge {
	id = "yaya.idv.mygame"
	supportBox2d()

	dependencyMulti("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")

	// To enable all targets at once
	//targetAll()

	// To enable targets based on properties/environment variables
	//targetDefault()

	// To selectively enable targets
	targetJvm()
	targetJs()
	targetDesktop()
	targetIos()
	targetAndroidIndirect()
	targetAndroidDirect()

}

