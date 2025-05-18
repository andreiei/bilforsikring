package failure

object ApiException {
    class BadRequestException(message: String = "Bad request") : Exception(message)
    class UnauthorizedException(message: String) : Exception(message)
    class NotFoundException(message: String) : Exception(message)
    class FailedToCreateCarInsurance : Exception("Failed to create car insurance agreement")
}
