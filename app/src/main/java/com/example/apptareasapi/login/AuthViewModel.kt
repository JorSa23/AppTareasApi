package com.example.apptareasapi.login
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareasapi.ApiClient
import com.example.apptareasapi.clases.SignupRequest
import com.example.apptareasapi.clases.User
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AuthViewModel : ViewModel() {

    var signupState: SignupState = SignupState.Idle
        private set

    fun signup(name: String, email: String, password: String) {
        signupState = SignupState.Loading
        viewModelScope.launch {
            try {
                val response = ApiClient.authService.signup(SignupRequest(name, email, password))
                if (response.isSuccessful) {
                    signupState = SignupState.Success(response.body()?.user)
                } else {
                    signupState = SignupState.Error("Error: ${response.message()}")
                }
            } catch (e: HttpException) {
                signupState = SignupState.Error("Http error: ${e.message}")
            } catch (e: IOException) {
                signupState = SignupState.Error("Network error: ${e.message}")
            }
        }
    }

    sealed class SignupState {
        object Idle : SignupState()
        object Loading : SignupState()
        data class Success(val user: User?) : SignupState()
        data class Error(val message: String) : SignupState()
    }
}