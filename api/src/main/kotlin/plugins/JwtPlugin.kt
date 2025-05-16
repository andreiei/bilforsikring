import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import failure.ApiException

class JwtPlugin {
    fun validate(request: APIGatewayProxyRequestEvent) {
        val authHeader = request.headers["Authorization"]
            ?: throw ApiException.UnauthorizedException("Missing Authorization header")
        val token = authHeader.removePrefix("Bearer ").trim()
        if (token.isEmpty() || !isValid(token)) {
            throw ApiException.UnauthorizedException("Invalid JWT token")
        }
    }

    private fun isValid(token: String): Boolean {
        return token == "valid-token"
    }
}
