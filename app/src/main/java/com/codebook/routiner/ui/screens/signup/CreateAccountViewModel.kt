package com.codebook.routiner.ui.screens.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.codebook.routiner.model.CreateAccountState
import com.codebook.routiner.model.CreateAccountStateUiEvents
import com.codebook.routiner.model.Habit
import com.codebook.routiner.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

class CreateAccountViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<CreateAccountState> =
        MutableStateFlow(CreateAccountState())
    var uiState: StateFlow<CreateAccountState> = _uiState
    var habitsList = mutableSetOf(
        Habit(0, "Drinking Water", "💧"),
        Habit(1, "Coding", "👨🏻‍💻"),
        Habit(2, "Gaming", "🎮"),
        Habit(3, "Reading", "📖"),
        Habit(4, "Sleeping", "💤"),
        Habit(5, "Study", "\uD83D\uDCD5"),
        Habit(6, "Water plant", "\uD83C\uDF3F\u200D"),
        Habit(7, "Meditate", "\uD83E\uDDD8\uD83C\uDFFB\u200D♀️"),
        Habit(8, "Cooking", "🍳"),
        Habit(9, "Shopping", "🛒"),
        Habit(10, "Music", "🎧"),
        Habit(11, "Body Building", "💪🏻"),
        Habit(12, "Smoking", "🚬🚭"),
        Habit(13, "Football", "⚽"),
        Habit(14, "Swimming", "🏊🏻"),
    )

    fun onEvent(events: CreateAccountStateUiEvents) {
        when (events) {
            is CreateAccountStateUiEvents.Email -> {
                _uiState.value = uiState.value.copy(email = events.value)
            }

            is CreateAccountStateUiEvents.Password -> {
                _uiState.value = uiState.value.copy(password = events.value)
            }

            is CreateAccountStateUiEvents.Name -> {
                _uiState.value = uiState.value.copy(name = events.value)
            }

            is CreateAccountStateUiEvents.SurName -> {
                _uiState.value = uiState.value.copy(surName = events.value)
            }

            is CreateAccountStateUiEvents.Gender -> {
                _uiState.value = uiState.value.copy(gender = events.value)
            }

            is CreateAccountStateUiEvents.Habits -> {
                _uiState.value = uiState.value.copy(habitsList = events.value)
            }
        }
    }

    fun validateEmail(): String? {
        return if (_uiState.value.email.isEmpty()) {
            null
        } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.toString(), _uiState.value.email)) {
            "Please enter a valid email."
        } else {
            null
        }
    }

    private fun isValidEmail() = _uiState.value.email.isNotEmpty() && Pattern.matches(
        Patterns.EMAIL_ADDRESS.toString(),
        _uiState.value.email
    )

    fun isBasicInfoScreenValid(): Boolean =
        isValidEmail() && isValidPassword() && isValidName(_uiState.value.name) && isValidName(
            _uiState.value.surName
        )

    private fun isValidName(name: String): Boolean = name.isNotEmpty() && name.length >= 3

    fun validateName(name: String): String? {
        return if (name.isEmpty()) {
            null
        } else if (name.length < 3) {
            ("Please enter a valid name at least 3 characters.")
        } else {
            null
        }
    }

    fun validatePassword(): String? {
        return if (_uiState.value.password.isEmpty()) {
            null
        } else if (!isValidPassword()) {
            ("Please enter a valid password at least 8 characters.")
        } else {
            null
        }
    }

    private fun isValidPassword(): Boolean {
        val pattern = Pattern.compile(Constants.PASSWORD_PATTERN)
        val matcher = pattern.matcher(_uiState.value.password)
        return matcher.matches()
    }

    fun isGenderScreenValid(): Boolean = _uiState.value.gender != -1

}