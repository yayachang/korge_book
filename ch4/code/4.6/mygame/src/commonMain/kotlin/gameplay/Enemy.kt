package gameplay

import com.soywiz.klock.seconds
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.vector.rect

class Enemy : Item() {

    lateinit var spriteMap: Bitmap
    lateinit var walkAnimation: SpriteAnimation
    lateinit var walkSprite: Sprite

    override suspend fun load() {
        spriteMap = resourcesVfs["pink_enemy_walk.png"].readBitmap()
        walkAnimation = SpriteAnimation(
            spriteMap = spriteMap,
            spriteWidth = 51,
            spriteHeight = 28,
            marginTop = 0,
            marginLeft = 0,
            columns = 2,
            rows = 1,
            offsetBetweenColumns = 0,
            offsetBetweenRows = 0
        )

        //solidRect(walkAnimation[0].width, walkAnimation[0].height, Colors.BLUE).xy(0, 0)

        walkSprite = sprite(walkAnimation) {
            spriteDisplayTime = 0.1.seconds
        }
        walkSprite.playAnimationLooped()

        hitShape {
            rect(0.0,0.0, walkSprite.width, walkSprite.height)
        }

    }

    override suspend fun position(x: Int, y: Int) {
        defaultX = x * BASE_WIDTH + (BASE_WIDTH - (walkSprite.width)) / 2
        position(defaultX, y * BASE_HEIGHT + (BASE_HEIGHT - (walkSprite.height)) / 2)
    }
}
