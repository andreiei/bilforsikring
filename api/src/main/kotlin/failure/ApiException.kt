package failure

object ApiException {
    class BadRequestException(message: String = "Invalid request", throwable: Throwable? = null) :
        Exception(message, throwable)

    class UnauthorizedException(message: String) : Exception(message)
    class NotFoundException(message: String) : Exception(message)
}
