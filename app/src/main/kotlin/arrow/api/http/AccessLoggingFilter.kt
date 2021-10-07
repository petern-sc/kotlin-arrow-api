package arrow.api.http

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import org.http4k.core.HttpTransaction
import org.http4k.filter.ResponseFilters

object AccessLoggingFilter {
    private val logger = KotlinLogging.logger {}
    private val jsonFormat = Json { prettyPrint = true }

    val accessLogging = ResponseFilters.ReportHttpTransaction { tx: HttpTransaction ->
        logger.info { jsonFormat.encodeToString(tx.toAccessLog()) }
    }
}

fun HttpTransaction.toAccessLog(): AccessLog =
    AccessLog(
        uri = this.request.uri.toString(),
        contentTypeHeader = this.request.headers.toMap(),
        responseCode = this.response.status.code,
        response = this.response.bodyString(),
        durationMs = this.duration.toMillis(),
        labels = this.labels
    )