package com.codebook.routiner.utils

object Constants {
    const val PASSWORD_PATTERN = "^" +
            "(?=.*[0-9])" +         // at least 1 digit
            "(?=.*[a-z])" +         // at least 1 lower case letter
            "(?=.*[A-Z])" +         // at least 1 upper case letter
            "(?=.*[@#$%^&+=])" +    // at least 1 special character
            "(?=\\S+$)" +           // no white spaces
            ".{8,}" +               // at least 4 characters
            "$"
}