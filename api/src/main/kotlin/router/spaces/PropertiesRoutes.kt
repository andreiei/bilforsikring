package com.airthings.location.test.api.router.spaces

import com.airthings.location.common.storage.ddb.DDBDefinitionQueries
import com.airthings.location.common.utils.kafka.GenericKafkaProducer
import com.airthings.location.test.api.models.CustomPath
import com.airthings.location.test.api.router.AbstractRoutes
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class PropertiesRoutes(
    private val request: APIGatewayProxyRequestEvent,
    private val kafkaProducer: GenericKafkaProducer,
    private val ddbDefinitionQueries: DDBDefinitionQueries,
    private val handler: PropertiesHandler = PropertiesHandler(request, kafkaProducer, ddbDefinitionQueries),
) : AbstractRoutes() {
    override fun getRoutes(path: CustomPath): APIGatewayProxyResponseEvent {
        return when (path) {
            "POST /space-properties/predefined" -> handler::createPredefinedProperties
            "GET /space-properties" -> handler::getProperties
            else -> throw Exception("Unsupported method and path: $path")
        }
            .call()
    }
}
