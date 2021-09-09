package arrow.api.getmovie

import arrow.core.Option

class GetMovieService(
    private val loadMovie: suspend (Long) -> Option<Movie>
) {
    suspend fun getMovie(movieId: Long): Option<Movie> {
        return loadMovie(movieId)
    }
}
