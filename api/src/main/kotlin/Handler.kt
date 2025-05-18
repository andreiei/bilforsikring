import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import failure.FailureHandler
import router.ApiRequest.Companion.toRequest
import java.util.logging.Logger

@Suppress("Unused")
class Handler(
    private val failureHandler: FailureHandler = FailureHandler(),
    private val apiContext: ApiContext = ApiContext(),
    private val router: Router = Router(apiContext),
) : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private val log: Logger = Logger.getLogger(Handler::class.java.name)

    override fun handleRequest(
        event: APIGatewayProxyRequestEvent,
        context: Context?,
    ): APIGatewayProxyResponseEvent {
        return try {
            log.info("HERE")
            router(event.toRequest())
        } catch (throwable: Throwable) {
            return failureHandler(throwable)
        }
    }
}
