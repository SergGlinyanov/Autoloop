package com.glinyanov.models

import org.jetbrains.exposed.sql.Table

@kotlinx.serialization.Serializable
data class User(
    val id: Int = 0,
    val userName: String,
    val password: String,
    val salt: String?
)

object Users : Table() {
    val id = integer("id").autoIncrement()
    val userName = varchar("userName", 128)
    val password = varchar("password", 1024)
    val salt = varchar("salt", 1024).nullable()

    override val primaryKey = PrimaryKey(id)
}
