import nearley  from 'nearley';
import {ASTBuilder} from './ast/ASTBuilder.js';
import  './grammar/grammar.js';

/**
 * To build the grammar.js: npm run parse
 * @type {Parser}
 */
export class Parser {

    constructor() {
        /* Due to the nature of the nearly module, we have to assign our ASTBuilder to the window scope
         * so we can reference it in the generated parser 'grammar.js' */
        window.ASTBuilder = new ASTBuilder();
    }

    parse(parseString = '') {
        const parser = this._createParser();
        let result = [];
        let errors = [];
        try {
            parseString = this._removeLineBreaks(parseString);
            result = parser.feed(parseString).results;
        } catch (parseError) {
            errors.push(`Error at character ${parseError.offset}`);
        }

        if (result.length > 1) {
            errors.push(`Ambiguous parsing: ${result.length} options. Choosing the first parsin.`);
            [result] = result;
        }
        return { result, errors, parseString };
    }


    _removeLineBreaks(string) {
        return string.replace(/(\r\n|\n|\r)/gm, '');
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






