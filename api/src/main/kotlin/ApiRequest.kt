import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val body: String,
    val path: String,
    val httpMethod: String,
) {
    inline fun <reified T> getBody(): T = decodeFromString<T>(body)

    fun getCustomPath(): String = path.removePrefix("/v1").let { "$httpMethod $it" }

    companion object {
        fun APIGatewayProxyRequestEvent.toApiRequest(): ApiRequest {
            return ApiRequest(
                body = body,
                path = path,
                httpMethod = httpMethod,
            )
        }
    }
}
