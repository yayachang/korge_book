package yaya.idv

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import yaya.idv.api.UserScoreAPI
import yaya.idv.database.DatabaseFactory


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation){
        gson()
    }

    DatabaseFactory.init()//資料庫的初始化
    DatabaseFactory.createTable()//建立Table

    routing {
        UserScoreAPI()
    }
}