import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import failure.ApiException
import router.ApiRequest
import router.Routes
import kotlin.reflect.KProperty0

class Router(
    private val apiContext: ApiContext = ApiContext(),
    private val carInsuranceRoutes: CarInsuranceRoutes = CarInsuranceRoutes(apiContext),
    private val authenticator: AuthPlugin = AuthPlugin(),
) {
    operator fun invoke(request: ApiRequest): APIGatewayProxyResponseEvent {
        authenticator.validate(request)

        val customPath: String = request.getCustomPath()
        val routesMap: Map<String, KProperty0<Routes>> =
            mapOf(
                "/v1/car-insurance" to ::carInsuranceRoutes,
            )

        for ((prefix: String, routeHandler: KProperty0<Routes>) in routesMap) {
            if (request.path.startsWith(prefix)) {
                return routeHandler.get().invoke(customPath, request)
            }
        }
        throw ApiException.NotFoundException("Unsupported path: ${request.path}")
    }
}
