import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import failure.ErrorCode
import failure.ErrorResponse
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.netty.handler.codec.http.HttpMethod
import models.NationalId
import router.insurance.request.PostCarInsuranceRequest
import services.FagSystem

class HandlerTest : ShouldSpec() {
    fun Handler.invoke(): APIGatewayProxyResponseEvent = handleRequest(
        context = null,
        event = APIGatewayProxyRequestEvent()
            .withBody(
                encodeToString(
                    PostCarInsuranceRequest(
                        registrationNumber = "123",
                        birthNumber = NationalId("99999999999"),
                        firstName = "Ola",
                        lastName = "Nordman",
                        email = "test@localhost.com",
                        bonus = "20.000",
                    ),
                ),
            )
            .withHttpMethod(HttpMethod.POST.name())
            .withPath("/v1/car-insurance"),
    )

    init {
        should("return a success response") {
            val handler = Handler()
            val response: APIGatewayProxyResponseEvent = handler.invoke()
            decodeFromString<PostCarInsuranceResponse>(response.body).status shouldBe InsuranceStatus.SENT
        }

        should("return a failure response") {
            val handler = Handler(
                apiContext = ApiContext(
                    letterService = mockk {
                        every { send(any(), any(), any()) } throws RuntimeException("email service is down")
                    },
                    fagSystem = FagSystem(),
                ),
            )
            val response: APIGatewayProxyResponseEvent = handler.invoke()

            decodeFromString<ErrorResponse>(response.body).error shouldBe ErrorCode.FAILED_TO_CREATE_CAR_INSURANCE
        }
    }
}
