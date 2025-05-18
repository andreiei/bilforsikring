package failure

import ApiResponse
import io.netty.handler.codec.http.HttpResponseStatus

class FailureHandler {
    operator fun invoke(throwable: Throwable): ApiResponse {
        val errorResponse: ErrorResponse = handleFailure(throwable)
        throwable.printStackTrace()
        return ApiResponse.from(errorResponse, errorResponse.code)
    }

    private fun handleFailure(failure: Throwable): ErrorResponse {
        return when (failure) {
            is ApiException.BadRequestException, is IllegalArgumentException ->
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

            is ApiException.FailedToCreateCarInsurance ->
                ErrorResponse(
                    error = ErrorCode.FAILED_TO_CREATE_CAR_INSURANCE,
                    description = failure.message,
                    status = HttpResponseStatus.INTERNAL_SERVER_ERROR,
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
