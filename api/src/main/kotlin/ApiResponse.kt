import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val body: String,
    val code: Int,
) {
    companion object {
        inline fun <reified T> from(body: T, code: Int = 200): ApiResponse =
            ApiResponse(body = encodeToString(body), code = code)
    }
}
