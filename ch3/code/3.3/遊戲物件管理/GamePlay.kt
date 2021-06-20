package scene

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import gameplay.*

class GamePlay : Scene() {
    override suspend fun Container.sceneInit() {
        //加入遊戲背景到場景
        addChild(Background().apply { load() })

        val parentView = this
        //物件管理
        ItemManager.run{
            init()//初始所有遊戲物件
            load(parentView)//加入遊戲物件到場景
        }
    }
}
