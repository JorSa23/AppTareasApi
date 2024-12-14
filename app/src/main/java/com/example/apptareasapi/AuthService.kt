package com.example.apptareasapi

import com.example.apptareasapi.clases.AuthResponse
import com.example.apptareasapi.clases.SignupRequest
import com.example.apptareasapi.login.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("signup")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<AuthResponse>

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
}
