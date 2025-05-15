
export interface CarInsuranceRequest {
    registrationNumber: string;
    birthNumber: string;
    firstName: string;
    lastName: string;
    email: string;
}

export enum Status {
    PENDING,
    ACCEPTED,
    DECLINED,
}

export interface CarInsuranceResponse {
    agreementNumber: string,
    status: Status
}

/**
 * I chose to mock the response since the backend isn't expected to run.
 * In a real scenario, this would be a fetch call, and I'd handle errors and response status properly.
 *
 * Depending on the authentication method, the request would contain an access token to validate the request.
 */
const postCarInsurance = async (request: CarInsuranceRequest): Promise<CarInsuranceResponse> => {
    return {
        agreementNumber: "123",
        status: Status.PENDING,
    };
}

export default postCarInsurance;
