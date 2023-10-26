package com.codebook.routiner.ui.screens.loginWithEmail

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
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
            "Email can't be empty."
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

    private fun isValidPassword() =
        uiState.value.email.isNotEmpty() && uiState.value.password.length < 8

    fun isValidScreen(): Boolean = isValidEmail() && isValidPassword()

    fun validatePassword(): String? {
        return if (uiState.value.password.isEmpty()) {
            ("Password can't be empty.")
        } else if (uiState.value.password.length < 8) {
            ("Please enter a password at least 8 characters.")
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