import com.airthings.location.test.api.models.CustomPath
import com.airthings.location.test.api.router.AbstractRoutes
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import failure.ApiException
import kotlinx.serialization.ExperimentalSerializationApi

class CarInsuranceRoutes(
    private val request: APIGatewayProxyRequestEvent,
    private val handler: CarInsuranceHandler = CarInsuranceHandler(request),
) : AbstractRoutes() {
    override fun getRoutes(path: CustomPath): APIGatewayProxyResponseEvent {
        return when (path) {
            "POST /car-insurance" -> handler::post
            else -> throw ApiException.NotFoundException("Unsupported method and path: $path")
        }
            .call()
    }
}
