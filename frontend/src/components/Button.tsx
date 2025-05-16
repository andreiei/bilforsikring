import React, { SyntheticEvent } from 'react';
import classNames from 'classnames';
import styles from './Button.module.scss';
import { txt } from '../utils/translate';

export type ButtonColor = 'black' | 'white';

export type Props = {
    onClick: (e: SyntheticEvent<HTMLButtonElement>) => void;
    title: string;
    color: ButtonColor;
    testId?: string;
};

const Button = ({ testId, onClick, title, color }: Props): React.ReactElement => {
    return (
        <button
            className={classNames(styles.button, styles[`color-${color}`])}
            onClick={onClick}
            type="button"
            {...{ 'data-testid': testId }}
        >
            {txt(title)}
        </button>
    );
};

export default Button;
