package yaya.idv.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import yaya.idv.entity.UserScores

object DatabaseFactory {

    fun init() {
        Database.connect(hikari())
    }

    fun createTable(){
        transaction {
            SchemaUtils.create(UserScores)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "com.mysql.cj.jdbc.Driver"
        config.jdbcUrl = "jdbc:mysql://localhost:3306/mygame"
        config.username = "ktor"
        config.password = "新密碼"
        config.validate()
        return HikariDataSource(config)
    }

}
