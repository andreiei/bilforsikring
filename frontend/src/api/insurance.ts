export interface CreateCarInsuranceRequest {
    registrationNumber: string;
    birthNumber: string;
    firstName: string;
    lastName: string;
    bonus: string;
    email: string;
}

export interface CreateCarInsuranceResponse {
    agreementId: string;
    status: CarRegistrationStatus;
}

export enum CarRegistrationStatus {
    PENDING,
    SENT,
}

/**
 * Mock of a Fetch request.
 * In a real scenario, this would be a fetch call, and the response, successful or failed, would be handled.
 */
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const postCarInsurance = async (request: CreateCarInsuranceRequest): Promise<CreateCarInsuranceResponse> => {
    return {
        agreementId: '5ae8407e-eeb3-4c28-9f2d-aaa7aee00f90',
        status: CarRegistrationStatus.SENT,
    };
};

export default postCarInsurance;
