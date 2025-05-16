import com.typesafe.config.ConfigFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.hocon.Hocon

@Serializable
data class ApiConfig(
    val bootstrapServers: String,
    @SerialName("schemaRegistryName")
    val schemaRegistryName: String,
    @SerialName("awsRegion")
    val awsRegion: String,
    @SerialName("spacePropertyRequestTopic")
    val spacePropertyRequestTopic: String,
    @SerialName("definitionsDDBTableName")
    val definitionsDDBTableName: String,
    @SerialName("apiKeyArn")
    val apiKeyArn: String,
) {
    companion object {
        operator fun invoke(): ApiConfig = ConfigFactory.load("application.conf")
            .let { Hocon.decodeFromConfig(serializer(), it) }
    }
}
