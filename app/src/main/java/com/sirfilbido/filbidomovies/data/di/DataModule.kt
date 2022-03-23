package com.sirfilbido.filbidomovies.data.di

import android.util.Log
import com.sirfilbido.filbidomovies.data.services.MovieService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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

    single {
        createService<MovieService>(get(), get())
    }
}

private fun createOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor {
        Log.e(OK_HTTP, it)
    }

    interceptor.level = HttpLoggingInterceptor.Level.BASIC

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(CustomInterceptor())
        .build()
}

private inline fun <reified T> createService(
    client: OkHttpClient,
    factory: Moshi,
): T {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .baseUrl(BASE_URL_POSTER)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .build()
        .create(T::class.java)
}

class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}