import com.airthings.location.test.api.router.insurance.request.PostCarInsuranceRequest
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import models.AgreementId
import models.CustomerId
import services.CreditService
import services.FagSystem
import services.RegistrationService
import utils.ResponseBuilder
import java.util.logging.Logger.getLogger

class CarInsuranceHandler(
    private val request: APIGatewayProxyRequestEvent,
    private val fagSystem: FagSystem = FagSystem(),
    private val letterService: LetterService = LetterService(),
    private val registrationService: RegistrationService = RegistrationService(),
    private val creditService: CreditService = CreditService(),
) {
    private val log: java.util.logging.Logger = getLogger(CarInsuranceHandler::class.java.name)

    suspend fun post(): APIGatewayProxyResponseEvent {
        val requestBody: PostCarInsuranceRequest = decodeFromString(request.body)
        registrationService.validate(requestBody.registrationNumber)
        creditService.check(requestBody.birthNumber)

        val customerId: CustomerId = fagSystem.createCustomer()
        val agreementId: AgreementId = fagSystem.createAgreement(customerId)

        val status = letterService.send(customerId, agreementId)

        fagSystem.updateStatus(agreementId)

        val response = PostCarInsuranceResponse(
            agreementId = agreementId,
            status = CarInsuranceStatus.ACCEPTED
        )

        return ResponseBuilder.respondWithContent(response)
    }
}
