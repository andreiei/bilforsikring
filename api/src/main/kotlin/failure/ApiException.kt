package failure

object ApiException {
    class BadRequestException(
        message: String = "Invalid request",
        throwable: Throwable? = null,
    ) : Exception(message, throwable)

    class ForbiddenException(message: String) : Exception(message)

    class UnauthorizedException(message: String) : Exception(message)

    class MethodNotAllowedException(message: String) : Exception(message)

    class NotFoundException(message: String) : Exception(message)

    class InternalServerErrorException(message: String?, throwable: Throwable? = null) : Exception(message, throwable)

    class RuntimeErrorException(message: String) : Exception(message)

    class LocationNotFound(locationId: LocationId) : Exception("Location with id: $locationId not found")

    class LocationDoesNotBelongToUser(locationId: LocationId) :
        Exception("Location with id: $locationId does not belong to user")

    class RadioRegionNotSupported(countryCode: String) :
        Exception("Radio region is not supported for country $countryCode")

    class InvalidImageSizeTooLarge : Exception("Image size can not be larger than 2 MB")

    class InvalidImageFormatNotSupported : Exception("Only PNG and JPG formats supported")

    class InvalidImageInterlacedPngNotSupported : Exception("Adam7 interlaced PNG image is not supported")

    class LatestSegmentNotFoundException(serialNumber: SerialNumber) :
        Exception("Latest segment not found for serial number $serialNumber")

    class SegmentNotFoundForDeviceException(segmentId: SegmentId, serialNumber: SerialNumber) :
        Exception("Segment: $segmentId for device: $serialNumber not found")

    class SegmentNotFound(publicPath: String) : Exception("No active segment found for public path: $publicPath")

    class SegmentIdNotFound(segmentId: SegmentId) : Exception("No active segment found for segment: $segmentId")

    class UserAlreadyInvited : Exception("User already invited")

    class InvalidInviteResponse : Exception("Invalid response to invite")

    class InviteAlreadyRedeemed : Exception("Invite already redeemed")

    class InviteRevoked(inviteId: InviteId) : Exception("Invite with id $inviteId has been revoked")

    class UserNotFound(userId: UserId) : Exception("User with id $userId not found")

    class OrganizationNotFound(userGroupId: UserGroupId) : Exception("Organization for group $userGroupId not found")

    class OrganizationNotFoundForDevice(serialNumber: SerialNumber) :
        Exception("No organization found for device with serial number: $serialNumber")

    class InviteNotFound(inviteId: InviteId) : Exception("Invite with id $inviteId not found")

    class ZuoraAccountNotFound(userGroupId: UserGroupId, throwable: Throwable? = null) :
        Exception("Missing Zuora accountId for user group $userGroupId", throwable)

    class UnableToCreateZuoraAccount(zuoraAccountId: ZuoraAccountId, throwable: Throwable? = null) :
        Exception("Unable to create child Zuora account for: $zuoraAccountId", throwable)

    class InvalidInformationInZuora(message: String, throwable: Throwable? = null) : Exception(message)

    class MaxNumberOfAlertTriggers : Exception("Exceeded maximum number of alerts per group")

    class InvalidGrantType : Exception("Invalid authorization code")

    class InvalidAuthorizationCode : Exception("Invalid authorization code")

    class InvalidClientCredentials : Exception("Invalid client credentials")

    class InvalidProductRatePlan(message: String, throwable: Throwable? = null) : Exception(message, throwable)

    class InvalidSubscriptionOrder(message: String, throwable: Throwable? = null) : Exception(message, throwable)

    class LocationWithOngoingSegmentsCannotBeDeleted(locationId: LocationId) :
        Exception("Location with id: $locationId has ongoing segments and cannot be deleted")

    class UnsupportedRadonUnitForReport :
        Exception("Unsupported unit for radon, only bequerel and picocurie are supported")

    class FromDateMustBeBeforeToDate : Exception("From date must be after to date")

    class FromDateToDateMustBeXDaysApart(days: Int) : Exception("From and to date must be at least $days days apart")

    class FromDateToDateMustBeWithinXDays(days: Int) : Exception("From and to date must be within $days days")

