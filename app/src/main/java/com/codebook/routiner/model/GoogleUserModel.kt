package com.codebook.routiner.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoogleUserModel(
    val name: String?,
    val email: String?
) : Parcelable