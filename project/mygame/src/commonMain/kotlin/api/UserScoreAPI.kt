package api

import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpClient
import com.soywiz.korio.stream.openAsync
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.UserScore


object UserScoreAPI {

    val serverURL = "http://my_server_ip:8080"

    suspend fun upload(data: UserScore) {//分數上傳API
        val content = Json.encodeToString(data)
        val result = HttpClient().requestAsString(
            method = Http.Method.POST,
            url = "${serverURL}/uploadScore",
            content = content.openAsync()
        )
        if (result.success) {
            println("更新分數成功")
        }
    }

    suspend fun getRankList(): List<UserScore> {//取得排行榜API
        val result = HttpClient().requestAsString(method = Http.Method.GET, url = "${serverURL}/getRankList")
        return if (result.success) {
            println("取得排行榜成功data:${result.content}")
            Json.decodeFromString(result.content)
        } else {
            listOf()
        }
    }


}
