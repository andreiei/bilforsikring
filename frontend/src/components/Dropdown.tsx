import React, { ReactElement } from 'react';
import classNames from 'classnames';
import styles from './Dropdown.module.scss';
import { txt } from '../utils/translate';

type Props = {
    title: string;
    onSelect: (id: string) => void;
    options: string[];
    id: string;
    isValid?: boolean;
    validate: boolean;
    value?: string;
};

const Dropdown = ({
    value = undefined,
    isValid = true,
    validate,
    options,
    onSelect,
    title,
    id,
}: Props): ReactElement => {
    return (
        <div className={styles.dropdownContainer}>
            <label htmlFor={id} className={styles.label}>
                {txt(title)}
            </label>
            <select
                className={classNames(styles.dropdown, {
                    [styles.invalid]: isValid === false && validate,
                })}
                value={value}
                id={id}
                onChange={e => onSelect(e.target.value)}
            >
                {!value && (
                    <option value="" disabled>
                        {txt('DropdownPlaceholder')}
                    </option>
                )}
                {options.map(opt => (
                    <option key={opt} value={opt}>
                        {opt}
                    </option>
                ))}
            </select>
        </div>
    );
};

export default Dropdown;
