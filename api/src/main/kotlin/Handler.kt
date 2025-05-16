import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import failure.FailureHandler
import kotlinx.serialization.ExperimentalSerializationApi
import java.util.logging.Logger

@Suppress("Unused")
class Handler(
    private val apiConfig: ApiConfig = ApiConfig(),
    private val failureHandler: FailureHandler = FailureHandler,
) : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private val log: Logger = Logger.getLogger(Handler::class.java.name)

    override fun handleRequest(
        event: APIGatewayProxyRequestEvent,
        context: Context?,
    ): APIGatewayProxyResponseEvent {
        return try {
            Router(request = event).init()
        } catch (throwable: Throwable) {
            return failureHandler.handle(throwable)
        }
    }
}
