package arrow.api

import arrow.api.getmovie.GetMovieController
import arrow.api.getmovie.GetMovieRepository
import arrow.api.getmovie.GetMovieService
import arrow.api.http.AccessLoggingFilter
import mu.KotlinLogging
import org.http4k.core.then
import org.http4k.routing.RoutingHttpHandler
import org.http4k.server.Jetty
import org.http4k.server.asServer

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

    val accessLogging = AccessLoggingFilter.accessLogging

    val routes = accessLogging.then(appRoutes)

    startServer(port = 8081, appRoutes = routes)
}

fun startServer(
    port: Int,
    appRoutes: RoutingHttpHandler
) {
    val logger = KotlinLogging.logger {}

    logger.info { "Starting app on port: $port" }
    appRoutes.asServer(Jetty(port)).start()
}