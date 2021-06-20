package scene


import com.soywiz.korev.Key
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*

class Demo : Scene() {
    override suspend fun Container.sceneInit() {
        val input = views.input

        text("").addUpdater {
            when {
                input.keys.justPressed(Key.SPACE) -> {
                    text += "key pressed\n"
                }
                input.keys.pressing(Key.SPACE) -> {
                    text += "key pressing\n"

                }
                input.keys.justReleased(Key.SPACE) -> {
                    text += "key justReleased\n"

                }
            }
        }

    }
}




