import kotlinx.serialization.Serializable
import models.AgreementId

@Serializable
data class PostCarInsuranceResponse(
    val agreementId: AgreementId,
    val status: InsuranceStatus,
)

@Serializable
enum class InsuranceStatus {
    PENDING,
    SENT,
}
