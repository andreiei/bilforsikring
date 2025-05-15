package com.airthings.location.test.api.router.spaces.response

import com.airthings.location.common.storage.ddb.models.DDBPropertyDefinition
import kotlinx.serialization.Serializable

@Serializable
data class GetPredefinedPropertiesResponse(
    val definitions: List<DDBPropertyDefinition>,
)
