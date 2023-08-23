package com.example.newsapp.data.api

import com.example.newsapp.data.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("/v2/everything?q=Apple&from=2023-08-21&sortBy=popularity&apiKey=86d8a5f0647945049f00cfb344776a41")
    suspend fun allNews(): Response
}