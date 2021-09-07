package arrow.api.fetchmovie

import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK


class FetchMovieController {
    fun getMovie(movieId: Long): Response {
        val movie = Movie(movieId, "Titanic")

        return movieResponseLens.inject(movie, Response(OK))
    }
}
