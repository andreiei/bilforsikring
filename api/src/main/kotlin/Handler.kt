import ApiRequest.Companion.toApiRequest
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import failure.FailureHandler
import router.Router

class Handler(
    private val apiContext: ApiContext = ApiContext(),
    private val router: Router = Router(apiContext),
    private val failureHandler: FailureHandler = FailureHandler(),
) : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    override fun handleRequest(
        event: APIGatewayProxyRequestEvent,
        context: Context?,
    ): APIGatewayProxyResponseEvent {
        val response: ApiResponse = try {
            router(event.toApiRequest())
        } catch (throwable: Throwable) {
            failureHandler(throwable)
        }
        return respondWithContent(response.body, response.code)
    }

    private fun respondWithContent(
        body: String,
        statusCode: Int = 200,
    ): APIGatewayProxyResponseEvent = APIGatewayProxyResponseEvent()
        .withStatusCode(statusCode)
        .withBody(body)
}
