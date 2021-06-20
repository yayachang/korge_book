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
	}
}

plugins {
	kotlin("multiplatform") version "1.4.20"
	kotlin("plugin.serialization") version "1.4.20"
}

apply<KorgeGradlePlugin>()

korge {
	id = "yaya.idv.mygame"
	supportBox2d()

	dependencyMulti("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

	// To selectively enable targets
	targetJvm()
	targetJs()
	targetDesktop()
	targetIos()
	targetAndroidIndirect()
}
