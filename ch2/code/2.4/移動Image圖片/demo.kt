package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Demo : Scene() {
    override suspend fun Container.sceneInit() {
        image(resourcesVfs["mylogo.png"].readBitmap()) {
            scaledWidth = ConfigModule.size.width.toDouble()
            scaledHeight = ConfigModule.size.height.toDouble()
            position(100,100)
        }
    }
}
