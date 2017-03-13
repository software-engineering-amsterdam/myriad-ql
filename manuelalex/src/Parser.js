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
        window.ASTBuilder = new ASTBuilder();
    }

    parse(parseString = '') {
        let parser = this._createParser();
        let result = [];
        try {
            /* Remove all line breaks from the parse string */
            parseString = parseString.replace(/(\r\n|\n|\r)/gm, "");

            result = parser.feed(parseString).results;
        } catch (parseError) {
            console.error(`Error at character ${parseError.offset}`);
        }

        if (result.length > 1) {
            console.error(`Ambigious parsing: ${result.length} options. Choosing the first parsing`);
            result = result[0];
        }
        return result;
    }

    /**
     * The nearley parser contains a state, thus can only be used once
     * @returns {Parser}
     * @private
     */
    _createParser() {
        return new nearley.Parser(grammar.ParserRules, grammar.ParserStart);
    }

}






