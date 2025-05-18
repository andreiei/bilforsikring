import React, { SyntheticEvent, useState } from 'react';
import styles from './CarInsuranceForm.module.scss';
import postCarInsurance, { CreateCarInsuranceRequest } from '../api/insurance';
import Button from '../components/Button';
import Dropdown from '../components/Dropdown';
import Input from '../components/Input';
import { txt } from '../utils/translate';

const CarInsuranceForm = (): React.ReactElement => {
    const [registrationNumber, setRegistrationNumber] = useState<string>('');
    const [birthNumber, setBirthNumber] = useState<string>('');
    const [firstName, setFirstName] = useState<string>('');
    const [lastName, setLastName] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [bonus, setBonus] = useState<string>('');

    const [isFormValid, setIsFormValid] = useState(true);

    const validateEmail = (emailParam: string): boolean => {
        const emailRegex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(emailParam.trim());
    };

    const validateBirthNumber = (): boolean => /^\d{11}$/.test(birthNumber.trim());

    const validateRegistrationNumber = (): boolean => registrationNumber.trim().length === 7;

    const validateForm = (): boolean => {
        const isValidFirstName: boolean = firstName.trim().length > 0;
        const isValidLastName: boolean = lastName.trim().length > 0;
        const isValidBirthNumber: boolean = validateBirthNumber();
        const isValidRegistrationNumber: boolean = validateRegistrationNumber();
        const isEmailValid: boolean = validateEmail(email);
        const isBonusValid: boolean = !!bonus;
        return (
            isValidFirstName &&
            isValidLastName &&
            isValidBirthNumber &&
            isValidRegistrationNumber &&
            isEmailValid &&
            isBonusValid
        );
    };

    const onSubmitForm = (): void => {
        if (!validateForm()) {
            setIsFormValid(false);
        } else {
            setIsFormValid(true);
            const request: CreateCarInsuranceRequest = {
                firstName: firstName.trim(),
                lastName: lastName.trim(),
                registrationNumber: registrationNumber.trim(),
                email: email.trim(),
                birthNumber: birthNumber.trim(),
                bonus,
            };
            postCarInsurance(request)
                .then((): void => {
                    // TODO Handle valid response
                })
                .catch((): void => {
                    // TODO Handle error
                });
        }
    };

    const onCancelForm = (): void => {
        // TODO Go back to main page
    };

    return (
        <div className={styles.body}>
            <h1 className={styles.header}>{txt('Header')}</h1>
            <div className={styles.description}>{txt('Description')}</div>
            <div className={styles.form}>
                <div className={styles.inputs}>
                    <Input
                        label="CarRegistrationNumber"
                        placeholder="CarRegistrationNumberPlaceholder"
                        onChange={(e: SyntheticEvent<HTMLInputElement>): void =>
                            setRegistrationNumber(e.currentTarget.value)
                        }
                        value={registrationNumber}
                        isValid={validateRegistrationNumber()}
                        validate={!isFormValid}
                        maxLength={7}
                        id="registrationNumber"
                        validationHint="ValidRegistrationNumberHint"
                    />
                    <Input
                        label="BirthNumber"
                        placeholder="BirthNumberPlaceholder"
                        onChange={(e: SyntheticEvent<HTMLInputElement>): void =>
                            setBirthNumber(e.currentTarget.value.trim())
                        }
                        value={birthNumber}
                        isValid={validateBirthNumber()}
                        validate={!isFormValid}
                        validationHint="ValidBirthNumberHint"
                        maxLength={11}
                        id="birthNumber"
                    />
                    <Dropdown
                        title="Bonus"
                        onSelect={(option: string) => setBonus(option)}
                        options={['0', '5.000', '10.000', '20.000']}
                        id="bonus"
                        isValid={!!bonus}
                        validate={!isFormValid}
                        value={bonus}
                    />
                    <div className={styles.inputGroup}>
                        <Input
                            label="FirstName"
                            onChange={(e: SyntheticEvent<HTMLInputElement>): void =>
                                setFirstName(e.currentTarget.value)
                            }
                            value={firstName}
                            isValid={firstName.length > 0}
                            validate={!isFormValid}
                            maxLength={56}
                            id="firstName"
                        />
                        <Input
                            label="LastName"
                            onChange={(e: SyntheticEvent<HTMLInputElement>): void => setLastName(e.currentTarget.value)}
                            value={lastName}
                            isValid={lastName.length > 0}
                            validate={!isFormValid}
                            maxLength={56}
                            id="lastName"
                        />
                    </div>
                    <Input
                        label="Email"
                        onChange={(e: SyntheticEvent<HTMLInputElement>): void => setEmail(e.currentTarget.value.trim())}
                        value={email}
                        isValid={validateEmail(email)}
                        validationHint="ValidEmailHint"
                        validate={!isFormValid}
                        maxLength={255}
                        id="email"
                    />
                </div>
                <div className={styles.buttons}>
                    <Button title="Buy" onClick={onSubmitForm} color="black" />
                    <Button title="Cancel" onClick={onCancelForm} color="white" />
                </div>
            </div>
        </div>
    );
};

export default CarInsuranceForm;
