package com.airthings.location.test.api.router

import com.airthings.location.common.storage.ddb.DDBDefinitionQueries
import com.airthings.location.common.utils.kafka.GenericKafkaProducer
import com.airthings.location.test.api.models.CustomPath
import com.airthings.location.test.api.router.spaces.PropertiesRoutes
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.reflect.KProperty0

@ExperimentalSerializationApi
class Router(
    private val request: APIGatewayProxyRequestEvent,
    private val kafkaProducer: GenericKafkaProducer,
    private val ddbDefinitionQueries: DDBDefinitionQueries,
    private val propertiesRoutes: PropertiesRoutes = PropertiesRoutes(request, kafkaProducer, ddbDefinitionQueries),
) {
    fun init(): APIGatewayProxyResponseEvent {
        val customPath: CustomPath = request.getCustomPath()

        val routesMap: Map<String, KProperty0<AbstractRoutes>> =
            mapOf(
                "/v1/space-properties" to ::propertiesRoutes,
            )

        for ((prefix: String, routeHandler: KProperty0<AbstractRoutes>) in routesMap) {
            if (request.path.startsWith(prefix)) {
                return routeHandler.get().getRoutes(customPath)
            }
        }
        throw Exception("Unsupported path: ${request.path}")
    }

    private fun APIGatewayProxyRequestEvent.getCustomPath(): String = path.removePrefix("/v1").let { "$httpMethod $it" }
}
