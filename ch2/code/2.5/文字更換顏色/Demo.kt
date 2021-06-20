package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Demo : Scene() {
    override suspend fun Container.sceneInit() {

        image(resourcesVfs["mylogo.png"].readBitmap()) {
            anchor(0.5, 0.5)
            scaledWidth = ConfigModule.size.width.toDouble()
            scaledHeight = ConfigModule.size.height.toDouble()
            position(scaledWidth/2, scaledHeight/2)
        }

        text("Tap to Start"){
            val screenWidth = ConfigModule.size.width.toDouble()
            val screenHeight = ConfigModule.size.height.toDouble()
            position((screenWidth - scaledWidth)/2, (screenHeight - screenHeight/4))
            textSize = 30.0
            color = Colors.BLUE
        }

    }
}
