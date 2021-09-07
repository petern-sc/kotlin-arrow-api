package arrow.api

import arrow.api.fetchmovie.FetchMovieController
import org.http4k.core.Method
import org.http4k.core.Request

fun main() {
    val fetchMovieController = FetchMovieController()
    val appRoutes = AppRoutes(
        fetchMovieController = fetchMovieController
    ).routes

    val response = appRoutes(Request(Method.GET, "/movies/1"))

    println(response)
}
