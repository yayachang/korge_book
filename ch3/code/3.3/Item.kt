package gameplay

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image

abstract class Item : Container() {

    val BASE_WIDTH = 70.0 //物件寬的單位
    val BASE_HEIGHT = 70.0//物件高的單位

    var moveSpeed = 4 //移動速度
    var defaultX = 0.0 //初始x位置
    var mImage: Image? = null //物件圖片

    abstract suspend fun load()//載入資料
    abstract suspend fun position(x: Int, y: Int)//擺放位置

    open fun move() {//移動
        x -= moveSpeed
    }
}
