import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import failure.ApiException
import router.ApiRequest
import router.Routes
import router.insurance.CarInsuranceHandler

class CarInsuranceRoutes(
    private val apiContext: ApiContext = ApiContext(),
    private val handler: CarInsuranceHandler = CarInsuranceHandler(apiContext),
) : Routes() {
    override fun invoke(path: String, request: ApiRequest): APIGatewayProxyResponseEvent {
        return when (path) {
            "POST /car-insurance" -> handler::post
            else -> throw ApiException.NotFoundException("Unsupported method and path: $path")
        }
            .invoke(request)
    }
}
