package failure

import io.netty.handler.codec.http.HttpResponseStatus
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: ErrorCode,
    val description: String,
    val code: Int,
) {
    constructor(error: ErrorCode, description: String?, status: HttpResponseStatus) : this(
        error = error,
        description = description ?: status.reasonPhrase(),
        code = status.code(),
    )
}

@Serializable
enum class ErrorCode(val shortName: String) {
    BAD_REQUEST("bad_request"),
    UNAUTHORIZED("unauthorized"),
    NOT_FOUND("not_found"),
    FORBIDDEN("forbidden"),
    INTERNAL_SERVER_ERROR("internal_server_error"),
    LOCATION_NOT_FOUND("location_not_found"),
    LOCATION_DOES_NOT_BELONG_TO_USER("location_does_not_belong_to_user"),
    RADIO_REGION_NOT_SUPPORTED("radio_region_not_supported"),
    INVALID_IMAGE_INTERLACED_PNG_NOT_SUPPORTED("invalid_image_interlaced_png_not_supported"),
    INVALID_IMAGE_FORMAT_NOT_SUPPORTED("invalid_image_format_not_supported"),
    INVALID_IMAGE_SIZE_TOO_LARGE("invalid_image_size_too_large"),
    USER_ALREADY_INVITED("user_already_invited"),
    USER_GROUP_INVITE_NOT_FOUND("usergroup_invite_not_found"),
    USER_GROUP_INVITE_ALREADY_REDEEMED("usergroup_invite_already_redeemed"),
    USER_GROUP_INVITE_REVOKED("user_group_invite_revoked"),
    USER_GROUP_INVALID_INVITE_RESPONSE("usergroup_invalid_invite_response"),
    SEGMENT_ID_NOT_FOUND("segment_id_not_found"),
    USER_NOT_FOUND("USER_NOT_FOUND"),
    ORGANIZATION_NOT_FOUND("organization_not_found"),
    INVITE_NOT_FOUND("INVITE_NOT_FOUND"),
    MAXIMUM_NUMBER_OF_ALERT_TRIGGERS("MAXIMUM_NUMBER_OF_ALERT_TRIGGERS"),
    INVALID_GRANT_TYPE("INVALID_GRANT_TYPE"),
    INVALID_AUTHORIZATION_CODE("INVALID_AUTHORIZATION_CODE"),
    INVALID_CLIENT_CREDENTIALS("INVALID_CLIENT_CREDENTIALS"),
    LOCATION_WITH_ONGOING_SEGMENTS_CANNOT_BE_DELETED("location_with_ongoing_segments_cannot_be_deleted"),
    UNSUPPORTED_RADON_UNIT_FOR_REPORT("unsupported_radon_unit_for_home_device_report"),

    // Subscriptions Error Codes
    ZUORA_ACCOUNT_NOT_FOUND("zuora_account_not_found"),
    UNABLE_TO_CREATE_ZUORA_ACCOUNT("unable_to_create_zuora_account"),
    INVALID_INFORMATION_IN_ZUORA("invalid_information_in_zuora"),
    INVALID_PRODUCT_RATE_PLAN("invalid_product_rate_plan"),
    INVALID_SUBSCRIPTION_ORDER("invalid_subscription_order"),

    // Reports
    INVALID_REQUEST_REPORT_FROM_AFTER_TO("invalid_request_report_fromdate_after_todate"),
    INVALID_REQUEST_REPORT_FROM_TOO_CLOSE_TO("invalid_request_report_fromdate_too_close_to_todate"),
    INVALID_REQUEST_REPORT_FROM_TO_TOO_APART("invalid_request_report_todate_too_far_from_fromdate"),
    LATEST_SEGMENTS_NOT_FOUND_FOR_GROUP("latest_segments_not_found_for_group"),

    SUBSCRIPTION_NOT_FOUND("subscription_not_found"),

    // SSO Error Codes
    INVALID_FEDERATED_CLIENT_ID("invalid_federated_client_id"),
    MALFORMED_REQUEST("malformed_request"),
    FEDERATED_CLIENT_ALREADY_EXISTS("federated_client_already_exists"),
    FEDERATED_CLIENT_NOT_FOUND("federated_client_not_found"),
    DOMAIN_VERIFICATION_ERROR("domain_verification_error"),
    FEDERATED_CLIENT_EMAIL_CONFLICT("federated_client_email_conflict"),
    FEDERATED_CLIENT_NOT_VERIFIED("federated_client_not_verified"),
    MISSING_OIDC_CONFIG("missing_oidc_config"),
    SSO_FAILURE("sso_failure"),
    FEDERATED_CLIENT_DOES_NOT_BELONG_TO_ORGANIZATION("federated_client_does_not_belong_to_organization"),

    INVALID_EMAIL_DOMAIN("invalid_email_domain"),
    NO_SAMPLE_VALUES_FOR_REPORT("no_sample_values_for_report"),
    NO_RADON_SENSOR("no_radon_sensor"),
    NOT_ENOUGH_SAMPLES_FOR_REPORT("not_enough_samples_for_report"),
    PUBLIC_DASHBOARD_NOT_FOUND("public_dashboard_not_found"),

    // Device Registration
    UNABLE_TO_ADD_DEVICE_TO_SPACE("unable_to_add_device_to_space"),
    DEVICE_LIMIT_EXCEEDED("device_limit_exceeded"),
    SPACE_NOT_FOUND("space_not_found"),

    SPACE_NOT_EMPTY("space_not_empty"),
    DEVICE_LIMIT_ON_LOCATION_EXCEEDED("device_limit_on_location_exceeded"),
    SPACE_ALREADY_CONTAINS_DEVICE("space_already_contains_device"),
    DEVICE_NOT_IN_SPACE("device_not_in_space"),
    UNABLE_TO_FETCH_PROPERTY_DEFINITIONS("unable_to_fetch_property_definitions"),
    UNABLE_TO_CREATE_PROPERTY_DEFINITION("unable_to_create_property_definition"),
    UNABLE_TO_UPDATE_PROPERTY_DEFINITION_VALUE("unable_to_update_property_definition_value"),
    PROPERTY_DEFINITION_NOT_FOUND("property_definition_not_found"),
    DEVICE_MGMT_MOVE_DEVICE_FAILURE("device_mgmt_move_device_failure"),
    DEVICE_MGMT_DEREGISTER_FAILURE("device_mgmt_deregister_failure"),
    DEVICE_MGMT_UNABLE_TO_UPDATE_SEGMENT_NAME("device_mgmt_unable_to_update_segment_name"),
    METHOD_NOT_ALLOWED("method_not_allowed"),
    DEVICE_ALREADY_REGISTERED_TO_ORGANIZATION("device_already_registered_to_organization"),
    DEVICE_ALREADY_REGISTERED("device_already_registered"),
    DEVICE_INVALID_CHECK_CODE("device_invalid_checkcode"),
    DEVICE_MAC_NOT_FOUND("device_mac_not_found"),

    PROPERTY_DEFINITION_VALUE_NOT_FOUND("property_definition_value_not_found"),
}
