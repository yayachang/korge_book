package yaya.idv.service

import yaya.idv.model.UserScore
import yaya.idv.repository.UserScoreRepository

class UserScoreService {
    val userScoreRepository = UserScoreRepository()

    suspend fun saveScore(data: UserScore) {//上傳分數
        if(userScoreRepository.get(data.user) == null){//是否曾經建立過玩家分數記錄
            userScoreRepository.add(data)//是，建立玩家分數
        }else{
            userScoreRepository.update(data)//否，已經存在玩家分數，可直接更新分數
        }
    }

    suspend fun getRankList():List<UserScore> {//取得排行榜
        return userScoreRepository.get()
    }

}
