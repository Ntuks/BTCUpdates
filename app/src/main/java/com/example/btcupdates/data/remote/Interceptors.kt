package com.example.btcupdates.data.remote

import com.example.btcupdates.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class HeadersInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val newRequest: Request = request.newBuilder()
            .addHeader(HEADER_API_KEY, BuildConfig.FIXER_API_KEY)
            .build()
        return chain.proceed(newRequest)
    }

}

const val HEADER_API_KEY = "apiKey"

 val loggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}