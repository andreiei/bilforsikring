package services

import models.AgreementId
import models.CustomerId
import java.util.logging.Logger

class LetterService {
    private val log: Logger = Logger.getLogger(LetterService::class.java.name)

    fun send(customerId: CustomerId, agreementId: AgreementId, registrationNumber: String) {
        // TODO Send mail
    }
}
