import React, { SyntheticEvent } from 'react';
import classNames from 'classnames';
import styles from './Button.module.scss';
import { txt } from '../utils/translate';

export type ButtonColor = 'black' | 'white';

type Props = {
    onClick: (e: SyntheticEvent<HTMLButtonElement>) => void | Promise<void>;
    title: string;
    color: ButtonColor;
};

const Button = ({ onClick, title, color }: Props): React.ReactElement => {
    return (
        <button className={classNames(styles.button, styles[color])} onClick={onClick} type="button">
            {txt(title)}
        </button>
    );
};

export default Button;
