package arrow.api.getmovie

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import kotlin.random.Random

class GetMovieRepository {

    suspend fun loadMovie(movieId: Long): Option<Movie> = run {
        if (randomness()) {
            Some(Movie(movieId, "Titanic"))
        } else {
            None
        }
    }

    private suspend fun randomness(): Boolean = Random.nextInt() % 2 == 0
}
