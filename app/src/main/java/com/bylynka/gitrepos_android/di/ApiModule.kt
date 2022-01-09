package com.bylynka.gitrepos_android.di

import com.bylynka.gitrepos_android.BuildConfig
import com.bylynka.gitrepos_android.api.IProjectsApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClientBuilder: OkHttpClient.Builder): Retrofit.Builder {
        val moshi = Moshi.Builder().build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Singleton
    @Provides
    fun provideReposApiService(retrofit: Retrofit.Builder): IProjectsApi {
        return retrofit
            .build()
            .create(IProjectsApi::class.java)
    }
}