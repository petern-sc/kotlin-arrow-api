package arrow.api.fetchmovie

import kotlinx.serialization.Serializable
import org.http4k.core.Body
import org.http4k.format.KotlinxSerialization.auto

@Serializable
data class Movie(
    val id: Long,
    val name: String
)

val movieResponseLens = Body.auto<Movie>().toLens()
