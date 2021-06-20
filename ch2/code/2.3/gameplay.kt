package scene

import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiTextButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.text
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.Point

class GamePlay() : Scene() {
    val textPos = Point(128, 128)
    val buttonWidth = 256.0
    val buttonHeight = 32.0
    val buttonPos = Point(128, 128 + 32)

    override suspend fun Container.sceneInit() {
        //顯示目前我是在GamePlay畫面
        text("I'm in ${GamePlay::class.simpleName}") {
            position(textPos)
        }
        //進到遊戲結束畫面的觸發按鈕
        uiTextButton(buttonWidth, buttonHeight) {
            text = "Go to GameOver"
            position(buttonPos)
            onClick {
                launchImmediately { sceneContainer.changeTo<GameOver>() }
            }
        }
    }
}
