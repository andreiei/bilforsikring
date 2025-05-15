package com.airthings.location.test.api.router.spaces.response

import com.airthings.location.common.models.entity.PropertyDefinitionId
import kotlinx.serialization.Serializable

@Serializable
data class PostPredefinedPropertiesResponse(
    val propertyDefinitionId: PropertyDefinitionId,
)
