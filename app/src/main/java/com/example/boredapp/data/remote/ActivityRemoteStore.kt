package com.example.boredapp.data.remote

import com.example.boredapp.data.base.BaseRemoteStore

class ActivityRemoteStore(private val api : BoredAPI) : BaseRemoteStore() {

    suspend fun getRandomActivity(): ActivityModel? {
        return safeApiCall(
            call = { api.getRandomActivity().await() },
            errorMessage = "Error getting random activity"
        )
    }

}