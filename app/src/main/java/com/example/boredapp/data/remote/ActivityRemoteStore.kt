package com.example.boredapp.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.Exception

class ActivityRemoteStore(private val api : BoredAPI) {

    suspend fun getRandomActivity(): Response<ActivityModel> {
        return try {
            api.getRandomActivityAsync().await()
        } catch (e: Exception) {
            Response.error(999, ResponseBody.create(null, ""))
        }
    }

}