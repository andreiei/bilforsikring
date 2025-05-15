package com.airthings.location.test.api.router

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent

abstract class AbstractRoutes {
    abstract fun getRoutes(path: String): APIGatewayProxyResponseEvent
}
