package router

import ApiRequest
import ApiResponse

interface Route {
    operator fun invoke(path: String, request: ApiRequest): ApiResponse
}
