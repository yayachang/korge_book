import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import scene.*

suspend fun main() = Korge(Korge.Config(module = ConfigModule))
object ConfigModule : Module() {
	override val bgcolor: RGBA = Colors["#2b2b2b"]
	override val size = SizeInt(512, 512)
	override val mainScene = Splash::class
	override suspend fun AsyncInjector.configure() {
		mapPrototype { Splash() }
		mapPrototype { Menu() }
		mapPrototype { GamePlay() }
		mapPrototype { GameOver() }
		mapPrototype { Rank() }
	}
}
