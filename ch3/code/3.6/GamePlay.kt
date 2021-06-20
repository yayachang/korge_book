package scene

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import gameplay.*

class GamePlay : Scene() {

    lateinit var alien: Alien
    lateinit var blood: Blood
    lateinit var score: Score
    lateinit var gameTimer: GameTimer

    override suspend fun Container.sceneInit() {

        val parentView = this

        //加入遊戲背景
        addChild(Background().apply { load() })

        //加入血條
        blood = Blood().apply {
            load()
            parentView.addChild(this)
            initPosition()

        }
        //加入分數
        score = Score().apply {
            load()
            parentView.addChild(this)
            initPosition()
        }
        //加入倒數計時
        gameTimer = GameTimer().apply {
            load()
            parentView.addChild(this)
            initPosition()
        }


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

        //設定外星人偵測到碰撞行為
        alien.addUpdater {
            if(collidesWithShape(ItemManager.scoreItem)){
                score.plus()//分數加1
            }
            if(collidesWithShape(ItemManager.hurtItem)){
                if(hurt()) {
                    blood.minus()//血包減1
                }
            }
        }
        //設定遊戲物件偵測到碰撞行為
        ItemManager.setCollision(alien, this)


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
            blood.update()//血條更新
            score.update()//分數更新
            gameTimer.update()//倒數計時更新
        })

        addFixedUpdater(1.seconds){//每秒倒數
            gameTimer.minus()
        }

        //倒數計時開始
        gameTimer.start()
    }

}


