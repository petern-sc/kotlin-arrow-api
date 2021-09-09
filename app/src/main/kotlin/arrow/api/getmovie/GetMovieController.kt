package arrow.api.getmovie

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import org.http4k.core.Body
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.format.KotlinxSerialization.auto

class GetMovieController(
    private val getMovie: suspend (Long) -> Option<Movie>
) {
    suspend fun handleGetMovie(movieId: Long): Response {
        val maybeMovie = getMovie(movieId)
        return maybeMovie.toResponse()
    }

}

private val movieResponseLens = Body.auto<Movie>().toLens()

fun Movie.toResponse(): Response = Response(Status.OK).with(movieResponseLens of this)
fun Option<Movie>.toResponse(): Response = when (this) {
    is Some -> this.value.toResponse()
    is None -> Response(Status.NOT_FOUND)
}
