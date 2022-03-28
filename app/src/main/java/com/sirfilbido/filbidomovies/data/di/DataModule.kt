package com.sirfilbido.filbidomovies.data.di

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.sirfilbido.filbidomovies.data.repository.genre.GenreRepository
import com.sirfilbido.filbidomovies.data.repository.genre.GenreRepositoryImpl
import com.sirfilbido.filbidomovies.data.repository.movie.MovieRepository
import com.sirfilbido.filbidomovies.data.repository.movie.MovieRepositoryImpl
import com.sirfilbido.filbidomovies.data.services.genres.GenresService
import com.sirfilbido.filbidomovies.data.services.movie.MovieService
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "04e284eb8b886be8418c1811e07c57f6"
private const val OK_HTTP = "Ok Http"

fun dataModule() = arrayListOf(
    repositoryModules(),
    serviceModule(),
    networkModule(),
)

private fun repositoryModules() = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<GenreRepository> { GenreRepositoryImpl(get()) }
}

private fun serviceModule() = module {
    single { createService<MovieService>(get(), get()) }
    single { createService<GenresService>(get(), get()) }
}

private fun networkModule() = module {
    single { createOkHttpClient() }
    single { createMoshi() }
}

private fun createMoshi(): Moshi? {
    return Moshi.Builder()
        .add(LocalDateAdapter)
        .add(KotlinJsonAdapterFactory())
        .build()
}

private fun createOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor { Log.i(OK_HTTP, it) }

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

object LocalDateAdapter {
    @RequiresApi(Build.VERSION_CODES.O)
    @FromJson
    fun fromJson(string: String) = LocalDate.parse(string, DateTimeFormatter.ISO_DATE)

    @ToJson
    fun toJson(value: LocalDate) = value.toString()
}