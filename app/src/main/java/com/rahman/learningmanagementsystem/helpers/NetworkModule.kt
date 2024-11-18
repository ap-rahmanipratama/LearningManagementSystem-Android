package com.rahman.learningmanagementsystem.helpers

import com.rahman.learningmanagementsystem.client.ContentClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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

        val errorHandlingInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())

            // Check if the response is not successful (non-2xx status code)
            if (!response.isSuccessful) {
                val errorMessage = "Server Error: ${response.code}"
                // You can also extract error details from the response body if needed
                throw ServerFailedException(errorMessage) // Throw a custom exception or handle the error
            }

            return@Interceptor response
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(errorHandlingInterceptor)
            .connectTimeout(4, TimeUnit.SECONDS)
            .readTimeout(4, TimeUnit.SECONDS)
            .writeTimeout(4, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://quipper.github.io/native-technical-exam/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideContentClient(retrofit: Retrofit): ContentClient {
        return retrofit.create(ContentClient::class.java)
    }
}