export interface CreateCarInsuranceRequest {
    registrationNumber: string;
    birthNumber: string;
    firstName: string;
    lastName: string;
    email: string;
}

export interface CreateCarInsuranceResponse {
    agreementId: string;
    status: CarRegistrationStatus;
}

export enum CarRegistrationStatus {
    PENDING,
    ACCEPTED,
    DECLINED,
}

/**
 * I chose to mock the response since the backend isn't expected to run.
 * In a real scenario, this would be a fetch call and the response, successful or failed, would be handled.
 *
 * Depending on the authentication method, the request would contain an access token to validate the request.
 */
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const postCarInsurance = async (request: CreateCarInsuranceRequest): Promise<CreateCarInsuranceResponse> => {
    return {
        agreementId: '5ae8407e-eeb3-4c28-9f2d-aaa7aee00f90',
        status: CarRegistrationStatus.ACCEPTED,
    };
};

export default postCarInsurance;
