package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*

class Demo : Scene() {
    override suspend fun Container.sceneInit() {
        val input = views.input
        text("").addUpdater {
            text = "Mouse Position:${input.mouse}"
            position(input.mouse.x, input.mouse.y)
        }
    }
}




