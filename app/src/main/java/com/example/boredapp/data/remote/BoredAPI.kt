package com.example.boredapp.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BORED_API_URL:String = "https://www.boredapi.com/api/"

interface BoredAPI {
    @GET("activity")
    suspend fun getRandomActivityAsync(): Response<ActivityModel>

    companion object ApiFactory{
        fun create():BoredAPI {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BORED_API_URL)
                .build()
            return retrofit.create(BoredAPI::class.java)
        }

    }
}