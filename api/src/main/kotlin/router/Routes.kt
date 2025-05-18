package router

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent

abstract class Routes {
    abstract operator fun invoke(path: String, request: ApiRequest): APIGatewayProxyResponseEvent
}
