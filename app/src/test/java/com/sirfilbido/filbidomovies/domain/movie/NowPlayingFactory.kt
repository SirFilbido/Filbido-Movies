package com.sirfilbido.filbidomovies.domain.movie

import com.sirfilbido.filbidomovies.data.services.movie.response.MovieResponse
import java.time.LocalDate

object NowPlayingFactory {

    val nowPlaying = listOf(
        MovieResponse(
            id = 637649,
            poster = "/M7SUK85sKjaStg4TKhlAVyGlz3.jpg",
            title = "Wrath of Man",
            releaseDate = LocalDate.parse("2021-04-22"),
            overview = "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
            genres = listOf(28, 80, 53)
        ),
        MovieResponse(
            id = 656663,
            poster = "/ugIdyvtAzHWOguD91UjHKoAvfum.jpg",
            title = "Jackass Forever",
            releaseDate = LocalDate.parse("2022-02-01"),
            overview = "Celebrate the joy of a perfectly executed shot to the groin as Johnny Knoxville, Steve-O and the rest of the gang return alongside some newcomers for one final round of hilarious, wildly absurd and often dangerous displays of stunts and comedy.",
            genres = listOf(14, 12, 35)
        ),
        MovieResponse(
            id = 763285,
            poster = "/zT5ynZ0UR6HFfWQSRf2uKtqCyWD.jpg",
            title = "Ambulance",
            releaseDate = LocalDate.parse("2022-03-16"),
            overview = "Decorated veteran Will Sharp, desperate for money to cover his wife's medical bills, asks for help from his adoptive brother Danny. A charismatic career criminal, Danny instead offers him a score: the biggest bank heist in Los Angeles history: $32 million.",
            genres = listOf(28, 16, 35)
        ),
        MovieResponse(
            id = 637649,
            poster = "/odVv1sqVs0KxBXiA8bhIBlPgalx.jpg",
            title = "Moonfall",
            releaseDate = LocalDate.parse("2022-02-03"),
            overview = "A mysterious force knocks the moon from its orbit around Earth and sends it hurtling on a collision course with life as we know it.",
            genres = listOf(14, 35, 16)
        ),
    )
}