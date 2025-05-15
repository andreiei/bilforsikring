import React, { SyntheticEvent } from 'react';
import styles from './Button.module.scss'
import classNames from 'classnames';
import {txt} from "../utils/translate";

export type ButtonColor = 'black' | 'white';

type Props = {
    onClick: (e: SyntheticEvent<HTMLButtonElement>) => void | Promise<void>;
    title: string;
    color: ButtonColor;
    loading?: boolean;
};

const Button = ({ loading, onClick, title, color }: Props): React.ReactElement => {
    return (
        <button
            className={classNames(styles.button, styles[color])}
            onClick={onClick}
            type="button"
        >
            {loading ? "..." : txt(title)}
        </button>
    )
}

export default Button;

