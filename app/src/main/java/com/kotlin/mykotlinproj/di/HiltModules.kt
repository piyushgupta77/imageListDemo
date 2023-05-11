package com.kotlin.mykotlinproj.di

import android.content.Context
import com.kotlin.mykotlinproj.Constants
import com.kotlin.mykotlinproj.data.network.CustomOkHttpBuilder
import com.kotlin.mykotlinproj.data.network.NetworkClient
import com.kotlin.mykotlinproj.data.repo.SearchRepository
import com.kotlin.mykotlinproj.data.repo.cache.Cache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {
    @Provides
    fun provideRepository(cache: Cache, networkClient: NetworkClient, @ApplicationContext context: Context): SearchRepository {
        return SearchRepository(cache, networkClient, "AY7KFgxThK1_T9tYEsHU_2oJ0uwNMNUla5UeRR9WxRE", context)
    }

    @Provides
    fun provideCache(): Cache {
        return Cache()
    }

    @Provides
    fun provideNetworkClient(retrofit: Retrofit): NetworkClient {
        return NetworkClient(retrofit)
    }

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return CustomOkHttpBuilder.getOkHttpClient(context)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}