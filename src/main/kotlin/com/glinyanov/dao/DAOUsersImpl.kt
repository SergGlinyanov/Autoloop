package com.glinyanov.dao

import com.glinyanov.dao.DatabaseFactory.dbQuery
import com.glinyanov.models.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class DAOUsersImpl : DAOUsers {

    private fun resultRowToArticle(row: ResultRow) = User(
        id = row[Users.id],
        userName = row[Users.userName],
        password = row[Users.password],
        salt = row[Users.salt]
    )

    override suspend fun getUserByUsername(userName: String): User? = dbQuery {
        Users
            .select { Users.userName eq userName }
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun insertUser(user: User): Boolean = dbQuery {
        val insertStatement = Users.insert {
            it[userName] = user.userName
            it[password] = user.password
            it[salt] = user.salt
        }
        return@dbQuery !insertStatement.resultedValues.isNullOrEmpty()
    }
}