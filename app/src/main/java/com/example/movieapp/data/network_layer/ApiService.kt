package com.example.movieapp.data.network_layer

import retrofit2.Call
import retrofit2.http.GET
import com.example.movieapp.data.models.PostDataItem
import retrofit2.Response

interface ApiService {
    @GET("posts")
    suspend fun getAllMovie(): Response<List<PostDataItem>>
}
