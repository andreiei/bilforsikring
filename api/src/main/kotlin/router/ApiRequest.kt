package router

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import decodeFromString
import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val body: String,
    val path: String,
    val httpMethod: String,
) {
    companion object {
        fun APIGatewayProxyRequestEvent.toRequest(): ApiRequest {
            return ApiRequest(
                body = body,
                path = path,
                httpMethod = httpMethod,
            )
        }
    }

    inline fun <reified T> getBody(): T = decodeFromString<T>(body)

    fun getCustomPath(): String = path.removePrefix("/v1").let { "$httpMethod $it" }
}
