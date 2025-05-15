package com.airthings.location.test.api.router.spaces.request

import com.airthings.location.common.models.entity.UserGroupId
import kotlinx.serialization.Serializable

@Serializable
data class PostPredefinedPropertiesRequest(
    val userGroupId: UserGroupId,
)
