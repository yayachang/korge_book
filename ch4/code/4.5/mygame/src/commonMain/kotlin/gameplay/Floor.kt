package gameplay

import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Floor : Item() {

    override suspend fun load() {
        mImage = image(resourcesVfs["grassMid.png"].readBitmap())
    }

    override suspend fun position(x: Int, y: Int) {
        defaultX = x * BASE_WIDTH + (BASE_WIDTH - (mImage?.width ?: 0.0)) / 2
        position(defaultX, y * BASE_HEIGHT + (BASE_HEIGHT - (mImage?.height ?: 0.0)) / 2)
    }
}
