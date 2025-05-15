package com.airthings.location.test.api.router.spaces

import ch.qos.logback.classic.Logger
import com.airthings.location.common.models.entity.PropertyDefinitionId
import com.airthings.location.common.models.entity.UserGroupId
import com.airthings.location.common.models.entity.UserGroupId.Companion.toUserGroupId
import com.airthings.location.common.models.enums.PropertyEventType
import com.airthings.location.common.models.events.CreateGroupPropertiesRequestEvent
import com.airthings.location.common.storage.ddb.DDBDefinitionQueries
import com.airthings.location.common.storage.ddb.models.DDBPropertyDefinition
import com.airthings.location.common.utils.decodeFromString
import com.airthings.location.common.utils.kafka.GenericKafkaProducer
import com.airthings.location.common.utils.logger
import com.airthings.location.test.api.ResponseBuilder
import com.airthings.location.test.api.router.spaces.request.PostPredefinedPropertiesRequest
import com.airthings.location.test.api.router.spaces.response.GetPredefinedPropertiesResponse
import com.airthings.location.test.api.router.spaces.response.PostPredefinedPropertiesResponse
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import java.util.UUID

@ExperimentalSerializationApi
class PropertiesHandler(
    private val request: APIGatewayProxyRequestEvent,
    private val producer: GenericKafkaProducer,
    private val ddbQueries: DDBDefinitionQueries,
) {
    private val log: Logger = logger()

    fun getProperties(): APIGatewayProxyResponseEvent =
        runBlocking {
            val userGroupId: UserGroupId = request.queryStringParameters["userGroupId"]?.toUserGroupId()
                ?: throw Exception("SerialNumber is required")
            val definitions: List<DDBPropertyDefinition> = ddbQueries.query(userGroupId)

            val response = GetPredefinedPropertiesResponse(definitions)
            ResponseBuilder.respondWithJson(200, response)
        }

    fun createPredefinedProperties(): APIGatewayProxyResponseEvent =
        runBlocking {
            val requestBody: PostPredefinedPropertiesRequest = decodeFromString(request.body)
            val userGroupId: UserGroupId = requestBody.userGroupId

            val propertyDefinitionId = PropertyDefinitionId(UUID.randomUUID().toString())

            val payload = CreateGroupPropertiesRequestEvent(
                eventType = PropertyEventType.CREATE_GROUP_DEFINITIONS.name,
                userGroupId = userGroupId,
            )
            log.info("Creating predefined properties for userGroupId: $userGroupId")

            producer.sendMessage<CreateGroupPropertiesRequestEvent>(userGroupId.toString(), payload)

            val response = PostPredefinedPropertiesResponse(propertyDefinitionId)
            ResponseBuilder.respondWithJson(200, response)
        }
}
