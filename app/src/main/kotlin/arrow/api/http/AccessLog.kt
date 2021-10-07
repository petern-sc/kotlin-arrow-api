package arrow.api.http

import kotlinx.serialization.Serializable

@Serializable
data class AccessLog(
    val uri: String,
    val contentTypeHeader: Map<String, String?>,
    val responseCode: Int,
    val response: String,
    val durationMs: Long,
    val labels: Map<String, String>
)