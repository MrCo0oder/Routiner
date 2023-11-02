package com.codebook.routiner.model

sealed class CreateAccountStateUiEvents {
    data class Email(val value: String) : CreateAccountStateUiEvents()
    data class Password(val value: String) : CreateAccountStateUiEvents()
    data class Name(val value: String) : CreateAccountStateUiEvents()
    data class SurName(val value: String) : CreateAccountStateUiEvents()
    data class Gender(val value: Int) : CreateAccountStateUiEvents()
    data class Habits(val value: MutableList<Int>) : CreateAccountStateUiEvents()
}