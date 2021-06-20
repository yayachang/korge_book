package scene

import UserScore
import com.soywiz.korau.sound.PlaybackTimes
import com.soywiz.korau.sound.SoundChannel
import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiTextButton
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launch
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs

class Demo : Scene() {
    override suspend fun Container.sceneInit() {
        launch {
            //UserScoreAPI.upload(UserScore(user = "yaya", score = 100))
            UserScoreAPI.getRankList()
        }
    }
}


