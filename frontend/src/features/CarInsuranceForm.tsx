import React, { SyntheticEvent, useState } from 'react';
import styles from './CarInsuranceForm.module.scss'
import Input from "../components/Input";
import Button from "../components/Button";
import { txt } from "../utils/translate";
import postCarInsurance, { CarInsuranceRequest } from '../api/insurance';
import Dropdown from "../components/Dropdown";

const CarInsuranceForm: () => React.ReactElement = (): React.ReactElement => {
    const [registrationNumber, setRegistrationNumber] = useState<string>("");
    const [birthNumber, setBirthNumber] = useState<string>("");
    const [firstName, setFirstName] = useState<string>("");
    const [lastName, setLastName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [bonus, setBonus] = useState<string>("");

    const [isValidForm, setIsValidForm] = useState(true);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<boolean>(true);

    const validateEmail = (email: string): boolean => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    };

    const validateBirthNumber = (value: string): boolean => /^\d{11}$/.test(value);

    const validateForm = (): boolean => {
        const isValidFirstName = firstName.length > 0
        const isValidLastName = lastName.length > 0
        const isValidBirthNumber = validateBirthNumber(birthNumber);
        const isValidRegistrationNumber = registrationNumber.length >= 3 && registrationNumber.length <= 6
        const isEmailValid = validateEmail(email)
        const isBonusValid = bonus.length > 0
        return isValidFirstName && isValidLastName && isValidBirthNumber && isValidRegistrationNumber && isEmailValid && isBonusValid
    }

    const onBuyInsurance = (): void => {
        console.log("A")
        const isValidForm = validateForm();
        console.log(isValidForm)
        if (!isValidForm) {
            setIsValidForm(false);
        } else {
            setIsValidForm(true)
            const request: CarInsuranceRequest = {
                firstName: firstName,
                lastName: lastName,
                registrationNumber: registrationNumber,
                email: email,
                birthNumber: birthNumber,
            }
            console.log(request);
            setLoading(true)
            postCarInsurance(request)
                .then((response) => console.log(response))
                .catch((): void => setError(true))
            setLoading(false)
        }
    }

    const onCancel = (): void => {
        // Go back to previous page
    }

    return (
        <div className={styles.body}>
            <h1 className={styles.header}>{txt("Header")}</h1>
            <p className={styles.description}>{txt("Description")}</p>

            <div className={styles.form}>
                <div className={styles.inputs}>
                    <Input
                        label="CarRegistrationNumber"
                        placeholder="CarRegistrationNumberHint"
                        onChange={(e: SyntheticEvent<HTMLInputElement>): void => setRegistrationNumber(e.currentTarget.value.trim())}
                        value={registrationNumber}
                        isValid={registrationNumber.length > 0 && registrationNumber.length <= 6}
                        validate={!isValidForm}
                        maxLength={6}
                    />
                    <Input
                        label="BirthNumber"
                        placeholder="BirthNumberHint"
                        onChange={(e: SyntheticEvent<HTMLInputElement>): void => setBirthNumber(e.currentTarget.value.trim())}
                        value={birthNumber}
                        isValid={validateBirthNumber(birthNumber)}
                        validate={!isValidForm}
                        validationHint="ValidBirthNumberHint"
                        maxLength={11}
                    />
                    <Dropdown
                        title="Bonus"
                        onSelect={(option: string) => setBonus(option)}
                        options={["10.000", "20.000", "30.000"]}
                        id="Bonus"
                        isValid={bonus.length > 0}
                        validate={!isValidForm}
                        value={bonus}
                    />
                    <div className={styles.inputGroup}>
                        <Input
                            label="FirstName"
                            onChange={(e: SyntheticEvent<HTMLInputElement>): void => setFirstName(e.currentTarget.value.trim())}
                            value={firstName}
                            isValid={firstName.length > 0}
                            validate={!isValidForm}
                            maxLength={56}
                        />
                        <Input
                            label="LastName"
                            onChange={(e: SyntheticEvent<HTMLInputElement>): void => setLastName(e.currentTarget.value.trim())}
                            value={lastName}
                            isValid={lastName.length > 0}
                            validate={!isValidForm}
                            maxLength={56}
                        />
                    </div>
                    <Input
                        label="Email"
                        onChange={(e: SyntheticEvent<HTMLInputElement>): void => setEmail(e.currentTarget.value.trim())}
                        value={email}
                        isValid={validateEmail(email)}
                        validationHint="ValidEmailHint"
                        validate={!isValidForm}
                        maxLength={255}
                    />
                </div>
                <div className={styles.buttons}>
                    <Button title="Buy" onClick={onBuyInsurance} color="black" loading />
                    <Button title="Cancel" onClick={onCancel} color="white"/>
                </div>
                {error && <div>Error...</div>}
            </div>
        </div>
    );
};

export default CarInsuranceForm;
