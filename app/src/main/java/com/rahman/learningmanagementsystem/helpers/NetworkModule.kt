package com.rahman.learningmanagementsystem.helpers

import com.rahman.learningmanagementsystem.client.ContentClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY  // Log full request and response body

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)  // Add the logging interceptor
            .connectTimeout(30, TimeUnit.SECONDS)  // Optional: Add timeout if needed
            .readTimeout(30, TimeUnit.SECONDS)    // Optional: Add timeout if needed
            .writeTimeout(30, TimeUnit.SECONDS)   // Optional: Add timeout if needed
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://quipper.github.io/native-technical-exam/") // Base URL
            .client(okHttpClient)  // Use OkHttp client with the interceptor
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideContentClient(retrofit: Retrofit): ContentClient {
        return retrofit.create(ContentClient::class.java)
    }
}