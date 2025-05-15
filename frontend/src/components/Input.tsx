import React, {SyntheticEvent} from 'react';
import styles from './Input.module.scss'
import {txt} from "../utils/translate";
import classNames from "classnames";

type Props = {
    value: string;
    placeholder?: string;
    onChange: (e: SyntheticEvent<HTMLInputElement>) => void;
    onBlur?: (e: SyntheticEvent<HTMLInputElement>) => void;
    onKeyUp?: (e: React.KeyboardEvent) => void;
    label?: string;
    validationHint?: string;
    isValid?: boolean
    validate: boolean;
    maxLength?: number;
}

const Input = ({ maxLength = 255, validate, isValid, validationHint, value, label, placeholder, onChange, onBlur, onKeyUp }: Props): React.ReactElement => {
    return (
        <div className={styles.inputContainer}>
            {label && (
                <label className={styles.label}>{txt(label)}</label>
            )}
            <input
                className={classNames(styles.input, {
                    [styles.invalid]: isValid === false && validate
                })}
                type="text"
                id="id"
                onChange={onChange}
                onBlur={onBlur}
                onKeyUp={onKeyUp}
                placeholder={placeholder && txt(placeholder)}
                value={value}
                required
                maxLength={maxLength}

                /*required={required}
                onChange={onChange}
                onBlur={onBlur}
                onKeyUp={onKeyUp}
                placeholder={placeholder}
                autoComplete={autoComplete}
                defaultValue={defaultValue}
                maxLength={maxLength}
                {...(testAttr !== undefined ? { [`data-${testAttr}`]: true } : {})}
                value={currentValue}
                accept={accept}
                disabled={disabled}
                // eslint-disable-next-line jsx-a11y/no-autofocus
                autoFocus={autoFocus}
                data-form-type="other" // preventing Dashlane from trying to auto fill
                {...{ 'data-testid': testId }}
                ref={innerRef}*/
            />
            {!isValid && validate && validationHint && <div className={styles.error}>{txt(validationHint)}</div>}
        </div>
    )
}

/*
 {validate && !isValid && hint && <div className="input-container__error">{hint && txt(hint)}</div>}
            {validate && isValid && checkmark}
 */


export default Input;
