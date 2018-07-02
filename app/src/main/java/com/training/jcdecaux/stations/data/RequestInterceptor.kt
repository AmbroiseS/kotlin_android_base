package com.training.jcdecaux.stations.data.jcdecaux

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val url = request.url().newBuilder()
                .addQueryParameter("apiKey", apiKey)
                .addQueryParameter("format", "json")
                .build()

        val newRequest = request.newBuilder()
                .url(url)
                .build()

        return chain.proceed(newRequest)
    }

}