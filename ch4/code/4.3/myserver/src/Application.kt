package yaya.idv

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import yaya.idv.database.DatabaseFactory
import yaya.idv.model.UserScore
import yaya.idv.repository.UserScoreRepository

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    DatabaseFactory.init()//資料庫的初始化
    DatabaseFactory.createTable()//建立Table


    routing {
        get("/") {
            testData()
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
    }
}

suspend fun testData(){
    val repository = UserScoreRepository()
    repository.add(UserScore(user="yaya", score=77))//新增
    val insertResult = repository.get()
    for(data in insertResult){
        System.out.println("new data:${data}")
    }
    repository.update(UserScore(user="yaya", score=88))//更新

    val updateResult = repository.get()//查詢
    for(data in updateResult){
        System.out.println("update data:${data}")
    }
}