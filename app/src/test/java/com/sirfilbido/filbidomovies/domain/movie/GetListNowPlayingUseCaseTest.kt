package com.sirfilbido.filbidomovies.domain.movie

import com.sirfilbido.filbidomovies.data.repository.genre.GenreRepository
import com.sirfilbido.filbidomovies.data.repository.movie.MovieRepository
import com.sirfilbido.filbidomovies.domain.genre.GenresFactory
import com.sirfilbido.filbidomovies.domain.interactor.genre.GetGenresUseCase
import com.sirfilbido.filbidomovies.domain.interactor.movie.GetListNowPlayingUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetListNowPlayingUseCaseTest {

    private val repository = mockk<MovieRepository>()
    private val repositoryGenreRepository = mockk<GenreRepository>()
    private val getGenresUseCase = GetGenresUseCase(repositoryGenreRepository)
    private val getListNowPlayingUseCase = GetListNowPlayingUseCase(repository, getGenresUseCase)

    @Test
    fun getListNowPlaying_return_list_with_success() = runBlocking {
        //GIVEN
        coEvery { repositoryGenreRepository.getGenres() } returns flow { emit(GenresFactory.genres) }
        coEvery { repository.getListNowPlaying() } returns flow { emit(NowPlayingFactory.nowPlaying) }

        //WHEN
        val result = getListNowPlayingUseCase().single()

        //THEN
        Assert.assertEquals(result.size, NowPlayingFactory.nowPlaying.size)
    }

    @Test
    fun getListNowPlaying_return_list_with_exception() = runBlocking {
        //GIVEN
        coEvery { repositoryGenreRepository.getGenres() } returns flow { emit(GenresFactory.genres) }
        coEvery { repository.getListNowPlaying() } throws Exception()

        //WHEN
        val result = getListNowPlayingUseCase().single()

        //THEN
        Assert.assertEquals(result.size, 0)
    }
}