package com.gilbertthio.restfulreqres.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val id: Int,

    val email: String,

    @Json(name = "first_name")
    val firstName: String,

    @Json(name = "last_name")
    val lastName: String,

    val avatar: String
) : Parcelable