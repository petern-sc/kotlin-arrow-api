package arrow.api

import arrow.api.getmovie.GetMovieController
import arrow.api.getmovie.GetMovieRepository
import arrow.api.getmovie.GetMovieService
import org.http4k.core.Method
import org.http4k.core.Request

fun main() {
    val getMovieRepository = GetMovieRepository()
    val getMovieService = GetMovieService(
        loadMovie = getMovieRepository::loadMovie
    )
    val getMovieController = GetMovieController(
        getMovie = getMovieService::getMovie
    )

    val appRoutes = AppRoutes(
        handleGetMovie = getMovieController::handleGetMovie
    ).routes

    val response = appRoutes(Request(Method.GET, "/movies/a"))

    println(response)
}
