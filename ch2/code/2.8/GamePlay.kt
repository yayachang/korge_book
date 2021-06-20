package scene

import com.soywiz.klock.seconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.scene.delay
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.ui.uiTextButton
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
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

        val spriteMap = resourcesVfs["green_alien_walk.png"].readBitmap()
        val alienWalkAnimation = SpriteAnimation(
            spriteMap = spriteMap,
            spriteWidth = 72,
            spriteHeight = 97,
            marginTop = 0,
            marginLeft = 0,
            columns = 11,
            rows = 1,
            offsetBetweenColumns = 0,
            offsetBetweenRows = 0
        )
        val alien = sprite(alienWalkAnimation)
        alien.spriteDisplayTime = 0.1.seconds
        alien.playAnimationLooped()

        launchImmediately{
            while (true) {
                alien.tween(alien::x[0.0, 512.0], time = 5.seconds)
                delay(1.seconds)
            }
        }
    }
}
