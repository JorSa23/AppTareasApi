import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptareasapi.ApiClient
import com.example.apptareasapi.clases.User
import com.example.apptareasapi.login.LoginRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel : ViewModel() {

    var loginState: LoginState = LoginState.Idle
        private set

    fun login(email: String, password: String) {
        loginState = LoginState.Loading
        viewModelScope.launch {
            try {
                val response = ApiClient.authService.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    loginState = LoginState.Success(response.body()?.user, response.body()?.token)
                } else {
                    loginState = LoginState.Error("Error: ${response.message()}")
                }
            } catch (e: HttpException) {
                loginState = LoginState.Error("Http error: ${e.message}")
            } catch (e: IOException) {
                loginState = LoginState.Error("Network error: ${e.message}")
            }
        }
    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(val user: User?, val token: String?) : LoginState()
        data class Error(val message: String) : LoginState()
    }
}
