package com.rahman.learningmanagementsystem.client

import com.rahman.learningmanagementsystem.client.dto.ContentListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ContentClient {

    @GET("/native-technical-exam/playlist.json")
    suspend fun getListContent(): Response<List<ContentListResponse>>
}
