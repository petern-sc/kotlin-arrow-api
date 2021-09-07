package arrow.api

import arrow.api.fetchmovie.FetchMovieController
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes


class AppRoutes(
    private val fetchMovieController: FetchMovieController
) {
    val routes = routes(
        "/ping" bind GET to { _: Request -> Response(OK).body("pong!") },
        "/movies/{id}" bind GET to {
            val id = it.path("id")?.toLong() ?: 0
            fetchMovieController.getMovie(id)
        }
    )
}
