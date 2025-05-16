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
    'no-shadow': 'off',
    '@typescript-eslint/no-shadow': ['error'],
    'react/jsx-props-no-spreading': 0,
    '@typescript-eslint/no-unused-expressions': 'off',
    'react/state-in-constructor': ['off'],
    'react/jsx-filename-extension': [2, { extensions: ['.jsx', '.tsx'] }],
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
                { pattern: 'commons/**', group: 'internal' },
                { pattern: 'business-dashboard/**', group: 'internal' },
                { pattern: 'consumer-dashboard/**', group: 'internal' },
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
    extends: ['plugin:react-hooks/recommended', 'airbnb', 'airbnb/hooks', 'plugin:import/typescript', 'plugin:@typescript-eslint/eslint-recommended', 'plugin:@typescript-eslint/recommended', 'plugin:prettier/recommended', 'prettier'],
    plugins: ['@typescript-eslint', 'prettier', 'import'],
    rules: {
        ...customRules
    },
    root: true,
    parser: '@typescript-eslint/parser',
    globals: {
        before: true,
        after: true,
        it: true,
        expect: true,
        describe: true,
        document: true,
        window: true,
        localStorage: true,
        fetch: true,
        beforeEach: true,
        navigator: true,
        atob: true,
    },
    overrides: [
        {
            files: ['*.js', '*.jsx'],
            parser: '@babel/eslint-parser',
        },
        {
            files: 'react-app-env.d.ts',
            rules: {
                'spaced-comment': 'off',
            },
        },
        {
            files: '**/reducers/**.ts',
            rules: {
                'default-param-last': 0,
            },
        },
    ],
    env: {
        jest: true,
        es6: true,
    },
    settings: {
        'import/extensions': ['.js', '.jsx', '.ts', '.tsx'],
        'import/resolver': {
            node: {
                extensions: ['.ts', '.tsx', '.js', '.jsx'],
            },
        },
    },
    parserOptions: {
        project: './tsconfig.json',
    },
};
