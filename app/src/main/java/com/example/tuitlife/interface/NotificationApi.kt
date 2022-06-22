package com.example.tuitlife.`interface`

import com.example.pdpchatapp.notificationModels.PushNotification
import com.example.tuitlife.constains.Constants.Companion.CONTENT_TYPE
import com.example.tuitlife.constains.Constants.Companion.SERVICE_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {
    @Headers("Authorization key=$SERVICE_KEY,Content_type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification:PushNotification
    ): Response<ResponseBody>
}