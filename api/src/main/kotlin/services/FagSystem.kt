package services

import models.AgreementId
import models.CustomerId
import models.NationalId
import java.util.logging.Logger

class FagSystem {
    private val log: Logger = Logger.getLogger(FagSystem::class.java.name)

    /**
     * Create customer with a unique UUID
     */
    fun createCustomer(
        nationalId: NationalId,
        firstName: String,
        lastName: String,
        email: String,
    ): CustomerId {
        return kotlin.runCatching {
            CustomerId("86f3ad89-e26c-4897-bb9c-ebd02e32d3b2")
        }
            .getOrElse {
                log.info("Error creating customer with message: ${it.message}")
                throw it
            }
    }

    /**
     * Create a customer agreement with initial status set to Pending
     */
    fun createAgreement(customerId: CustomerId, registrationNumber: String): AgreementId {
        return kotlin.runCatching {
            AgreementId("701ab7d3-e3fb-42ad-80c4-5cd6ca1d6b38")
        }
            .getOrElse {
                log.info("Error creating agreement for $customerId with message: ${it.message}")
                throw it
            }
    }

    /**
     * Update the status on the agreement id to SENT
     */
    fun updateStatus(agreementId: AgreementId): FagSystemAgreementStatus {
        return kotlin.runCatching {
            FagSystemAgreementStatus.SENT
        }
            .getOrElse {
                log.info("Error updating status for agreementId $agreementId")
                throw it
            }
    }

    companion object {
        enum class FagSystemAgreementStatus {
            PENDING,
            SENT,
        }
    }
}
