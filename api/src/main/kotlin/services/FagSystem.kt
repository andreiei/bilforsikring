package services

import models.AgreementId
import models.CustomerId

class FagSystem {
    suspend fun createCustomer(): CustomerId {

    }

    suspend fun createAgreement(customerId: CustomerId): AgreementId {

    }

    suspend fun updateStatus(agreementId: AgreementId) {

    }
}

/*
@Serializable
data class PostCarInsuranceRequest(
    val registrationNumber: String,
    val birthNumber: String,
    val firstName: String,
    val lastName: String,
    val email: String,
)
 */
