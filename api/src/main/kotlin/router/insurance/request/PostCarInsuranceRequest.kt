package com.airthings.location.test.api.router.insurance.request

import kotlinx.serialization.Serializable

@Serializable
data class PostCarInsuranceRequest(
    val registrationNumber: String,
    val birthNumber: String,
    val firstName: String,
    val lastName: String,
    val email: String,
) {
    fun validate() {
        val errors = mutableListOf<String>()

        if (registrationNumber.isBlank()) errors.add("Registration number is required.")
        if (birthNumber.isBlank()) errors.add("Birth number is required.")
        if (firstName.isBlank()) errors.add("First name is required.")
        if (lastName.isBlank()) errors.add("Last name is required.")
        if (!email.contains("@")) errors.add("Email must be valid.")

        return errors
    }
}
