package com.codebook.routiner.ui.screens.loginWithEmail

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codebook.routiner.utils.Constants.PASSWORD_PATTERN
import java.util.regex.Pattern

class LoginWithEmailViewModel : ViewModel() {
    var uiState = mutableStateOf(LoginState())
    fun onEvent(event: LoginStateUiEvents) {
        when (event) {
            is LoginStateUiEvents.Email -> {
                uiState.value = uiState.value.copy(email = event.email)
            }

            is LoginStateUiEvents.Password -> {
                uiState.value = uiState.value.copy(password = event.password)
            }
        }
    }

    fun validateEmail(): String? {
        return if (uiState.value.email.isEmpty()) {
            null
        } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.toString(), uiState.value.email)) {
            "Please enter a valid email."
        } else {
            null
        }
    }

    private fun isValidEmail() = uiState.value.email.isNotEmpty() && Pattern.matches(
        Patterns.EMAIL_ADDRESS.toString(),
        uiState.value.email
    )

    private fun isValidPassword(): Boolean {
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(uiState.value.password)
        return matcher.matches()
    }

    fun isValidScreen(): Boolean = isValidEmail() && isValidPassword()

    fun validatePassword(): String? {
        return if (uiState.value.password.isEmpty()) {
            null
        } else if (!isValidPassword()) {
            ("Please enter a valid password at least 8 characters.")
        } else {
            (null)
        }
    }

}

data class LoginState(var email: String = "", var password: String = "")

sealed class LoginStateUiEvents {
    data class Email(val email: String) : LoginStateUiEvents()
    data class Password(val password: String) : LoginStateUiEvents()
}