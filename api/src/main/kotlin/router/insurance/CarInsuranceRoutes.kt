package router.insurance

import ApiContext
import ApiRequest
import ApiResponse
import failure.ApiException
import router.Route

class CarInsuranceRoutes(
    private val apiContext: ApiContext = ApiContext(),
    private val handler: CarInsuranceHandler = CarInsuranceHandler(apiContext),
) : Route {
    override fun invoke(path: String, request: ApiRequest): ApiResponse {
        return when (path) {
            "POST /car-insurance" -> handler::post
            else -> throw ApiException.NotFoundException("Unsupported method and path: $path")
        }
            .invoke(request)
    }
}
