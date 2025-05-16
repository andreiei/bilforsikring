import en from '../locales/no.json';

/**
 * A stand in for a proper react-i18next implementation.
 */
export function txt(key: string, vars?: Record<string, string | number>): string {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const value = key.split('.').reduce((obj: any, k) => {
        if (obj && k in obj) return obj[k];
        throw new Error(`Missing translation key: "${key}"`);
    }, en);
    if (!vars) return value;
    return Object.entries(vars).reduce((str, [k, v]) => str.replaceAll(`{{${k}}}`, String(v)), value);
}

export default txt;