    class SubscriptionNotFound(subscriptionNumber: String) :
        Exception("Unable to find the subscription number: $subscriptionNumber")

    class LatestSegmentsNotFoundForGroup() : Exception("Unable to find latest segments")

    class InvalidEmailDomain(emailDomain: String) : Exception("Invalid email domain: $emailDomain")

    // SSO exceptions
    class InvalidFederatedClientId(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "Invalid federated client ID", throwable)

    class MalformedRequest(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "Bad request", throwable)

    class FederatedClientAlreadyExists(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "Federated client already exists", throwable)

    class FederatedClientNotFound(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "Federated client not found", throwable)

    class DomainVerificationError(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "BadRequest", throwable)

    class FederatedClientEmailConflict(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "Email domain can not be updated", throwable)

    class FederatedClientNotVerified(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "Client domain not verified", throwable)

    class MissingOidcConfig(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "Unable to discover OIDC config for issuer", throwable)

    class SsoFailure(message: String?, throwable: Throwable? = null) :
        Exception(message ?: "Something went wrong with SSO", throwable)

    class FederatedClientDoesNotBelongToOrganization(message: String, throwable: Throwable? = null) :
        Exception(message, throwable)

    class NoSampleValuesForReportException :
        Exception("No samples found in the provided time range, no report generated")

    class NoRadonSensorException : Exception("No radon sensor found for device")

    class NotEnoughSamplesForReport : Exception("Not enough samples for report")

    class PublicDashboardNotFound(publicPath: String) :
        Exception("No mapping found for public dashboard with url: $publicPath")

    class UnableToAddDeviceToSpace(message: String, throwable: Throwable? = null) : Exception(message, throwable)

    class UnableToFetchSpace(message: String, throwable: Throwable? = null) : Exception(message, throwable)

    class SpaceNotFound(spaceId: SpaceId) : Exception("Space with id: $spaceId not found")

    class SpaceNotEmpty(spaceId: SpaceId) : Exception("Space with id: $spaceId is not empty")

    class DeviceLimitExceeded(spaceId: SpaceId) : Exception("Device limit exceeded for space: $spaceId")
    class DeviceLimitOnLocationExceeded(
        locationId: LocationId,
    ) : Exception("Device limit exceeded for location: $locationId")
    class SpaceAlreadyContainsDevice(
        toSpaceId: SpaceId,
        serialNumber: SerialNumber,
    ) : Exception("Space $toSpaceId already contains device $serialNumber")

    class DeviceNotInSpace(fromSpaceId: SpaceId, serialNumber: SerialNumber) :
        Exception("Space $fromSpaceId does not contain device $serialNumber")

    class UnableToFetchPropertyDefinitions(message: String, throwable: Throwable? = null) :
        Exception(message, throwable)

    class UnableToCreatePropertyDefinition(message: String, throwable: Throwable? = null) :
        Exception(message, throwable)

    class UnableToUpdatePropertyDefinitionValue(message: String, throwable: Throwable? = null) :
        Exception(message, throwable)

    class PropertyDefinitionNotFound(propertyDefinitionId: PropertyDefinitionId) :
        Exception("Property definition with id: $propertyDefinitionId not found")

    class DeviceMgmtMoveDeviceFailure(message: String, throwable: Throwable? = null) :
        Exception(message, throwable)

    class DeviceMgmtDeregisterFailure(message: String, throwable: Throwable? = null) :
        Exception(message, throwable)

    class PropertyDefinitionValueNotFound(propertyName: String) :
        Exception("Property definition value not found for property name: $propertyName")

    class DeviceMgmtDeviceAlreadyRegisteredToAccount(message: String) : Exception(message)

    class DeviceMgmtDeviceAlreadyRegistered(message: String) : Exception(message)

    class DeviceMgmtDeviceInvalidCheckCode(message: String) : Exception(message)

    class DeviceMgmtMacAddressNotFound(message: String) : Exception(message)

    class DeviceMgmtUnableToUpdateSegmentName(message: String, throwable: Throwable? = null) :
        Exception(message, throwable)
}
