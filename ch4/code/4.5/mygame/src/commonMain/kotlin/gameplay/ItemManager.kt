package gameplay

import com.soywiz.korge.view.*

object ItemManager {

    var BASE_FLOOR: Floor? = null

    var items = arrayListOf<Item?>()

    var scoreItem = arrayListOf<View>()
    var hurtItem = arrayListOf<View>()

    suspend fun init() {
        items.clear()

        val stageValue = listOf(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 2, 2, 3, 2, 2, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        )

        for (y in 0..7) {
            for (x in 0..13*2) {
                val item = when (ItemType.values()[stageValue[y * 14*2 + x]]) {
                    ItemType.FLOOR -> {
                        Floor().apply {
                            load()
                            position(x, y)
                        }
                    }
                    ItemType.COIN -> {
                        Coin().apply {
                            load()
                            position(x, y)
                        }
                    }
                    ItemType.OBSTACLE -> {
                        Obstacle().apply {
                            load()
                            position(x, y)
                        }
                    }
                    ItemType.ENEMY -> {
                        Enemy().apply {
                            load()
                            position(x, y)
                        }
                    }
                    else -> {
                        null
                    }
                }
                when (item) {
                    is Coin -> {
                        scoreItem.add(item.root)
                    }
                    is Obstacle -> {
                        hurtItem.add(item.root)
                    }
                    is Enemy -> {
                        hurtItem.add(item.root)
                    }
                }
                items.add(item)
            }
        }
    }

    fun load(parentView: Container) {
        items.forEach {
            it?.run {
                parentView.addChild(this)
            }


            if (it is Floor) {
                BASE_FLOOR = it
            }
        }
    }

    fun move() {
        items.forEach {
            it?.move()
        }
    }

    fun stop() {//停止所有物件移動
        items.forEach {
            it?.stop()
        }
        items.clear()
        scoreItem.clear()
        hurtItem.clear()
    }


    fun setCollision(alien: Alien, parentView: Container) {
        items.forEach {
            when (it) {
                is Coin -> {
                    it.addUpdater {
                        if (collidesWith(alien.sprite)) {
                            parentView.removeChild(this)//移除金幣在遊戲場景上
                        }
                    }
                }
                is Enemy -> {
                    it.addUpdater {
                        if (collidesWithShape(alien.sprite)) {
                            hurtItem.remove(this)//從傷害物件List移除
                        }
                    }
                }
                is Obstacle -> {

                    it.addUpdater {
                        if (collidesWithShape(alien.sprite)) {
                            hurtItem.remove(this)//從傷害物件List移除
                        }
                    }
                }
            }
        }
    }
}