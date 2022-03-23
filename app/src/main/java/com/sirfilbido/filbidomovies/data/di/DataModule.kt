package com.sirfilbido.filbidomovies.data.di

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "04e284eb8b886be8418c1811e07c57f6"
private const val BASE_URL_POSTER = "https://image.tmdb.org/t/p/original/"
private const val OK_HTTP = "Ok Http"

fun dataModule() = arrayListOf(
    networkModule()
)

private fun networkModule() = module {
    single {
        createOkHttpClient()
    }

    single {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}

private fun createOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor {
        Log.e(OK_HTTP, it)
    }

    interceptor.level = HttpLoggingInterceptor.Level.BASIC

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

private inline fun <reified T> createService(
    client: OkHttpClient,
    factory: Moshi,
): T {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .build()
        .create(T::class.java)
}