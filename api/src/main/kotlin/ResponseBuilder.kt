package com.airthings.location.test.api

import com.airthings.location.common.utils.encodeToString
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent

class ResponseBuilder {
    companion object {
        inline fun <reified T> respondWithJson(
            statusCode: Int = 200,
            body: T,
        ): APIGatewayProxyResponseEvent {
            return APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(encodeToString(body))
        }

        fun respond(): APIGatewayProxyResponseEvent {
            return APIGatewayProxyResponseEvent().withStatusCode(204)
        }
    }
}
