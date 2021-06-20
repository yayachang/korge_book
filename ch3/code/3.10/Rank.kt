package scene

import ConfigModule
import SharedData
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.color.Colors
import com.soywiz.korim.font.readTtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.VfsOpenMode
import com.soywiz.korio.file.std.resourcesVfs
import gameplay.Score

class Rank : Scene() {

    lateinit var rankImage: Image
    lateinit var resultString: Image

    override suspend fun Container.sceneInit() {

        val parentView = this

        val screenWidth = ConfigModule.size.width.toDouble()
        val screenHeight = ConfigModule.size.height.toDouble()
        val ttfFont = resourcesVfs["NotoSans-Black.ttf"].readTtfFont()

        //加入背景圖
        image(resourcesVfs["bg_shroom.png"].readBitmap()) {
            anchor(0.5, 0.5)
            scaledWidth = screenWidth
            scaledHeight = screenHeight
            position(scaledWidth / 2, scaledHeight / 2)
        }
        //加入排行榜文字
        val textString = "RANK"
        val fontSize = 40.0
        val rankBitmap = NativeImage(width = 120, height = 100).apply {
            getContext2d().fillText(
                textString,
                x = 0,
                y = fontSize,
                font = ttfFont,
                fontSize = fontSize,
                color = Colors.BLUE
            )
        }

        rankImage = image(rankBitmap) {
            x = (parentView.width - width) / 2
            y += 50
        }

        //加入我的最高分文字
        val myScoreString = "My Highest Score:"
        val myScorefontBitmap = NativeImage(width = 400, height = 100).apply {
            getContext2d().fillText(
                myScoreString,
                x = 0,
                y = fontSize,
                font = ttfFont,
                fontSize = fontSize,
                color = Colors.BLACK
            )
        }

        resultString = image(myScorefontBitmap) {
            alignTopToBottomOf(rankImage)
            x = (parentView.width - width) / 2 - 100
            y += 20.0
        }

        //加入分數
        Score().apply {
            load()
            parentView.addChild(this)
            initPosition()
            nowValue = getHighestScore()
            update()
            alignTopToBottomOf(rankImage)
            alignLeftToRightOf(resultString)
            y += 20.0
        }

        //加上下一步按鈕
        image(resourcesVfs["next.png"].readBitmap()) {
            alignTopToBottomOf(resultString)
            x = (parentView.width - width) / 2
            onClick {
                launchImmediately {
                    sceneContainer.changeTo<Menu>()
                }
            }
        }
    }

    suspend fun getHighestScore(): Int {//取得目前最高分數
        val scoreFile = resourcesVfs["score.txt"]
        if (!scoreFile.exists()) {//檢查檔案是否存在
            scoreFile.open(VfsOpenMode.CREATE_NEW)//不存在就建立新檔案
        }
        val savedScoreString = scoreFile.readString()
        var savedScore = 0
        if (savedScoreString.isNotEmpty()) {//檢查前次儲存的分數是否是空值或0
            savedScore = savedScoreString.toInt()//非空值跟0的值就讀取出來
        }
        println("savedScore = $savedScore")
        if (SharedData.GAME_SCORE > savedScore) {//若有新高分記錄
            savedScore = SharedData.GAME_SCORE
            scoreFile.writeString(SharedData.GAME_SCORE.toString())//將分數寫入檔案
            println("new savedScore = $savedScore")
        }
        return savedScore//回傳目前最高分數
    }
}
