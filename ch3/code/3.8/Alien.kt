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
        STAND,
        WALK,
        JUMP,
        FALL,
        HURT
    }



    var status = STATUS.STAND
    var character = Character.GREEN

    lateinit var walkAnimation: SpriteAnimation
    lateinit var hurtAnimation: SpriteAnimation

    lateinit var sprite: Sprite
    lateinit var walkBitmp: Bitmap
    lateinit var headBitmap: Bitmap
    lateinit var standBitmap: Bitmap
    lateinit var jumpBitmap: Bitmap
    lateinit var hurtBitmap: Bitmap
    lateinit var deadBitmap: Bitmap

    var lastSTATUS = STATUS.WALK

    var defaultY = 0.0

    var alienWalkCount = 2
    var alienGoalCount = 2
    var alienWalkSpeed = 0.3
    var alienGoalSpeed = 0.3


    suspend fun load(character: Character) {

        this.character = character

        when(character){
            Character.GREEN->{
                alienWalkCount = 11
                alienWalkSpeed = 0.1
                headBitmap = resourcesVfs["green_alien_head.png"].readBitmap()
                standBitmap = resourcesVfs["green_alien_stand.png"].readBitmap()
                walkBitmp = resourcesVfs["green_alien_walk.png"].readBitmap()
                hurtBitmap = resourcesVfs["green_alien_hurt.png"].readBitmap()
                jumpBitmap = resourcesVfs["green_alien_jump.png"].readBitmap()

            }
            Character.PURPLE->{
                alienWalkCount = 11
                alienWalkSpeed = 0.1
                headBitmap = resourcesVfs["purple_alien_head.png"].readBitmap()
                standBitmap = resourcesVfs["purple_alien_stand.png"].readBitmap()
                walkBitmp = resourcesVfs["purple_alien_walk.png"].readBitmap()
                hurtBitmap = resourcesVfs["purple_alien_hurt.png"].readBitmap()
                jumpBitmap = resourcesVfs["purple_alien_jump.png"].readBitmap()
                deadBitmap = hurtBitmap

            }
            Character.PINK->{
                alienWalkCount = 11
                alienWalkSpeed = 0.1
                headBitmap = resourcesVfs["pink_alien_head.png"].readBitmap()
                standBitmap = resourcesVfs["pink_alien_stand.png"].readBitmap()
                walkBitmp = resourcesVfs["pink_alien_walk.png"].readBitmap()
                hurtBitmap = resourcesVfs["pink_alien_hurt.png"].readBitmap()
                jumpBitmap = resourcesVfs["pink_alien_jump.png"].readBitmap()


            }
            Character.BEIGE->{
                alienWalkCount = 2
                alienWalkSpeed = 0.3
                headBitmap = resourcesVfs["beige_alien_head.png"].readBitmap()
                standBitmap = resourcesVfs["beige_alien_stand.png"].readBitmap()
                walkBitmp = resourcesVfs["beige_alien_walk.png"].readBitmap()
                hurtBitmap = resourcesVfs["beige_alien_hurt.png"].readBitmap()
                jumpBitmap = resourcesVfs["beige_alien_jump.png"].readBitmap()


            }
            Character.YELLOW->{
                alienWalkCount = 2
                alienWalkSpeed = 0.3
                headBitmap = resourcesVfs["yellow_alien_head.png"].readBitmap()
                standBitmap = resourcesVfs["yellow_alien_stand.png"].readBitmap()
                walkBitmp = resourcesVfs["yellow_alien_walk.png"].readBitmap()
                hurtBitmap = resourcesVfs["yellow_alien_hurt.png"].readBitmap()
                jumpBitmap = resourcesVfs["yellow_alien_jump.png"].readBitmap()


            }
        }

        hurtAnimation = SpriteAnimation(spriteMap = hurtBitmap, spriteWidth = hurtBitmap.width, spriteHeight = hurtBitmap.height)
        walkAnimation = SpriteAnimation(
            spriteMap = walkBitmp,
            spriteWidth =  walkBitmp.width/alienWalkCount,
            spriteHeight = walkBitmp.height,
            marginTop = 0,
            marginLeft = 0,
            columns = alienWalkCount,
            rows = 1,
            offsetBetweenColumns = 0,
            offsetBetweenRows = 0
        )
        sprite = sprite(standBitmap)
    }

    fun hurt(): Boolean {
        if (status == STATUS.HURT) {
            return false
        } else {
            changeStatus()
            sprite = sprite(hurtAnimation) {
                spriteDisplayTime = 0.3.seconds
            }.apply {
                playAnimation(1)
                onAnimationCompleted {
                    status = lastSTATUS
                    if (status == STATUS.WALK) {
                        walk()
                    }
                }
            }

            lastSTATUS = status
            status = STATUS.HURT
            return true
        }
    }

    fun stop(){
        changeStatus()
        status = STATUS.STAND
        sprite = sprite(standBitmap)
    }

    fun walk() {
        changeStatus()
        sprite = sprite(walkAnimation) {
            spriteDisplayTime = alienWalkSpeed.seconds
        }.apply {
            playAnimationLooped()
        }
        status = STATUS.WALK
    }

    fun changeStatus() {
        if (sprite != null && sprite.parent != null) {
            sprite.stopAnimation()
            sprite.removeFromParent()
        }
    }

    fun jump() {
        if (status != STATUS.JUMP && status != STATUS.FALL)  {
            changeStatus()
            sprite = sprite(jumpBitmap)
            status = STATUS.JUMP
        }
    }

    fun fall() {
        status = STATUS.FALL
    }

    fun update() {
        when (status) {
            STATUS.HURT -> {
                if (sprite.alpha > 0) {
                    sprite.alpha -= 0.1
                } else if (sprite.alpha <= 0.5) {
                    sprite.alpha = 1.0
                }
            }
            STATUS.JUMP -> {
                y -= 4
                if (y <= defaultY - 100) {
                    fall()
                }
            }
            STATUS.FALL -> {
                y += 6
                if (y >= defaultY) {
                    walk()
                }
            }
        }
    }
}
