package com.rapiddeploy.mobile.cartracktechtask.api.support

import com.rapiddeploy.mobile.cartracktechtask.ui.search.OmdbResponseCache
import com.rapiddeploy.mobile.cartracktechtask.ui.search.Repository
import com.rapiddeploy.mobile.cartracktechtask.ui.search.TitlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val API_KEY = "7b37fbec"
private const val BASE_URL = "https://www.omdbapi.com"

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                val url = request.url
                    .newBuilder()
                    .addQueryParameter("apiKey", API_KEY).build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }.build()

    @Singleton
    @Provides
    fun provideRetroFit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiInterface: ApiInterface, omdbResponseCache: OmdbResponseCache) =
        Repository(apiInterface, omdbResponseCache) as TitlesRepository
}