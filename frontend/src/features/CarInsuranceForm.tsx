import React, { SyntheticEvent, useState } from 'react';
import styles from './CarInsuranceForm.module.scss';
import postCarInsurance, { CarInsuranceRequest } from '../api/insurance';
import Button from '../components/Button';
import Dropdown from '../components/Dropdown';
import Input from '../components/Input';
import { txt } from '../utils/translate';

const CarInsuranceForm: () => React.ReactElement = (): React.ReactElement => {
    const [registrationNumber, setRegistrationNumber] = useState<string>('');
    const [birthNumber, setBirthNumber] = useState<string>('');
    const [firstName, setFirstName] = useState<string>('');
    const [lastName, setLastName] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [bonus, setBonus] = useState<string>('');

    const [isValidForm, setIsValidForm] = useState(true);

    const validateEmail = (emailParam: string): boolean => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(emailParam);
    };

    const validateBirthNumber = (value: string): boolean => /^\d{11}$/.test(value);

    const validateForm = (): boolean => {
        const isValidFirstName = firstName.length > 0;
        const isValidLastName = lastName.length > 0;
        const isValidBirthNumber = validateBirthNumber(birthNumber);
        const isValidRegistrationNumber = registrationNumber.length >= 3 && registrationNumber.length <= 6;
        const isEmailValid = validateEmail(email);
        const isBonusValid = bonus.length > 0;
        return (
            isValidFirstName &&
            isValidLastName &&
            isValidBirthNumber &&
            isValidRegistrationNumber &&
            isEmailValid &&
            isBonusValid
        );
    };

    const onBuyInsurance = (): void => {
        if (!validateForm()) {
            setIsValidForm(false);
        } else {
            setIsValidForm(true);
            const request: CarInsuranceRequest = {
                firstName,
                lastName,
                registrationNumber,
                email,
                birthNumber,
            };
            postCarInsurance(request)
                .then((): void => {
                    // TODO Handle response
                })
                .catch((): void => {
                    // TODO Handle error
                });
        }
    };

    const onCancel = (): void => {
        // Go back to previous page
    };

    return (
        <div className={styles.body}>
            <h1 className={styles.header}>{txt('Header')}</h1>
            <p className={styles.description}>{txt('Description')}</p>

            <div className={styles.form}>
                <div className={styles.inputs}>
                    <Input
                        label="CarRegistrationNumber"
                        placeholder="CarRegistrationNumberHint"
                        onChange={(e: SyntheticEvent<HTMLInputElement>): void =>
                            setRegistrationNumber(e.currentTarget.value.trim())
                        }
                        value={registrationNumber}
                        isValid={registrationNumber.length > 0 && registrationNumber.length <= 6}
                        validate={!isValidForm}
                        maxLength={6}
                        id="registrationNumber"
                    />
                    <Input
                        label="BirthNumber"
                        placeholder="BirthNumberHint"
                        onChange={(e: SyntheticEvent<HTMLInputElement>): void =>
                            setBirthNumber(e.currentTarget.value.trim())
                        }
                        value={birthNumber}
                        isValid={validateBirthNumber(birthNumber)}
                        validate={!isValidForm}
                        validationHint="ValidBirthNumberHint"
                        maxLength={11}
                        id="birthNumber"
                    />
                    <Dropdown
                        title="Bonus"
                        onSelect={(option: string) => setBonus(option)}
                        options={['10.000', '20.000', '30.000']}
                        id="bonus"
                        isValid={bonus.length > 0}
                        validate={!isValidForm}
                        value={bonus}
                    />
                    <div className={styles.inputGroup}>
                        <Input
                            label="FirstName"
                            onChange={(e: SyntheticEvent<HTMLInputElement>): void =>
                                setFirstName(e.currentTarget.value.trim())
                            }
                            value={firstName}
                            isValid={firstName.length > 0}
                            validate={!isValidForm}
                            maxLength={56}
                            id="firstName"
                        />
                        <Input
                            label="LastName"
                            onChange={(e: SyntheticEvent<HTMLInputElement>): void =>
                                setLastName(e.currentTarget.value.trim())
                            }
                            value={lastName}
                            isValid={lastName.length > 0}
                            validate={!isValidForm}
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
                        validate={!isValidForm}
                        maxLength={255}
                        id="email"
                    />
                </div>
                <div className={styles.buttons}>
                    <Button title="Buy" onClick={onBuyInsurance} color="black" />
                    <Button title="Cancel" onClick={onCancel} color="white" />
                </div>
            </div>
        </div>
    );
};

export default CarInsuranceForm;
