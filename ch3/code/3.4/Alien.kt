package gameplay

import com.soywiz.klock.seconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korge.view.sprite
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Alien : Container() {

    enum class STATUS {
        WALK,
        JUMP,
        FALL,
        HURT,
        DEAD,
        GOAL
    }

    var defaultY = 0.0//角色初始y位置
    var status = STATUS.WALK//角色狀態

    lateinit var spriteMap: Bitmap

    //走路
    lateinit var walkAnimation: SpriteAnimation

    lateinit var sprite: Sprite//目前圖片

    //跳
    lateinit var jumpBitmap: Bitmap

    suspend fun load() {//載入資源
        spriteMap = resourcesVfs["green_alien_walk.png"].readBitmap()

        walkAnimation = SpriteAnimation(
            spriteMap = spriteMap,
            spriteWidth = 72,
            spriteHeight = 97,
            marginTop = 0,
            marginLeft = 0,
            columns = 11,
            rows = 1,
            offsetBetweenColumns = 0,
            offsetBetweenRows = 0
        )
        sprite = sprite(walkAnimation) {
            spriteDisplayTime = 0.1.seconds
        }

        jumpBitmap = resourcesVfs["green_alien_jump.png"].readBitmap()
        
    }

    fun walk() {//走路動作
        changeStatus()
        status = STATUS.WALK
        sprite = sprite(walkAnimation) {
            spriteDisplayTime = 0.1.seconds
        }.apply {
            playAnimationLooped()
        }
    }

    fun jump() {//跳躍動作
        if (status != STATUS.JUMP && status != STATUS.FALL) {
            changeStatus()
            sprite = sprite(jumpBitmap)
            status = STATUS.JUMP
        }
    }

    fun changeStatus(){//狀態改遍
        sprite.stopAnimation()
        sprite.removeFromParent()
    }

    fun fall() {//掉落動作
        status = STATUS.FALL
    }

    fun update() {//狀態及數值更新
        when (status) {
            STATUS.JUMP -> {
                y -= 4
                if (y <= defaultY - 100) {
                    fall()
                }
            }
            STATUS.FALL -> {
                y += 6
                if (y >= defaultY) {
                    changeStatus()
                    walk()
                }
            }
        }
    }

}
