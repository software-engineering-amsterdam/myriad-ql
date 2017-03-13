import nearley                                   from 'nearley';
import {FormPostProcessor}                       from './processors/FormPostProcessor.js';
import  './grammar.js';

/**
 * To build the grammar: npm run parse
 * (Building the grammar is not yet supported due to us requiring the PostProcessor inside the grammar, need to find a solution)
 * @type {Parser}
 */
export class Parser {

    constructor() {
        window.FormPostProcessor = new FormPostProcessor();
        this.errorMessage;
        this.parseString;
    }

    parse(parseString = '') {
        let parser = this._createParser();
        let result = [];
        try {
            /* Remove all line breaks from the parse string */
            this.parseString = parseString;
            parseString = parseString.replace(/(\r\n|\n|\r)/gm, "");

            result = parser.feed(parseString).results;
        } catch (parseError) {
            this.errorMessage = `Error at character ${parseError.offset} `;
            console.error(this.errorMessage);

        }

        if (result.length > 1) {
            this.errorMessage = `Ambigious parsing: ${result.length} options. Choosing the first parsing`;
            console.error(this.errorMessage);
            result = result[0];
        }

        return {result, errors: this.errorMessage};
    }

    /**
     * The nearley parser contains a state, thus can only be used once
     * @returns {Parser}
     * @private
     */
    _createParser() {
        return new nearley.Parser(grammar.ParserRules, grammar.ParserStart);
    }

    getErrorMessage(){
        return this.errorMessage;
    }

    getParseString(){
        return this.parseString;
    }

}






