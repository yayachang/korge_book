package scene

import api.UserScoreAPI
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korio.async.launch

class Demo : Scene() {
    override suspend fun Container.sceneInit() {
        launch {
            //api.UserScoreAPI.upload(UserScore(user = "yaya", score = 100))
            UserScoreAPI.getRankList()
        }
    }
}


