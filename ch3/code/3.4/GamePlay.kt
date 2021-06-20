package scene

import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import gameplay.*

class GamePlay : Scene() {

    lateinit var alien: Alien

    override suspend fun Container.sceneInit() {

        val parentView = this

        //加入遊戲背景
        addChild(Background().apply { load() })

        //物件管理
        ItemManager.run {
            init()//初始所有遊戲物件
            load(parentView)//將物件加入GamePlay場景
        }

        //加入外星人
        alien = Alien()
        alien.apply {
            load()
            parentView.addChild(this)
            alignBottomToTopOf(ItemManager.BASE_FLOOR!!)
            x = Item.BASE_WIDTH * 1
            defaultY = alien.y
            walk()//外星人走路
        }


    }

    override suspend fun Container.sceneMain() {
        keys {
            down(Key.SPACE) {
                alien.jump()//外星人跳躍
            }
        }

        addUpdater(fun Container.(it: TimeSpan) {//更新遊戲物件狀態
            ItemManager.move()//金幣、敵人、地板都放在這裡管理，所以一起移動
            alien.update()//外星人狀態更新
        })
    }

}


