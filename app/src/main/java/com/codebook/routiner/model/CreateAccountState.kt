package com.codebook.routiner.model

/**
 * @param gender 0 for male and 1 for female.
 **/
data class CreateAccountState(
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var surName: String = "",
    var gender: Int = 0,
    var habitsList: MutableList<Int> = mutableListOf()
)