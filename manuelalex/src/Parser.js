import nearley                            from 'nearley';
import {ASTBuilder}                       from './ast/ASTBuilder.js';
import  './grammar.js';

/**
 * To build the grammar: npm run parse
 * (Building the grammar is not yet supported due to us requiring the PostProcessor inside the grammar, need to find a solution)
 * @type {Parser}
 */
export class Parser {

    constructor() {
        /* Due to the nature of the nearly module, we have to assing our ASTBuilder to the window scope so we can reference it in the generated parser 'grammar.js' */
        window.ASTBuilder = new ASTBuilder();
    }

    parse(parseString = '') {
        const parser = this._createParser();
        let result = [];
        const errors = [];
        try {
            /* Remove all line breaks from the parse string */
            parseString = parseString.replace(/(\r\n|\n|\r)/gm, '');
            result = parser.feed(parseString).results;
        } catch (parseError) {
            errors.push(`Error at character ${parseError.offset}`);
        }

        if (result.length > 1) {
            errors.push(`Ambiguous parsing: ${result.length} options. Choosing the first parsing`);
            [result] = result;
        }

        return { result, errors, parseString };
    }

    /**
     * The nearley parser contains a state, thus can only be used once
     * @returns {Parser}
     * @private
     */
    _createParser() {
        /* Grammar is defined on the window scope by grammar.js due to the nature of the nearley module */
        return new nearley.Parser(grammar.ParserRules, grammar.ParserStart);
    }
}






