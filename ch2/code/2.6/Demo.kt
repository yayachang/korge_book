package scene

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

        image(bitmap) {
            position((screenWidth - scaledWidth) / 2, screenHeight - screenHeight / 4)
            onClick {
                launchImmediately { sceneContainer.changeTo<Menu>() }
            }
        }

    }
}
