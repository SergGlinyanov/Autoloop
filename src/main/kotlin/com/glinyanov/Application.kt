package com.glinyanov

import com.glinyanov.dao.DAOUsersImpl
import com.glinyanov.dao.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.glinyanov.plugins.*
import com.glinyanov.security.hashing.SHA256HashingService
import com.glinyanov.security.token.JwtTokenService
import com.glinyanov.security.token.TokenConfig
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()
    val userDataSource = DAOUsersImpl()
    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        exiresIn = 365L * 1000L * 60L * 24L,
        secret = "JWT_SECRET"
    )
    val hashingService = SHA256HashingService()
    configureSerialization()
    configureMonitoring()
    configureSecurity(tokenConfig)
    configureRouting(
        userDataSource = userDataSource,
        hashingService = hashingService,
        tokenService = tokenService,
        tokenConfig = tokenConfig
    )
}
