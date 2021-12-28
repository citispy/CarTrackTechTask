package com.rapiddeploy.mobile.cartracktechtask.api.support

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "7b37fbec"
private const val BASE_URL = "https://www.omdbapi.com"

object ApiService {

    val apiInterface: ApiInterface = getRetrofit().create(ApiInterface::class.java)

    private fun getClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor{chain ->
                var request = chain.request()
                val url = request.url
                    .newBuilder()
                    .addQueryParameter("apiKey", API_KEY).build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }.build()
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}