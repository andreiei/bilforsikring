const customRules = {
    "indent": ["error", 4],
    'prettier/prettier': [
        'error',
        {
            tabWidth: 4,
            useTabs: false,
            semi: true,
            singleQuote: true,
        },
    ],
    '@typescript-eslint/no-unused-expressions': [
        'error',
        {
            allowShortCircuit: true,
            allowTernary: false,
        },
    ],
    'react/require-default-props': [
        'off',
        {
            ignoreFunctionalComponents: true,
        },
    ],
    'react/function-component-definition': [
        0,
        { 'named-components': 'arrow-function', unnamedComponents: 'arrow-function' },
    ],
    '@typescript-eslint/no-unused-vars': ['error'],
    'no-use-before-define': 'off',
    '@typescript-eslint/no-use-before-define': ['error'],
    'react/jsx-filename-extension': [2, { extensions: ['.ts', '.tsx'] }],
    'import/extensions': [
        'error',
        'ignorePackages',
        {
            js: 'never',
            jsx: 'never',
            ts: 'never',
            tsx: 'never',
        },
    ],
    'import/order': [
        'error',
        {
            groups: ['builtin', 'external', 'internal', ['sibling', 'parent']],
            pathGroups: [
                {
                    pattern: 'react',
                    group: 'builtin',
                    position: 'before',
                },
            ],
            alphabetize: {
                order: 'asc',
                caseInsensitive: true,
            },
            pathGroupsExcludedImportTypes: ['react'],
        },
    ],
};

module.exports = {
    extends: [
        'plugin:react-hooks/recommended',
        'airbnb',
        'airbnb/hooks',
        'plugin:import/typescript',
        'plugin:@typescript-eslint/eslint-recommended',
        'plugin:@typescript-eslint/recommended',
        'plugin:prettier/recommended',
        'prettier'
    ],
    plugins: ['@typescript-eslint', 'prettier', 'import'],
    rules: { ...customRules },
    root: true,
    parser: '@typescript-eslint/parser',
    env: {
        jest: true,
        es6: true,
    },
    settings: {
        'import/extensions': ['.ts', '.tsx'],
        'import/resolver': {
            node: {
                extensions: ['.ts', '.tsx'],
            },
        },
    },
    parserOptions: {
        project: './tsconfig.json',
    },
};
