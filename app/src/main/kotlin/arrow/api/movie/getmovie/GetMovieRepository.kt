package arrow.api.movie.getmovie

import arrow.api.movie.Movie
import arrow.api.util.toArrowOption
import arrow.core.Option
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.inTransactionUnchecked
import org.jdbi.v3.core.kotlin.mapTo

class GetMovieRepository(
    private val db: Jdbi
) {
    suspend fun loadMovie(movieId: Long): Option<Movie> {
        return db.inTransactionUnchecked { handle ->
            handle.createQuery(selectMovieByIdSql)
                .bind("id", movieId)
                .mapTo<Movie>()
                .findOne()
                .toArrowOption()
        }
    }

    companion object {
        private val selectMovieByIdSql = """
            SELECT 
                id, 
                name 
            FROM movie
            WHERE id = :id
        """.trimIndent()
    }
}
