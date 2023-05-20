package com.glinyanov.dto.requests

@kotlinx.serialization.Serializable
data class AuthRequest(
    val userName: String,
    val password: String
)
