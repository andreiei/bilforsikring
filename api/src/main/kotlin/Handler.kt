package com.airthings.location.test.api


import com.airthings.location.test.api.router.Router
import com.airthings.location.test.api.router.spaces.request.PostPredefinedPropertiesRequest
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.vertx.core.Vertx
import kotlinx.serialization.ExperimentalSerializationApi
import org.slf4j.Logger
import software.amazon.awssdk.http.HttpStatusCode
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import java.util.UUID

@Suppress("Unused")
@OptIn(ExperimentalSerializationApi::class)
class Handler(
    private val secretClient: SecretsManagerClient =
        SecretsManagerClient
            .builder()
            .httpClientBuilder(UrlConnectionHttpClient.builder())
            .region(Region.US_EAST_1)
            .build(),
    private val apiConfig: ApiConfig = ApiConfig(),
    private val apiKey: String = secretClient.getSecretString(apiConfig.apiKeyArn),
    private val ddbClient: DynamoDbAsyncClient = DynamoDbAsyncClient.builder().build(),
    private val vertx: Vertx = Vertx.vertx(),
    private val kafkaProducer: GenericKafkaProducer =
        GenericKafkaProducer(
            topic = apiConfig.spacePropertyRequestTopic,
            producer =
            GenericKafkaProducer.createProducer(
                vertx = vertx,
                awsRegion = apiConfig.awsRegion,
                schemaRegistryName = apiConfig.schemaRegistryName,
                bootstrapServers = apiConfig.bootstrapServers,
            ),
        ),
    private val ddbDefinitionQueries: DDBDefinitionQueries =
        DDBDefinitionQueries(ddbClient, apiConfig.definitionsDDBTableName),
) : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private val log: Logger = logger()

    @OptIn(ExperimentalSerializationApi::class)
    override fun handleRequest(
        event: APIGatewayProxyRequestEvent,
        context: Context?,
    ): APIGatewayProxyResponseEvent {
        if (event.path == "/health") {
            return healthCheck()
        }

        if (event.headers["api-key"] != apiKey) {
            return ResponseBuilder.respondWithJson(401, "Missing authorization key")
        }

        return try {
            Router(request = event, kafkaProducer = kafkaProducer, ddbDefinitionQueries = ddbDefinitionQueries).init()
        } catch (throwable: Throwable) {
            log.error("Ended with error: ${throwable.message}", throwable)
            ResponseBuilder.respondWithJson(500, "Error: ${throwable.message}")
        }
    }

    private fun healthCheck(): APIGatewayProxyResponseEvent {
        log.info("Health was called. Responding with ${HttpStatusCode.OK}.")
        return ResponseBuilder.respondWithJson(HttpStatusCode.OK, "Service is healthy")
    }
}
