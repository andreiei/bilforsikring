package router.insurance.request

import kotlinx.serialization.Serializable
import models.NationalId

@Serializable
data class PostCarInsuranceRequest(
    val registrationNumber: String,
    val birthNumber: NationalId,
    val firstName: String,
    val lastName: String,
    val email: String,
    val bonus: String,
) {
    fun validate() {
        require(registrationNumber.length in 1..8) {
            "Registration number is required."
        }
        require(lastName.length in 1..80) {
            "Last name is required."
        }
        require(firstName.length in 1..80) {
            "First name is required."
        }
        require(isValidEmail(email)) {
            "Invalid email $email"
        }
        require(birthNumber.toString().length == 11) {
            "Birth number must be 11 characters long."
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(emailRegex) && email.length <= 255
    }
}
