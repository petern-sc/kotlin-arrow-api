package arrow.api.getmovie

import arrow.core.None
import arrow.core.Some
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import kotlinx.serialization.json.Json
import org.http4k.core.HttpMessage
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.haveBody
import org.http4k.kotest.haveStatus
import org.http4k.kotest.shouldHaveStatus

class GetMovieControllerTest : StringSpec({
    "should return movie response" {
        val movie = Movie(id = 1, name = "Titanic")
        val controller = GetMovieController(getMovie = { Some(movie) })

        val response = controller.handleGetMovie(1)

        response should haveStatus(OK)
        response.shouldHaveJsonBody(
            """
            {
                "id": 1,
                "name": "Titanic"
            }
            """
        )
    }

    "should return not found" {
        val controller = GetMovieController(getMovie = { None })

        val response = controller.handleGetMovie(1)

        response shouldHaveStatus NOT_FOUND
    }
})

fun HttpMessage.shouldHaveJsonBody(string: String) {
    val json = Json.parseToJsonElement(string)
    this should haveBody(json.toString())
}