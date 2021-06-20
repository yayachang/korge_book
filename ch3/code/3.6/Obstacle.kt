package gameplay

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.vector.rect

class Obstacle : Item() {

    val offsetX = 25.0//寬度的偏移數值
    val offsetY = 20.0//高度的偏移數值

    override suspend fun load() {
        val bitamp = resourcesVfs["rock.png"].readBitmap()
        solidRect(bitamp.width - offsetX, (bitamp.height + offsetY) / 2, Colors.BLUE).xy(offsetX / 2, (bitamp.height + offsetY) / 2)
        mImage = image(bitamp)
        hitShape {
            rect(offsetX / 2, (bitamp.height + offsetY) / 2, bitamp.width - offsetX, (bitamp.height + offsetY) / 2)
        }
    }

    override suspend fun position(x: Int, y: Int) {
        defaultX = x * BASE_WIDTH + (BASE_WIDTH - (mImage?.width ?: 0.0)) / 2
        position(defaultX, y * BASE_HEIGHT + (BASE_HEIGHT - (mImage?.height ?: 0.0)) / 2)
    }
}
