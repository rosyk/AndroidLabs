package com.example.lab10

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/posts/{postId}")
    suspend fun getPost(@Path("postId") postId: Int): Post

    @POST("/posts")
    suspend fun postPost(@Body data: Post): Post
}