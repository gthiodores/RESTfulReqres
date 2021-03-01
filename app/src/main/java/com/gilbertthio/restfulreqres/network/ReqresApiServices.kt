package com.gilbertthio.restfulreqres.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Base url for the API
private const val BASE_URL = "https://reqres.in/api/"

/**
 * Parse the JSON received from the server
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * A HTTP Client to connect and interact with the server
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * List of functions for retrofit to generate
 */
interface ReqresApiServices {
    @GET("users")
    suspend fun getUsers(@Query("page")page: Int = 1) : UsersResponse
}

/**
 * Lazily initialize [ReqresApiServices] using [retrofit]
 */
object ReqresApi {
    val reqresApiServices : ReqresApiServices by lazy {
        retrofit.create(ReqresApiServices::class.java)
    }
}