package arrow.api.movie

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Long,
    val name: String
)
