package com.sirfilbido.filbidomovies.domain.genre

import com.sirfilbido.filbidomovies.data.repository.genre.GenreRepository
import com.sirfilbido.filbidomovies.domain.interactor.genre.GetGenresUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetGenresUseCaseTest {

    private val repository = mockk<GenreRepository>()
    private val getGenresUseCase = GetGenresUseCase(repository)

    @Test
    fun getGenres_return_list_with_success() = runBlocking {
        //GIVEN
        coEvery { repository.getGenres() } returns flow { emit(GenresFactory.genres) }

        //WHEN
        val result = getGenresUseCase().single()

        //THEN
        Assert.assertEquals(result.size, GenresFactory.genres.size)
    }

    @Test
    fun getGenres_return_list_with_exception() = runBlocking {
        //GIVEN
        coEvery { repository.getGenres() } throws Exception()

        //WHEN
        val result = getGenresUseCase().single()

        //THEN
        Assert.assertEquals(result.size, 0)
    }
}