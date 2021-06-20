package scene

import com.soywiz.klock.milliseconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.color.Colors
import com.soywiz.korim.font.readTtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs

class Demo : Scene() {
    override suspend fun Container.sceneInit() {

        val screenWidth = ConfigModule.size.width.toDouble()
        val screenHeight = ConfigModule.size.height.toDouble()

        image(resourcesVfs["mylogo.png"].readBitmap()) {
            anchor(0.5, 0.5)
            scaledWidth = screenWidth
            scaledHeight = screenHeight
            position(scaledWidth / 2, scaledHeight / 2)
        }

        val bitmap = NativeImage(width = 200, height = 100).apply {
            getContext2d().fillText(
                text =
                "Tap to Start",
                x = 0,
                y = 30,
                font = resourcesVfs["NotoSans-Black.ttf"].readTtfFont(),
                fontSize = 30.0,
                color = Colors.BLUE
            )
        }

        var moveHeight = screenHeight - screenHeight / 4 //畫面的四分之三的高度

        val tapString = image(bitmap) {
            position((screenWidth - scaledWidth) / 2, 0.0) //字串y座標歸零
            onClick {
                launchImmediately { sceneContainer.changeTo<Menu>() }
            }
        }

        tapString.addFixedUpdater(100.milliseconds){
            tapString.alpha -= 0.1//字串減少alpha值0.1
            if(tapString.alpha <= 0){
                tapString.alpha = 1.0
            }
            if(tapString.y < moveHeight){
                tapString.y += 20//座標y值增加20
            }
        }
    }
}