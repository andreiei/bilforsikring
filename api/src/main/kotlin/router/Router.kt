import com.airthings.location.test.api.models.CustomPath
import com.airthings.location.test.api.router.AbstractRoutes
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.reflect.KProperty0

class Router(
    private val request: APIGatewayProxyRequestEvent,
    private val carInsuranceRoutes: CarInsuranceRoutes = CarInsuranceRoutes(request),
    private val authenticator: JwtPlugin = JwtPlugin()
) {
    fun init(): APIGatewayProxyResponseEvent {
        authenticator.validate(request)

        val customPath: CustomPath = request.getCustomPath()
        val routesMap: Map<String, KProperty0<AbstractRoutes>> =
            mapOf(
                "/v1/car-insurance" to ::carInsuranceRoutes,
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
