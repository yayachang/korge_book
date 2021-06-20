package gameplay

import com.soywiz.korge.view.Container

object ItemManager {

    var items = arrayListOf<Item?>()

    suspend fun init() {
        items.clear()

        val stageValue = listOf(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 2, 2, 3, 2, 2, 4, 2,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        )

        for (y in 0..7) {
            for (x in 0..13) {
                val item = when (ItemType.values()[stageValue[y * 14 + x]]) {
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
                items.add(item)
            }
        }
    }

    fun load(parentView: Container) {
        items.forEach {
            it?.run {
                parentView.addChild(this)
            }
        }
    }
}