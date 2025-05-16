package failure

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.netty.handler.codec.http.HttpResponseStatus
import utils.ResponseBuilder

object FailureHandler {
    fun handle(throwable: Throwable): APIGatewayProxyResponseEvent {
        val errorResponse: ErrorResponse = handleFailure(throwable)
        return ResponseBuilder.respondWithContent(errorResponse, errorResponse.code)
    }

    private fun handleFailure(failure: Throwable): ErrorResponse {
        return when (failure) {
            is ApiException.BadRequestException ->
                ErrorResponse(
                    error = ErrorCode.BAD_REQUEST,
                    description = failure.message,
                    status = HttpResponseStatus.BAD_REQUEST,
                )

            is ApiException.UnauthorizedException ->
                ErrorResponse(
                    error = ErrorCode.UNAUTHORIZED,
                    description = failure.message,
                    status = HttpResponseStatus.UNAUTHORIZED,
                )

            is ApiException.NotFoundException ->
                ErrorResponse(
                    error = ErrorCode.NOT_FOUND,
                    description = failure.message,
                    status = HttpResponseStatus.NOT_FOUND,
                )

            else ->
                ErrorResponse(
                    error = ErrorCode.INTERNAL_SERVER_ERROR,
                    description = failure.message,
                    status = HttpResponseStatus.INTERNAL_SERVER_ERROR,
                )
        }
    }
}
