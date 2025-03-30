package org.example

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("posts")
    fun createPost(@Body message: Message): Call<Message>
}
