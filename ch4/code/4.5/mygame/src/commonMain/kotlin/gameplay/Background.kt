package gameplay

import com.soywiz.korge.view.image
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Background : Item() {

    override suspend fun load() {
        mImage = image(resourcesVfs["bg_shroom.png"].readBitmap())
    }

    override suspend fun position(x: Int, y: Int) {
    }
}
