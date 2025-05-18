package router.insurance

import ApiContext
import ApiRequest
import ApiResponse
import InsuranceStatus
import PostCarInsuranceResponse
import failure.ApiException
import models.AgreementId
import models.CustomerId
import router.insurance.request.PostCarInsuranceRequest
import services.FagSystem.Companion.FagSystemAgreementStatus
import java.util.logging.Logger

class CarInsuranceHandler(
    private val apiContext: ApiContext = ApiContext(),
) {
    private val log: Logger = Logger.getLogger(CarInsuranceHandler::class.java.name)

    fun post(request: ApiRequest): ApiResponse {
        val requestBody: PostCarInsuranceRequest = request.getBody()
        requestBody.validate()

        return kotlin.runCatching {
            val customerId: CustomerId = apiContext.fagSystem.createCustomer(
                nationalId = requestBody.birthNumber,
                lastName = requestBody.lastName,
                firstName = requestBody.firstName,
                email = requestBody.email,
            )

            val agreementId: AgreementId = apiContext.fagSystem.createAgreement(
                customerId = customerId,
                registrationNumber = requestBody.registrationNumber,
            )

            apiContext.letterService.send(customerId, agreementId, requestBody.registrationNumber)

            val status: FagSystemAgreementStatus = apiContext.fagSystem.updateStatus(agreementId)

            val response = PostCarInsuranceResponse(
                agreementId = agreementId,
                status = status.toResponseEnum(),
            )
            ApiResponse.from(response)
        }.getOrElse {
            log.info("Failed to process car insurance request: ${it.message}")
            throw ApiException.FailedToCreateCarInsurance()
        }
    }

    private fun FagSystemAgreementStatus.toResponseEnum(): InsuranceStatus {
        return when (this) {
            FagSystemAgreementStatus.PENDING -> InsuranceStatus.PENDING
            FagSystemAgreementStatus.SENT -> InsuranceStatus.SENT
        }
    }
}
