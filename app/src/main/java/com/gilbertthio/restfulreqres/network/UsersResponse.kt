package com.gilbertthio.restfulreqres.network

import com.squareup.moshi.Json

data class UsersResponse(
    @Json(name = "total_pages")
    val pages : Int,

    val data : List<Users>
)