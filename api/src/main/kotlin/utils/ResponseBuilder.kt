package utils

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import encodeToString

class ResponseBuilder {
    companion object {
        inline fun <reified T> respondWithContent(
            body: T,
            statusCode: Int = 200,
        ): APIGatewayProxyResponseEvent {
            return APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(encodeToString(body))
        }
    }
}
