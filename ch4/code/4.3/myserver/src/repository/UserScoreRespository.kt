package yaya.idv.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import yaya.idv.entity.UserScores
import yaya.idv.model.UserScore

class UserScoreRepository {
    suspend fun add(data: UserScore) {
        transaction {
            UserScores.insert {
                it[user] = data.user
                it[score] = data.score
                it[updateTime] = System.currentTimeMillis()
            }
        }
    }

    suspend fun update(data: UserScore) {
        transaction {
            UserScores.update({ (UserScores.user eq data.user) and (UserScores.score less data.score) }) {
                it[score] = data.score
                it[updateTime] = System.currentTimeMillis()
            }
        }
    }


    suspend fun get(): List<UserScore> {
        val data = withContext(Dispatchers.IO) {
            transaction {
                UserScores.selectAll().orderBy(UserScores.score, SortOrder.DESC).mapNotNull {
                    toUSerScore(it)
                }.toMutableList()

            }
        }
        return data
    }

    private fun toUSerScore(row: ResultRow): UserScore =
        UserScore(
            id = row[UserScores.id].value,
            user = row[UserScores.user],
            score = row[UserScores.score]
        )

}
