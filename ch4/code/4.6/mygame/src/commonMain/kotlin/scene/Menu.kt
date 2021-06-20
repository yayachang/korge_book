package scene

import ConfigModule
import com.soywiz.kds.iterators.fastForEachReverse
import com.soywiz.klock.milliseconds
import com.soywiz.klock.seconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.scene.delay
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.bitmap.slice
import com.soywiz.korim.color.Colors
import com.soywiz.korim.font.readTtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launch
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing
import gameplay.Alien
import gameplay.Character

class Menu : Scene() {

    var selectHeadImage: Image? = null//被選擇的外星人頭像
    var goImage: Image? = null//開始按鈕Image

    var selectRunAlien: Alien? = null//被選擇的外星人物件

    var statToGo = false
    override suspend fun Container.sceneInit() {

        val parentView = this

        val screenWidth = ConfigModule.size.width.toDouble()
        val screenHeight = ConfigModule.size.height.toDouble()

        //載入選單背景
        image(resourcesVfs["menu.png"].readBitmap()) {
            anchor(0.5, 0.5)
            scaledWidth = screenWidth
            scaledHeight = screenHeight
            position(scaledWidth / 2, scaledHeight / 2)
        }

        //放入提示選外星人的文字
        val showString = "Choose An Alien to GO"
        val fontSize = 40.0
        val bitmap = NativeImage(width = 500, height = 100).apply {
            getContext2d().fillText(
                text =
                showString,
                x = 0,
                y = 40,
                font = resourcesVfs["NotoSans-Black.ttf"].readTtfFont(),
                fontSize = fontSize,
                color = Colors.BLUE
            )
        }

        val tapString = image(bitmap) {
            position((screenWidth - width)/2, 50.0) //字串座標
        }


        //宣告五個外星人物件
        val alienArray = arrayListOf<Alien>()//外星人陣列
        for (i in 0 until Character.values().count()) {
            val alien = Alien().apply {
                load(Character.values()[i])
                onClick {
                    selectRunAlien?.stop()//被選到前先讓前一個被選到的停止動作
                    selectRunAlien = this//目前被選到的外星人
                    SharedData.SELECT_RUN_ALIEN = this.character//暫存在SharedData.kt
                    walk()//進行走的動作
                }
            }
            alienArray.add(alien)//加入外星人陣列
        }
        //加入場景並計算擺放間隔
        var totalAlienWidth = 0.0
        alienArray.fastForEachReverse {
            totalAlienWidth += it.width//所有外星人的寬度加總
            parentView.addChild(it)//為了讓左邊外星人往右走時在其他外星人之上，所以順序需要反過來加入遊戲場景
        }
        //算出外星人擺放間隔
        val alienSpace = (screenWidth -totalAlienWidth) / 6

        //擺放外星人位置
        for (i in 0 until alienArray.count()) {
            alienArray[i].apply {
                when (i) {
                    0 -> {
                        alignBottomToBottomOf(parentView)
                        alignLeftToLeftOf(parentView)
                        y -= 42
                    }
                    else -> {
                        alignBottomToBottomOf(alienArray[i - 1])
                        alignLeftToRightOf(alienArray[i - 1])
                    }
                }
                x += alienSpace
            }
        }


        //貼上開始遊戲Go
        val goBitmp = resourcesVfs["go.png"].readBitmap()

        addUpdater {

            selectRunAlien?.let {
                if (selectHeadImage == null) {//建立新的被選取的外星人頭像，放在題示文字的下方
                    selectHeadImage = image(it.headBitmap) {
                        alignTopToBottomOf(tapString)
                        centerXOn(parentView)
                        scale = 1.5
                        x -= 100
                        y += 10

                    }

                    goImage = image(goBitmp) {//加入開始遊戲按鈕，放在題示文字的下方並放在被選取外星人頭像的右手邊
                        anchor(0.5, 0.5)
                        alignTopToBottomOf(tapString)
                        centerXOn(parentView)
                        x += 50
                        y -= height/4

                        onClick {//處理點擊開始遊戲按鈕的事件
                            launch {
                                if (!statToGo) {
                                    statToGo = true
                                    delay(((Character.values().size - selectRunAlien!!.character.ordinal) * 0.5).seconds)
                                    sceneContainer.changeTo<GamePlay>()
                                }
                            }
                        }

                        launch {
                            while (true) {
                                tween(this::rotation[ (-16).degrees], time = 100.milliseconds, easing = Easing.EASE_IN_OUT)
                                tween(this::rotation[ (+16).degrees], time = 100.milliseconds, easing = Easing.EASE_IN_OUT)
                            }
                        }
                    }
                } else {
                    //如果選取頭像的物件已經被建立，只要更新外星人的頭像圖片即可
                    selectHeadImage?.bitmap = it.headBitmap.slice()
                }


                //若開始遊戲，外星人會往右邊開始走動
                if (statToGo) {
                    it.x += 6
                }
            }


        }

    }
}

