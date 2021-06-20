package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import gameplay.*

class GamePlay : Scene() {


    override suspend fun Container.sceneInit() {
        addChild(Background().apply { load() })

        val stageValue = listOf<Int>(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 2, 2, 3, 2, 2, 4, 2,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        )
        
        var items = arrayListOf<Item?>()
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
        val parentView = this
        items.forEach {
            it?.also {
                parentView.addChild(it)
            }
        }
    }
}
