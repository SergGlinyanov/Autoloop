package com.glinyanov.security.token

import com.glinyanov.security.token.TokenClaim
import com.glinyanov.security.token.TokenConfig

interface TokenService {
    fun generate(
        config: TokenConfig,
        vararg claims: TokenClaim
    ): String
}