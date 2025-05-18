package models

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class NationalId(private val value: String) {
    override fun toString(): String = value
}
