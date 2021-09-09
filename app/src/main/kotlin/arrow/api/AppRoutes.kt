package arrow.api

import kotlinx.coroutines.runBlocking
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.lens.Path
import org.http4k.lens.long
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes


val requiredId = Path.long().of("id")

class AppRoutes(
    private val handleGetMovie: suspend (Long) -> Response
) {
    val routes: RoutingHttpHandler = ServerFilters.CatchLensFailure.then(
        routes(
            "/ping" bind GET to { _: Request -> Response(OK).body("pong!") },
            "/movies/{id}" bind GET to { request: Request ->
                val id = requiredId(request)
                runBlocking { handleGetMovie(id) }
            }
        )
    )

}
