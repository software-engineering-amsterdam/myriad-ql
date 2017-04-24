import nearley  from 'nearley';
import {ASTBuilder} from './ast/ASTBuilder.js';
import './grammar/grammar.js';

/**
 * To build the grammar.js: npm run parse
 * @type {Parser}
 */
export class Parser {

    parse(parseString = '', generatedParser = null) {
        const parser = generatedParser || this.createParser(null, null);
        let result = [];
        let errors = [];
        try {
            parseString = this._removeLineBreaks(parseString);
            result = parser.feed(parseString).results;
        } catch (parseError) {
            errors.push(`Error at character ${parseError.offset}`);
        }

        if (result.length > 1) {
            // errors.push(`Ambiguous parsing: ${result.length} options. Choosing the first parsing.`);
            // [result] = result;
            result = [result[0]];
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
    createParser(importedNearley = null, grammar = null) {


        if (typeof module !== 'undefined' && typeof module.exports !== 'undefined') {
            return new importedNearley.Parser(grammar.ParserRules, grammar.ParserStart);
        } else {
            /* Due to the nature of the nearly module, we have to assign our ASTBuilder to the window scope
             * so we can reference it in the generated parser 'grammar.js' */
            window.ASTBuilder = new ASTBuilder();

            /* Grammar is defined on the window scope by grammar.js due to the nature of the nearley module */
            return new (importedNearley || nearley).Parser((grammar || window.grammar).ParserRules, (grammar || window.grammar).ParserStart);
        }
    }
}



