package router.insurance.request

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import models.NationalId

class PostCarInsuranceRequestTest : FreeSpec({
    val request = PostCarInsuranceRequest(
        registrationNumber = "123",
        birthNumber = NationalId("99999999999"),
        firstName = "Ola",
        lastName = "Nordman",
        email = "test@localhost.com",
        bonus = "20.000",
    )

    "should pass" {
        shouldNotThrow<IllegalArgumentException> {
            request.validate()
        }
    }

    "fail: ${PostCarInsuranceRequest::registrationNumber}" - {
        "invalid string length 0" {
            shouldThrow<IllegalArgumentException> {
                request.copy(registrationNumber = "").validate()
            }
        }
        "invalid string length 9" {
            shouldThrow<IllegalArgumentException> {
                request.copy(registrationNumber = "ABCDEFGHIKL").validate()
            }
        }
    }

    "fail: ${PostCarInsuranceRequest::birthNumber}" {
        shouldThrow<IllegalArgumentException> {
            request.copy(birthNumber = NationalId("070592")).validate()
        }
    }

    "fail: ${PostCarInsuranceRequest::firstName}" - {
        "invalid string length 0" {
            shouldThrow<IllegalArgumentException> {
                request.copy(firstName = "").validate()
            }
        }
        "string length exceeds maximum" {
            shouldThrow<IllegalArgumentException> {
                request.copy(
                    firstName = """
                    ABCDEFGHIKLABCDEFGHIKLABCDEFGHIKLABC
                    ABCDEFGHIKLABCDEFGHIKLABCDEFGHIKLABC
                    ABCDEFGHIKLABCDEFGHIKLABCDEFGHIKLABC
                    """.trimIndent(),
                ).validate()
            }
        }
    }
    "fail: ${PostCarInsuranceRequest::lastName}" - {
        "invalid string length 0" {
            shouldThrow<IllegalArgumentException> {
                request.copy(lastName = "").validate()
            }
        }
        "string length exceed maximum" {
            shouldThrow<IllegalArgumentException> {
                request.copy(
                    lastName = """
                    ABCDEFGHIKLABCDEFGHIKLABCDEFGHIKLABC
                    ABCDEFGHIKLABCDEFGHIKLABCDEFGHIKLABC
                    ABCDEFGHIKLABCDEFGHIKLABCDEFGHIKLABC
                    """.trimIndent(),
                ).validate()
            }
        }
    }
    "fail: ${PostCarInsuranceRequest::email}" {
        shouldThrow<IllegalArgumentException> {
            request.copy(email = "invalid").validate()
        }
    }
})
