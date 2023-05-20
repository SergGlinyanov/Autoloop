package com.glinyanov.dao

import com.glinyanov.models.User

interface DAOUsers {
    suspend fun getUserByUsername(userName: String): User?
    suspend fun insertUser(user: User): Boolean

}