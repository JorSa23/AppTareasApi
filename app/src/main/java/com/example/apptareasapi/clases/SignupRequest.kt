package com.example.apptareasapi.clases

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String
)