package model

import kotlinx.serialization.Serializable

@Serializable
data class UserScore(var id: Long? = null, var user: String, var score: Int)