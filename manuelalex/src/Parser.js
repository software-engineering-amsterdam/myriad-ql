const jspmImport = require('./utils/JspmImport.js');

const grammar = require('./grammar.js');

let nearley;
let test1, test2, testOperator;

/**
 * To build the grammer: nearleyc grammar.ne -o grammar.js
 * (Building the grammar is not yet supported due to us requiring the PostProcessor inside the grammar, need to find a solution)
 * @type {Parser}
 */
module.exports = class Parser {

    constructor() {
        Promise.all([
            jspmImport('nearley'),
            jspmImport('./src/test/TestStrings.js')
        ]).then((imports) => {
            [nearley, {test1, test2, testOperator}] = imports;
        }).then(this._run.bind(this)).catch((error) => {
            // todo handle import errors correctly
            console.log(error);
        });
    }

    _run() {

        const parser = new nearley.Parser(grammar.ParserRules, grammar.ParserStart);
        let result;
        // Create a Parser object from our grammar.
        try{
            result = parser.feed(testOperator).results;

        } catch (parseError){
            console.log(`Error at character ${parseError.offset}`);
        } finally{
            console.log(`Result: ${JSON.stringify(result)}`);
        }
    }
};






