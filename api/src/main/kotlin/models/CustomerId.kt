package models

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class CustomerId(private val value: String) {
    override fun toString(): String = value

}

