package arrow.api.getmovie

import arrow.api.data.DatabaseConfig
import arrow.core.None
import arrow.core.Some
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GetMovieRepositoryTest : StringSpec({
    "should return movie from database" {
        val repository = GetMovieRepository(DatabaseConfig.jdbi)

        // No compiler check on assertion
        // repository.loadMovie(1) shouldBe Movie(1, "Titanic")
        repository.loadMovie(1) shouldBe Some(Movie(1, "Titanic"))
    }

    "should return none when movie does not exist" {
        val repository = GetMovieRepository(DatabaseConfig.jdbi)

        repository.loadMovie(-1) shouldBe None
    }
})