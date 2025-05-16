import kotlinx.serialization.Serializable
import models.AgreementId

@Serializable
data class PostCarInsuranceResponse(
    val agreementId: AgreementId,
    val status: CarInsuranceStatus
)

enum class CarInsuranceStatus {
    PENDING,
    ACCEPTED,
    DECLINED,
}
