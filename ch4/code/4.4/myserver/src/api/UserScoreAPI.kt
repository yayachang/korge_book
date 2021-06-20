package yaya.idv.api

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import yaya.idv.model.UserScore
import yaya.idv.service.UserScoreService

fun Route.UserScoreAPI() {

    post("/uploadScore") {//上傳分數API
        var data = Gson().fromJson(call.receive<String>(), UserScore::class.java)
        if (data == null) {
            call.respond(HttpStatusCode.BadRequest, "玩家資料不存在")
        }else {
            UserScoreService().saveScore(data)
            call.respond(HttpStatusCode.OK)
        }
    }

    get("/getRankList") {//取得排行榜API
        call.respond(HttpStatusCode.OK, UserScoreService().getRankList())
    }

}