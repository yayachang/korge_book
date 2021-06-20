package gameplay

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.vector.rect

class Coin : Item() {

    override suspend fun load() {
        val bitamp = resourcesVfs["hud_coins.png"].readBitmap()
        //solidRect(bitamp.width,  bitamp.height, Colors.BLUE).xy(0, 0)
        mImage = image(bitamp)
        hitShape {
            rect(0,0, bitamp.width, bitamp.height)
        }
    }

    override suspend fun position(x: Int, y: Int) {
        defaultX = x * BASE_WIDTH + (BASE_WIDTH - (mImage?.width ?: 0.0)) / 2
        position(defaultX, y * BASE_HEIGHT + (BASE_HEIGHT - (mImage?.height ?: 0.0)) / 2)
    }
}
