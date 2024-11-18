package com.rahman.learningmanagementsystem.service

import com.rahman.learningmanagementsystem.client.ContentClient
import com.rahman.learningmanagementsystem.client.dto.ContentListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class ContentService @Inject constructor(
    private val client: ContentClient,
    private val dispatcher: CoroutineDispatcher
) {
    private fun getDispatcher() = dispatcher

    suspend fun getListContent(): Response<List<ContentListResponse>> {
        return withContext(dispatcher) {
            client.getListContent()
        }
    }
}