package yaya.idv.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import yaya.idv.entity.UserScores

object DatabaseFactory {

    fun init() {
        Database.connect(hikari())//跟MySQL Server進行連接
    }

    fun createTable(){
        transaction {
            SchemaUtils.create(UserScores)//建立排行榜UserScores資料表(Table)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "com.mysql.cj.jdbc.Driver"
        config.jdbcUrl = "jdbc:mysql://localhost:3306/mygame"
        config.username = "xxxxxxxx"
        config.password = "xxxxxxxx"
        config.validate()
        return HikariDataSource(config)
    }

}
