package router

import ApiContext
import ApiRequest
import ApiResponse
import failure.ApiException
import plugins.AuthenticationPlugin
import router.insurance.CarInsuranceRoutes
import kotlin.reflect.KProperty0

class Router(
    private val apiContext: ApiContext = ApiContext(),
    private val carInsuranceRoutes: CarInsuranceRoutes = CarInsuranceRoutes(apiContext),
    private val authenticator: AuthenticationPlugin = AuthenticationPlugin(),
) {
    operator fun invoke(request: ApiRequest): ApiResponse {
        authenticator.validate(request)

        val routesMap: Map<String, KProperty0<Route>> =
            mapOf(
                "/v1/car-insurance" to ::carInsuranceRoutes,
            )

        val customPath: String = request.getCustomPath()
        for ((prefix: String, routeHandler: KProperty0<Route>) in routesMap) {
            if (request.path.startsWith(prefix)) {
                return routeHandler.get().invoke(customPath, request)
            }
        }
        throw ApiException.NotFoundException("Unsupported path: $customPath")
    }
}
