import React, { SyntheticEvent } from 'react';
import classNames from 'classnames';
import styles from './Input.module.scss';
import { txt } from '../utils/translate';

type Props = {
    id: string;
    value: string;
    placeholder?: string;
    onChange: (e: SyntheticEvent<HTMLInputElement>) => void;
    onBlur?: (e: SyntheticEvent<HTMLInputElement>) => void;
    onKeyUp?: (e: React.KeyboardEvent) => void;
    label?: string;
    validationHint?: string;
    isValid?: boolean;
    validate: boolean;
    maxLength?: number;
    testId?: string;
};

function Input({
    maxLength = 255,
    validate,
    isValid,
    validationHint,
    value,
    label,
    placeholder,
    onChange,
    onBlur,
    onKeyUp,
    id,
    testId,
}: Props): React.ReactElement {
    return (
        <div className={styles.inputContainer}>
            {label && (
                <label htmlFor={id} className={styles.label}>
                    {txt(label)}
                </label>
            )}
            <input
                className={classNames(styles.input, {
                    [styles.invalid]: isValid === false && validate,
                })}
                type="text"
                id="id"
                onChange={onChange}
                onBlur={onBlur}
                onKeyUp={onKeyUp}
                placeholder={placeholder && txt(placeholder)}
                value={value}
                maxLength={maxLength}
                {...{ 'data-testid': testId }}
            />
            {!isValid && validate && validationHint && <div className={styles.error}>{txt(validationHint)}</div>}
        </div>
    );
}

export default Input;
