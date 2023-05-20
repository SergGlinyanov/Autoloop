package com.glinyanov.plugins

import com.glinyanov.dao.DAOUsers
import com.glinyanov.routes.getSecretInfo
import com.glinyanov.routes.signIn
import com.glinyanov.routes.signUp
import com.glinyanov.security.hashing.HashingService
import com.glinyanov.security.token.JwtTokenService
import com.glinyanov.security.token.TokenConfig
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting(
    userDataSource: DAOUsers,
    hashingService: HashingService,
    tokenService: JwtTokenService,
    tokenConfig: TokenConfig
) {

    routing {
        signUp(hashingService = hashingService, userDataSource = userDataSource)
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        getSecretInfo()
    }
}
