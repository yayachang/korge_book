package scene

import ConfigModule
import SharedData
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.color.Colors
import com.soywiz.korim.font.TtfFont
import com.soywiz.korim.font.readTtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korio.stream.openSync
import gameplay.Alien
import gameplay.Score

class GameOver : Scene() {

    lateinit var fontBitmap: Bitmap
    lateinit var resultString: Image

    override suspend fun Container.sceneInit() {
        val parentView = this

        val screenWidth = ConfigModule.size.width.toDouble()
        val screenHeight = ConfigModule.size.height.toDouble()

        //加入闖關背景圖案
        if (SharedData.IS_GAME_OVER_SUCCESS) {//闖關成功
            image(resourcesVfs["gameover_success.png"].readBitmap()) {
                anchor(0.5, 0.5)
                scaledWidth = screenWidth
                scaledHeight = screenHeight
                position(scaledWidth / 2, scaledHeight / 2)
            }
        } else {//闖關失敗
            image(resourcesVfs["gameover_failed.png"].readBitmap()) {
                anchor(0.5, 0.5)
                scaledWidth = screenWidth
                scaledHeight = screenHeight
                position(scaledWidth / 2, scaledHeight / 2)
            }
        }

        //加入文字
        val textString = if (SharedData.IS_GAME_OVER_SUCCESS) {
            "You're Success!!!"
        } else {
            "You're Failure!!"
        }
        val fontSize = 40.0
        fontBitmap = NativeImage(width = 350, height = 100).apply {
            getContext2d().fillText(
                textString,
                x = 0,
                y = fontSize,
                font = resourcesVfs["NotoSans-Black.ttf"].readTtfFont(),
                fontSize = fontSize,
                color = Colors.BLUE
            )
        }

        resultString = image(fontBitmap) {
            position((parentView.width  - width) / 2, 50.0)
        }

        //加入分數
        val score = Score().apply {
            load()
            parentView.addChild(this)
            initPosition()
            alignTopToBottomOf(resultString)
            nowValue = SharedData.GAME_SCORE
            update()
        }

        //外星人闖關後表情
        Alien().apply {
            load(SharedData.SELECT_RUN_ALIEN)
            parentView.addChild(this)
            alignTopToBottomOf(resultString)
            alignRightToLeftOf(score)
            x -= 30
            if (SharedData.IS_GAME_OVER_SUCCESS) {
                goal()
            } else {
                hurt()
            }
        }

        //加上下一步按鈕
        image( resourcesVfs["next.png"].readBitmap()) {
            alignTopToBottomOf(score)
            alignLeftToLeftOf(score)
            y+= 30

            onClick {
                launchImmediately {
                    sceneContainer.changeTo<Rank>()
                }
            }
        }
    }
}
